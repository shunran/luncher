package ee.ttu.luncher.drools;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.logging.Level;

@Log
public class Rules {
	
	private @Setter @Getter Integer step = 0;
	private @Setter @Getter String Question;
	private @Setter @Getter Integer[] choices;
	
	private FactDao factDao;
	
	protected KieSession kSession;
	
	public class FormStrings {
		public final static int ASIZE = 9;
		@Getter @Setter private String question;
		@Getter @Setter private String[][] answers;
		
		private final String[] qs = {
				"Kas söömiseks kuluv aeg on oluline?",
				"Soovite süüa koha peal või kuskil mujal?",
				"Kas teeninduse kvaliteet on oluline?",
				"Kas 15 eurot pearoa eeson liiga kallis?",
				"Kas liha söömine tekitab teis negatiivseid tundeid?",
				"Kas sooviksite laua varem reserveerida?",
				"Kas olulisem on elamus või kõhutäis?",
				"Kas olete valmis ennast viisakalt riidesse panema?",
				"Kas toidu kvaliteet on teile oluline?",
				"Kas lähete autoga?" };

		private final String[][][] as = { 
				{ {"jah", "1"}, {"ei", "2" } },
				{ {"koha peal", "3"}, {"kaasa", "4"} },
				{ {"oluline", "5"}, {"ei ole oluline", "6"} },
				{ {"kallis", "7"}, {"ei ole kallis", "8"} },
				{ {"jah", "9"}, {"ei", "10"} },
				{ {"jah", "11"}, {"ei", "12"} },
				{ {"elamus", "13"}, {"kõhutäis", "14"} },
				{ {"jah", "15"}, {"ei", "16"} },
				{ {"jah", "17"}, {"ei", "18"} },
				{ {"jah", "19"}, {"ei", "20"} }
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
		choices = new Integer[FormStrings.ASIZE + 1];
	}
	
	public void saveAnswerIfExists(Answer answer) {
		if (answer.getAnswer() != null) {
			log.info(String.valueOf((step - 1)) + " sammu vastus oli: " + answer.toString());
			choices[step-1] = Integer.parseInt(answer.getAnswer());	
		}
	}
	
	public String getBestChoice() {
		//kSession.getQueryResults("get best choice");
		return "";
	}
	
	public void launch() {
		kSession.fireAllRules();
		log.info(choices.toString());
		log.info("and now the remainers:");
		for (Object fact : kSession.getObjects()) {
			log.info(fact.toString());
		}
	}
	
	public void increaseStep() {
		step += 1;
	}
	
	
	public FormStrings getFormStrings() {
		return new FormStrings(step);
	}
}
