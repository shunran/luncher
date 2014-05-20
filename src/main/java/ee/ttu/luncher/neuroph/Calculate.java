package ee.ttu.luncher.neuroph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.java.Log;

import org.neuroph.core.NeuralNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	NeuralNetwork network;

	private Double calculate(double... input) {
		network.setInput(input);
		network.calculate();
		double[] output = network.getOutput();
		Double answer = output[0];
		return answer;
	}

	public FactVo getAnswer(List<Integer> choice) {
		InputStream in = (InputStream) getClass().getResourceAsStream(
				"/resources/luncher1.nnet");
		network = NeuralNetwork.load(in);
		choice = normalizeData(choice);
		Double answer = calculate(choice.get(0), choice.get(1), choice.get(2),
				choice.get(3), choice.get(4), choice.get(5), choice.get(6),
				choice.get(7), choice.get(8), choice.get(9));

		ArrayList<FactVo> places = factDao.getFacts();
		Double fraction = 0.05;
		log.info(fraction + " " + places.size());
		log.info(places
				.get((int) Math.round(answer / fraction)).getName());
		log.info(String.valueOf(Math.round(answer / fraction)));
		log.info(answer.toString());
		return places.get((int) Math.round(answer / fraction));
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
	

	public void train (KieBean kieBean) {

		List <Choice> choices = new ArrayList<Choice>(FormStrings.ASIZE+1);
		for (Integer i=0; i < Math.pow(2,FormStrings.ASIZE+1); i++) {
			String pad = String.format("%0" + (FormStrings.ASIZE + 1) + 'd', new Integer(Integer.toBinaryString(i)));
			Choice choice = new Choice();
			for (char ch: pad.toCharArray()) {
				choice.getChoice().add(Character.getNumericValue(ch));
			}
			choices.add(choice);
		}
		log.info(choices.toString());
		//kieBean.launch(choice); ????
		//NeuralNetwork nw  = new NeuralNetwork(); ???
		return;
	}

}
