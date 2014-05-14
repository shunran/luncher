package ee.ttu.luncher.controller;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ee.ttu.luncher.drools.Answer;
import ee.ttu.luncher.drools.KieBean;
import ee.ttu.luncher.drools.Rules;
import ee.ttu.luncher.drools.Rules.FormStrings;

@Controller
@SessionAttributes({"rules"})
@RequestMapping("/drools")
@Log
public class DroolsController {
	
	@Autowired
	KieBean kieBean;
	
	@ModelAttribute("rules")
	public Rules initNewRules() {
		Rules rules = new Rules(kieBean);
		//rules.initSession(kieBean.getNewkSession());
		log.info("creating new session!");
		return rules;
	}

	@RequestMapping("")
	public String index(/*@ModelAttribute("rules") */Rules rules, @ModelAttribute("form") Answer answer, Model model)
	{
		model.addAttribute("formdata", rules.getFormStrings());
		model.addAttribute("step", rules.getStep().toString());
		if (rules.getStep() > FormStrings.ASIZE) {
			rules.saveAnswerIfExists(answer);
			rules.launch();
			model.addAttribute("list", rules.getDeterminedChoices(2));
			return "droolsresult";
		} else if (answer.getAnswer() == null && rules.getStep() != 0) {
				rules.decreaseStep();
				String sWarning = "Palun vali küsimusele vastus!";
				model.addAttribute("warning", sWarning);
		} else {
			rules.saveAnswerIfExists(answer);
		}
		model.addAttribute("formdata", rules.getFormStrings());
		rules.increaseStep();
		return "droolsform";
	}

	@RequestMapping("/clear")
	public String index(SessionStatus status)
	{
		status.setComplete();
		return "redirect:/";
	}

	@RequestMapping("/full")
	public String fullTable(Rules rules, Model model)
	{
		log.info(rules.getStep().toString());
		if (rules.getStep() <= FormStrings.ASIZE)
			return "redirect:/";
		model.addAttribute("list", rules.getDeterminedChoices());
		return "droolsfull";
	}
}