package fr.humanbooster.cap_entreprise.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.service.AvisService;
import fr.humanbooster.cap_entreprise.service.JeuService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AvisController {

	private final AvisService avisService;
	private final JeuService jeuService;

	private final HttpSession httpSession;

	private final static int NB_AVIS_PAR_PAGE = 5;

	/**
	 * URL mappinng qui nous renvoie sur la vue listeDesAvis.jsp On récupère les
	 * avis qui ont été modérés et qui seront paginés
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/avis")
	public ModelAndView avisGet(@PageableDefault(size = NB_AVIS_PAR_PAGE, sort = "dateEnvoi") Pageable pageable) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("listeDesAvis");

		mav.addObject("avis", avisService.recupererAvis());
		mav.addObject("pages", avisService.recupererAvisModeres("franck", pageable));

		return mav;
	}

	/**
	 * URL mappinng qui nous renvoie sur la vue ajoutAvis.jsp, cette vue contient le
	 * formulaire d'ajout d'un avis. On récupère les jeux pour les placer dans le
	 * formulaire
	 * 
	 * @return
	 */
	@GetMapping("/avis/ajout")
	public ModelAndView ajoutAvisGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("ajoutAvis");

		mav.addObject("jeux", jeuService.recupererJeux());

		return mav;
	}

	/**
	 * Méthode pour ajouter un avis
	 * 
	 * @param description
	 * @param note
	 * @param jeu
	 * @return
	 */
	@PostMapping("/avis/ajout")
	public ModelAndView ajoutAvisPost(@RequestParam("description") String description, @RequestParam("note") float note,
			@RequestParam("jeu") Jeu jeu) {

		LocalDateTime dateEnvoi = LocalDateTime.now();

		Joueur joueur = (Joueur) httpSession.getAttribute("joueur");

		avisService.ajouterAvis(description, dateEnvoi, note, joueur, jeu);

		return new ModelAndView("redirect:/avis");
	}

	/**
	 * URL Mapping qui nous renvoie sur la vue detailsAvis.jsp. On récupère l'avis
	 * dont on veut voir le détail grâce à son id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("avis/details")
	public ModelAndView detailsJeuGet(@RequestParam(name = "id", required = true) Long id) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("detailsAvis");

		mav.addObject("avis", avisService.recupererAvis(id));

		return mav;
	}
}
