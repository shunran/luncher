package ee.ttu.luncher.neuroph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ee.ttu.luncher.generic.FactDao;
import ee.ttu.luncher.generic.FactVo;

@Component
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
		System.out.println(fraction + " " + places.size());
		System.out.println(places
				.get((int) Math.round(answer / fraction)).getName());
		System.out.println(Math.round(answer / fraction));
		System.out.println(answer);
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
//		System.out.println(choices.toString());
		return choices;
	}

}
