package ee.ttu.luncher.drools;

import lombok.extern.java.Log;
import ee.ttu.luncher.generic.AbstractRules;

@Log
public class Rules extends AbstractRules {
	private KieBean kieBean;
	
	public Rules(KieBean kieBean) {
		super();
		this.kieBean = kieBean;
	}
	
	public void launch() {
		log.info("sisestan drools sessiooni vastused:" + choice);
		facts = kieBean.launch(choice);
		log.info("Rules fired");
	}
}
