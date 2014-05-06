package ee.ttu.luncher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"rules"})
public class HomeController {

	@RequestMapping("/")
	public String index(Rules rules, @ModelAttribute("form") Answer answer, Model model)
	{
		model.addAttribute("formdata", rules.getFormStrings());
		rules.increaseStep();
		if (rules.getStep() > rules.getFormStrings().ASIZE) {
			System.out.print("ruulid launch");
			rules.launch();
			return "result";
		} else {
			return "index";
		}
	}
}