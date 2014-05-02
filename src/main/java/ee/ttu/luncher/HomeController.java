package ee.ttu.luncher;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"rules"})
public class HomeController {

	@RequestMapping("/")
	public String index(Rules rules, @ModelAttribute("form") Form form, Model model)
	{
		System.out.println(form.getAnswer());
		rules.setStep(rules.getStep() + 1);
		model.addAttribute("step", rules.getStep() + 1);
		model.addAttribute("formdata", rules.getFormData());
		System.out.print(rules.getStep());
		System.out.print(Arrays.toString(rules.getFormData().getAnswers()));
		if (rules.getStep() > 3) {
			System.out.print("ruulid launch");
			rules.launch();
		}
		return "index";
	}
}