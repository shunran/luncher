package ee.ttu.luncher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"rules"})
public class HomeController {

	@RequestMapping("/")
	public String index(Rules rules, @ModelAttribute("form") Form form, Model model)
	{
		System.out.println(form.getAnswer());
		rules.setStep(rules.getStep() + 1);
		rules.launch();
		model.addAttribute("step", rules.getStep() + 1);
		model.addAttribute("formdata", rules.getFormData());
		System.out.print(rules.getStep());
		System.out.print(Arrays.toString(rules.getFormData().getAnswers()));
		return "index";
	}
}