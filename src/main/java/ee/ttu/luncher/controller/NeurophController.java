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
import ee.ttu.luncher.drools.FactDao;
import ee.ttu.luncher.drools.Rules;
import ee.ttu.luncher.drools.Rules.FormStrings;
import ee.ttu.luncher.neuroph.Calculate;

@Controller
@SessionAttributes({"rules"})
@RequestMapping("/neuroph")
@Log
public class NeurophController {
	
	
	
	@Autowired
	Calculate calculate;
	
	@ModelAttribute("rules")
	public Rules initNewRules() {
		Rules rules = new Rules();
		log.info("creating new www session!");
		return rules;
	}
 
	@RequestMapping("")
	public String index(Rules rules, @ModelAttribute("form") Answer answer, Model model)
	{
		model.addAttribute("formdata", rules.getFormStrings());
		model.addAttribute("step", rules.getStep().toString());
		if (rules.getStep() > FormStrings.ASIZE) {
			
			rules.saveAnswerIfExists(answer);
			model.addAttribute("list", calculate.getAwnser(rules.getChoice().getChoice()));
			return "neurophresult";
		} else if (answer.getAnswer() == null && rules.getStep() != 0) {
				rules.decreaseStep();
				String sWarning = "Palun vali küsimusele vastus!";
				model.addAttribute("warning", sWarning);
		} else {
			rules.saveAnswerIfExists(answer);
		}
		model.addAttribute("formdata", rules.getFormStrings());
		rules.increaseStep();
		return "neurophform";
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