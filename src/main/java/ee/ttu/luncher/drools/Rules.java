package ee.ttu.luncher.drools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.drools.core.process.core.TypeObject;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;

import java.util.logging.Level;

@Log
public class Rules {
	
	private @Setter @Getter Integer step = 0;
	private @Setter @Getter String Question;
	private @Setter @Getter Choice choice;
	
	private FactDao factDao;
	
	protected KieSession kSession;
	
	public class FormStrings {
		public final static int ASIZE = 9;
		@Getter @Setter private String question;
		@Getter @Setter private String[][] answers;
		
		private final String[] qs = {
				"Kas söömiseks kuluv aeg on oluline?", //0
				"Soovite süüa koha peal või kuskil mujal?", //1
				"Kas teeninduse kvaliteet on oluline?", //2
				"Kas 15 eurot pearoa eeson liiga kallis?", // 3
				"Kas liha söömine tekitab teis negatiivseid tundeid?", // 4
				"Kas sooviksite laua varem reserveerida?", // 5
				"Kas olulisem on elamus või kõhutäis?", // 6
				"Kas olete valmis ennast viisakalt riidesse panema?", // 7
				"Kas toidu kvaliteet on teile oluline?", // 8
				"Kas lähete autoga?" }; // 9

		private final String[][][] as = { 
				{ {"jah", "1"}, {"ei", "2" } }, // 0
				{ {"koha peal", "3"}, {"kaasa", "4"} }, // 1
				{ {"oluline", "5"}, {"ei ole oluline", "6"} }, // 2
				{ {"kallis", "7"}, {"ei ole kallis", "8"} }, // 3
				{ {"jah", "9"}, {"ei", "10"} }, // 4
				{ {"jah", "11"}, {"ei", "12"} }, // 5
				{ {"elamus", "13"}, {"kõhutäis", "14"} }, // 6
				{ {"jah", "15"}, {"ei", "16"} }, // 7
				{ {"jah", "17"}, {"ei", "18"} }, // 8
				{ {"jah", "19"}, {"ei", "20"} } // 9
				};
		
		public FormStrings(int i) {
			if (ASIZE >= i) {
				question = qs[i];
				answers = as[i];				
			}
		}
	}
	public Rules() {
		try {

			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			
			kSession = kContainer.newKieSession("ksession-rules");
			factDao = new FactDao();
			factDao.load();
			for (FactVo fact : factDao.getFacts()) {
				log.info("trying to load into drools:" + fact.toString());
				kSession.insert(fact);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		choice = new Choice();
		//Collections.sort(choices);
	}
	
	public void saveAnswerIfExists(Answer answer) {
		if (answer.getAnswer() != null) {
			log.info(String.valueOf((step - 1)) + " sammu vastus oli: " + answer.toString());
			choice.getChoice().add(step - 1, Integer.parseInt(answer.getAnswer()));	
		}
	}
	
	public String getBestChoice() {
		ArrayList<FactVo> facts = new ArrayList<FactVo>();
		for (Object fact : kSession.getObjects()) {
			if (!(fact instanceof FactVo))
				continue;
			facts.add((FactVo) fact);
		}
		Collections.sort(facts);
		return facts.get(0).getName();
	}
	
	public void launch() {
		kSession.insert(choice);
		log.info("firing all the rules");
		kSession.fireAllRules();
		log.info("rules fired");
	}
	
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
