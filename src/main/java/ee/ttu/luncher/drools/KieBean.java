package ee.ttu.luncher.drools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.extern.java.Log;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Component
public class KieBean implements InitializingBean{
	
	@Autowired
	FactDao factDao;
	
	private KieSession kSession;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("initializing kiebean");
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		kSession = kContainer.newKieSession("ksession-rules");
		insertFacts();
	}
	
	/**
	 * Clones facts from drools query and resets perceptrons.
	 * @param choice Array of user choices
	 * @return List of drools FactVO with original perceptrons.
	 */
	public List<FactVo> launch(Choice choice) {
		log.info("Käivitan droolsi mootori");
		FactHandle fHandler = kSession.insert(choice);
		kSession.getAgenda().getAgendaGroup("ag1").setFocus();
		kSession.fireAllRules();
		QueryResults results = kSession.getQueryResults("getFacts");
		ArrayList<FactVo> facts = new ArrayList<FactVo>();
		for (QueryResultsRow resultRow : results ) {
			FactVo fact = (FactVo) resultRow.get("fact");
			facts.add(fact.clone());
			fact.setPerceptron(0.0);
		}
		Collections.sort(facts);
		kSession.delete(fHandler);
		return facts;
	}
	
	public void insertFacts() {
		try {
			for (FactVo fact : factDao.getFacts()) {
				log.info("Trying to load into drools:" + fact.toString());
				kSession.insert(fact);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
