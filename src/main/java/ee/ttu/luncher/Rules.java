package ee.ttu.luncher;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Rules {
	
	private @Setter @Getter Integer step = 0;
	private @Setter @Getter String Question;
	private @Setter @Getter Map <String, Integer> choices;
	
	private FactDao factDao;
	
	protected KieSession kSession;
	
	protected class FormStrings {
		public final int ASIZE = 2;
		@Getter @Setter private String question;
		@Getter @Setter private String[] answers;
		
		private final String[] qs = {
				"Kas eelistaksid taimetoitu?",
				"Milleks teile külmkapp, kui te ei suitseta?",
				"Mis värvi on armastus?"};

		private final String[][] as = { {
				"jah", "ei" },
				{
				"ikka", "natukene"},
				{
				"ei ole teind", "lepatriinu"}		
				};
		
		public FormStrings(int i) {
			if (ASIZE > i) {
				question = qs[i];
				answers = as[i];				
			} else {
				question = qs[0];
				answers = as[0];				
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
			//System.out.println(kSession.getEntryPoints().toArray().toString());
			for (FactVo fact : factDao.getFacts()) {
				//pass
				kSession.insert(fact);
			}

			// TODO: fill decisiontree and launch rules

			/*Message message = new Message();
			/message.setMessage("Hello World");
		    message.setStatus(Message.HELLO);
			kSession.insert(message);
			kSession.fireAllRules();*/
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void processAnswer(Answer answer) {
		return;
	}
	
	public void launch() {
		kSession.fireAllRules();
		System.out.println("and now the remainers:");
		for (Object fact : kSession.getObjects()) {
			//pass
			System.out.println(fact);
		}
		//kSession.execute(arg0)
	}
	
	public void increaseStep() {
		step += 1;
	}
	
	
	public FormStrings getFormStrings() {
		return new FormStrings(step);
	}
}
