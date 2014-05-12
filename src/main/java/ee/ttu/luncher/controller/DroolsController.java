package ee.ttu.luncher.controller;

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
public class DroolsController {

	@RequestMapping("")
	public String index(Rules rules, @ModelAttribute("form") Answer answer, Model model)
	{
		model.addAttribute("formdata", rules.getFormStrings());
		if (answer.getAnswer() == null && rules.getStep() != 0) {
			rules.decreaseStep();
			String sWarning = "Palun vali küsimusele vastus!";
			model.addAttribute("warning", sWarning);
		} else if (rules.getStep() > FormStrings.ASIZE) {
			rules.saveAnswerIfExists(answer);
			rules.launch();
			model.addAttribute("best", rules.getBestChoice());
			return "droolsresult";
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
	public String showFullTable(Rules rules, Model model)
	{
		if (rules.getStep() > FormStrings.ASIZE) {
			model.addAttribute("list", rules.getDeterminedChoices(10));
			return "droolsfull";
		}
		return "redirect:/";
	}
}