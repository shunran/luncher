package ee.ttu.luncher.generic;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractRules {
	
	protected @Setter @Getter Integer step = 0;
	protected @Setter @Getter Choice choice;
	
	protected List<FactVo> facts;
	
	public AbstractRules(){
		choice = new Choice();
	}
	
	public void saveAnswerIfExists(Answer answer) {
		if (answer.getAnswer() != null) {
			choice.getChoice().add(step - 1, Integer.parseInt(answer.getAnswer()));	
		}
	}

	public List<FactVo> getDeterminedChoices(int count) {
		if (count > facts.size() || count <= 0) {
			count = facts.size();
		}
		return facts.subList(0, count);
	}

	public List<FactVo> getDeterminedChoices() {
		return facts;
	}
	
	public abstract void launch();
	
	public void increaseStep() {
		step += 1;
	}
	
	public void decreaseStep() {
		step -= 1;
	}

	public FormStrings getFormStrings() {
		return new FormStrings(step);
	}
}
