package ee.ttu.luncher;

import java.util.Map;

public class Form {
	
	private Integer step = 0;
	private String Question;
	private Map <String, Integer> choices;
	

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Map <String, Integer> getChoices() {
		return choices;
	}

	public void setChoices(Map <String, Integer> choices) {
		this.choices = choices;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}
}
