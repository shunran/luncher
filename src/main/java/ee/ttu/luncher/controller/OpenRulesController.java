package ee.ttu.luncher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenRulesController {

	@RequestMapping("/orules")
	public String index()
	{
	   return "orules";
	}
}