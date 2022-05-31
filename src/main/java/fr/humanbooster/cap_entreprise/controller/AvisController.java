package fr.humanbooster.cap_entreprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AvisController {

	@GetMapping("/avis")
	public ModelAndView avisGet() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("avis");
		
		return mav;
	}
}
