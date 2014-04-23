package ee.ttu.luncher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Home {

	@RequestMapping("/")
	public String index(Form form, Model model)
	{
		model.addAttribute("step", form.getStep() + 1);
		return "index";
	}
	
	
	@RequestMapping("/testing")
	@ResponseBody
	public String testing()
	{
		return "Pablo!";
	}
	
}