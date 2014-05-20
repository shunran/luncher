package ee.ttu.luncher.neuroph;

import lombok.extern.java.Log;
import ee.ttu.luncher.generic.AbstractRules;

@Log
public class Rules extends AbstractRules {
	
	public void launch() {
		log.info("sisestan drools sessiooni vastused:" + choice);
		//facts = kieBean.launch(choice);
		log.info("Rules fired");
	}
}
