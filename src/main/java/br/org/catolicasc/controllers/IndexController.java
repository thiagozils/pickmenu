package br.org.catolicasc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/oi")
	public String index() {
		return "index";
	}

}
