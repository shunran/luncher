package ee.ttu.luncher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ee.ttu.luncher.drools.Answer;
import ee.ttu.luncher.drools.Rules;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
}