package fr.humanbooster.cap_entreprise.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.cap_entreprise.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UtilisateurController {

	private final UtilisateurService utilisateurService;

	@GetMapping({ "/index", "/" })
	public ModelAndView connexionGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("index");

		return mav;
	}

	@PostMapping({ "/index", "/" }) 
	public ModelAndView connexionPost(@RequestParam("pseudo") String pseudo,
			@RequestParam("motDePasse") String motDePasse) {
		
		utilisateurService.recupererUtilisateur(pseudo, motDePasse);
		
		return new ModelAndView("redirect:/avis");
	}

	@GetMapping("/inscription")
	public ModelAndView inscriptionGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("inscription");

		return mav;
	}

	@PostMapping("/inscription")
	public ModelAndView inscriptionPost(@RequestParam("pseudo") String pseudo, @RequestParam("email") String email,
			@RequestParam("motDePasse") String motDePasse, @RequestParam("dateDeNaissance") String dateForm) {

		LocalDate dateDeNaissance = LocalDate.parse(dateForm);

		utilisateurService.ajouterJoueur(pseudo, email, motDePasse, dateDeNaissance);

		return new ModelAndView("redirect:index");
	}

}