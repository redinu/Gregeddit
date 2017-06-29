package com.onn;


import java.security.Principal;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
	public String saveLink(@ModelAttribute Links link ,Principal principal, Model model) {
		
		link.setDate( new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) );
		link.setFullName(principal.getName());
		linksRepository.save(link);
		Iterable<Links> l = linksRepository.findAll();
		model.addAttribute("links", l);
		return "result";
	}
}
