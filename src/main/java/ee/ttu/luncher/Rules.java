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
		final int len = 1;
		@Getter @Setter private String question;
		@Getter @Setter private String[] answers;
		
		private final String[] qs = {
				"Kui vana oli surnud siil?",
				"Milleks teile külmkapp, kui te ei suitseta?",
				"Mis värvi on armastus?"};

		private final String[][] as = { {
				"jeha", "nope" },
				{
				"ikka", "natukene"},
				{
				"ei ole teind", "lepatriinu"}		
				};
		
		public FormStrings(int i) {
			if (len >= i) {
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
	
	public void launch() {
		kSession.fireAllRules();
	}
	
	
	public FormStrings getFormData() {
		return new FormStrings(step);
	}
/*
	public static class Message {

		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;

		private int status;

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(int status) {
			this.status = status;
		}
	}*/
}
