package ee.ttu.luncher.neuroph;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.java.Log;

import org.neuroph.core.NeuralNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ee.ttu.luncher.drools.KieBean;
import ee.ttu.luncher.generic.Choice;
import ee.ttu.luncher.generic.FactDao;
import ee.ttu.luncher.generic.FactVo;
import ee.ttu.luncher.generic.FormStrings;

@Component
@Log
public class Calculate {

	@Autowired
	FactDao factDao; 
	
	@Autowired
	KieBean kieBean;
	
	NeuralNetwork network;

	private double[] calculate(double... input) {
		network.setInput(input);
		network.calculate();
		double[] answer = network.getOutput();
		return answer;
	}

	public FactVo getAnswer(List<Integer> choice) {
		InputStream in = (InputStream) getClass().getResourceAsStream(
				"/resources/weightFile.nnet");
		network = NeuralNetwork.load(in);
		choice = normalizeData(choice);
		double[] answer = calculate(choice.get(0), choice.get(1), choice.get(2),
				choice.get(3), choice.get(4), choice.get(5), choice.get(6),
				choice.get(7), choice.get(8), choice.get(9));

		ArrayList<FactVo> places = factDao.getFacts();
		String binaryNumber = "";
		for(double n : answer){
			if(n > 0.5){
				binaryNumber += "1";
			}else{
				binaryNumber += "0";
			}
		}
		int decimalValue = Integer.parseInt(binaryNumber, 2);
		log.info(decimalValue + "");
		return places.get(decimalValue);
	}

	public List<Integer> normalizeData(List<Integer> choices) {
		for (int i = 0; i < 10; i++) {
			if (choices.get(i) > 1 && i != 1) {
				choices.set(i, 1);
			} else {
				choices.set(i, 0);
			}
		}
		return choices;
	}
	

	public void train () {
		Map <Choice, List<FactVo>> trainingData = new HashMap<Choice, List<FactVo>>();
		for (Integer i=0; i < Math.pow(2,FormStrings.ASIZE+1); i++) {
			String pad = String.format("%0" + (FormStrings.ASIZE + 1) + 'd', new Integer(Integer.toBinaryString(i)));
			Choice choice = new Choice();
			for (char ch: pad.toCharArray()) {
				choice.getChoice().add(Character.getNumericValue(ch));
			}
			trainingData.put(choice, kieBean.launch(choice));
		}
		write(trainingData);
	}
	
	private void write(Map <Choice, List<FactVo>> trainingData) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("trainingdata.txt", "UTF-8");
			for(Entry<Choice, List<FactVo>> entry : trainingData.entrySet()) {
			writer.println(entry.getKey().getChoice().toString() + entry.getValue().get(0).getName() + 
					"," + entry.getValue().get(1).getName()
					+ "," + entry.getValue().get(2).getName());
			}
			writer.println("That's it, folks!");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
