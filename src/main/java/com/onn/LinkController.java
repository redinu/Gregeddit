package com.onn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkController {
	
	@Autowired
	LinksRepository linksRepository;
	
	@GetMapping("/")
	public String getForm (Model model) {
		model.addAttribute("link", new Links());
		return "mainForm";
	}
	@RequestMapping(path ="/saveLink", method= RequestMethod.POST)
	public String saveLink(@ModelAttribute Links link , Model model) {
		linksRepository.save(link);
		Iterable<Links> l = linksRepository.findAll();
		model.addAttribute("links", l);
		return "result";
	}
}
