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

import fr.humanbooster.cap_entreprise.business.Avis;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Moderateur;
import fr.humanbooster.cap_entreprise.service.AvisService;
import fr.humanbooster.cap_entreprise.service.JeuService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {

	private final AvisService avisService;
	private final JeuService jeuService;

	private final HttpSession httpSession;

	private final static int NB_AVIS_PAR_PAGE = 5;

	// TOUTES LES ROUTES DE LA CLASSE AdminController SONT ACCESSIBLES UNIQUEMENT
	// AUX MODERATEURS

	/**
	 * URL Mapping qui nous renvoie sur la vue adminDashboard. Elle permet aux admin
	 * d'accéder à toutes les pages dont il a besoin via plusieurs boutons
	 * 
	 * @return
	 */
	@GetMapping("/admin")
	public ModelAndView adminGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("adminDashboard");

		return mav;
	}

	/**
	 * URL Mapping nous renvoyant sur la vue adminAvis.jsp accessible uniquement aux
	 * modérateurs. Cette vue contient une liste de tous les avis (modérés et non
	 * modérés) qui seront paginés
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/admin/avis")
	public ModelAndView adminAvisGet(@PageableDefault(size = NB_AVIS_PAR_PAGE, sort = "dateEnvoi") Pageable pageable) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("adminAvis");

		mav.addObject("pages", avisService.recupererAvis(pageable));

		return mav;
	}

	/**
	 * URL Mapping nous renvoyant sur la vue adminAvis.jsp. Cette vue contient une
	 * liste des avis non modérés qui seront paginés
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("admin/avis/aModerer")
	public ModelAndView adminAvisAAModererGet(
			@PageableDefault(size = NB_AVIS_PAR_PAGE, sort = "dateEnvoi") Pageable pageable) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("adminAvis");

		mav.addObject("pages", avisService.recupererAvisAModerer(pageable));

		return mav;
	}
	
	 /**
     * URL Mapping nous renvoyant sur la vue adminAvis.jsp. Cette vue contient une
     * liste des avis modérés qui seront paginés
     * 
     * @param pageable
     * @return
     */
    @GetMapping("admin/avis/moderes")
    public ModelAndView adminAvisModeresGet(
            @PageableDefault(size = NB_AVIS_PAR_PAGE, sort = "dateEnvoi") Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("adminAvis");
        
        mav.addObject("pages", avisService.recupererAvisModeres("nana", pageable));
        
        return mav;
    }

	/**
	 * URL mapping renvoyant sur la vue adminModeration.jsp. On récupère l'avis à
	 * modérer afin d'en voir le détail et on récupère les jeux à placer dans le
	 * formulaire
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/avis/moderation")
	public ModelAndView adminAModerationGet(@RequestParam(name = "id", required = true) Long id) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("adminModeration");

		mav.addObject("avis", avisService.recupererAvis(id));
		mav.addObject("jeux", jeuService.recupererJeux());

		return mav;
	}

	/**
	 * Méthode permettant de valider l'avis à modérer
	 * 
	 * @param id
	 * @param description
	 * @param note
	 * @param jeu
	 * @return
	 */
	@PostMapping("/admin/avis/moderation")
	public ModelAndView adminModerationPost(@RequestParam(name = "id", required = true) Long id,
			@RequestParam("description") String description, @RequestParam("note") float note,
			@RequestParam("jeu") Jeu jeu) {

		Avis avis = avisService.recupererAvis(id);

		LocalDateTime dateModeration = LocalDateTime.now();

		Moderateur moderateur = (Moderateur) httpSession.getAttribute("moderateur");

		avisService.validerAvis(id, description, avis.getDateEnvoi(), note, dateModeration, moderateur,
				avis.getJoueur(), avis.getJeu());

		return new ModelAndView("redirect:/admin/avis");
	}

	/**
	 * Méthode permettant de supprimer un avis
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/avis/supprimer")
	public ModelAndView supprimerAvisGet(@RequestParam(name = "id", required = true) Long id) {

		avisService.supprimerAvis(id);

		return new ModelAndView("redirect:/admin/avis");
	}
}