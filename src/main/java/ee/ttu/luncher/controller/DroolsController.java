package ee.ttu.luncher.controller;

import lombok.extern.java.Log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ee.ttu.luncher.drools.Answer;
import ee.ttu.luncher.drools.Rules;
import ee.ttu.luncher.drools.Rules.FormStrings;

@Controller
@SessionAttributes({"rules"})
@RequestMapping("/drools")
@Log
public class DroolsController {

	@RequestMapping("")
	public String index(Rules rules, @ModelAttribute("form") Answer answer, Model model)
	{
		model.addAttribute("formdata", rules.getFormStrings());
		rules.saveAnswerIfExists(answer);
		log.info("praegune step on" + rules.getStep().toString());
		rules.getFormStrings();
		if (rules.getStep() > FormStrings.ASIZE) {
			log.info("launching rules");
			rules.launch();
			model.addAttribute(rules.getBestChoice());
			return "droolsresult";
		} else {
			rules.increaseStep();
			return "droolsform";
		}
	}

	@RequestMapping("/clear")
	public String index(SessionStatus status)
	{
		status.setComplete();
		return "redirect:/";
	}
}