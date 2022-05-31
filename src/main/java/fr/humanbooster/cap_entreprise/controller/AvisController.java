package fr.humanbooster.cap_entreprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.cap_entreprise.service.AvisService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AvisController {

	private final AvisService avisService;

	@GetMapping("/avis")
	public ModelAndView avisGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("listeDesAvis");
		mav.addObject("avis", avisService.recupererAvis());

		return mav;
	}

	@PostMapping("/avis")
	public ModelAndView avisPost() {

		return new ModelAndView();
	}
}
