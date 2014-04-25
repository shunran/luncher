package ee.ttu.luncher;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

@Component
public class Rules {
	
	private @Setter @Getter Integer step = 0;
	private @Setter @Getter String Question;
	private @Setter @Getter Map <String, Integer> choices;
	
	protected KieSession kSession;
	private FormData formData;

	public Rules() {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			//KieSession 
			kSession = kContainer.newKieSession("ksession-rules");

			// go !
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
		formData = new FormData(step);
	}
	
	public FormData getFormData() {
		return formData;
	}

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
	}
}
