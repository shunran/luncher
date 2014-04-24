package ee.ttu.luncher;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@Autowired
	Rules rules;

	@RequestMapping("/")
	public String index(Form form, Model model)
	{
		rules.launch(form.getStep());
		//Log.debug("fdsfds");
		model.addAttribute("step", form.getStep() + 1);
		model.addAttribute("formdata", rules.getFormData());
		System.out.print(Arrays.toString(rules.getFormData().getAnswers()));
		return "index";
	}
	
	
	@RequestMapping("/testing")
	@ResponseBody
	public String testing()
	{
		return "Pablo!";
	}
	
}