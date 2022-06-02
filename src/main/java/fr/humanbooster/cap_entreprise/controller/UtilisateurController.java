package fr.humanbooster.cap_entreprise.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.cap_entreprise.business.Utilisateur;
import fr.humanbooster.cap_entreprise.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UtilisateurController {

	private final UtilisateurService utilisateurService;
	private final HttpSession httpSession;

	/**
	 * Url mapping qui nous renvoie sur la vue index.jsp cette vue contient le
	 * formulaire de connexion
	 * 
	 * @return
	 */
	@GetMapping({ "/index", "/" })
	public ModelAndView connexionGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("index");

		return mav;
	}

	/**
	 * Méthode permettant de vérifier si l'utilisateur peut se connecter ou non: si
	 * l'utilisateur n'est pas inscrit, celui-ci restera sur la page index.jsp sinon
	 * il sera récupéré et placé en session. Si l'utilisateur est un modérateur, il
	 * sera renvoyé sur la route admin/, si c'est un joueur il sera renvoyé sur la
	 * route avis/
	 * 
	 * @param pseudo
	 * @param motDePasse
	 * @return
	 */
	@PostMapping({ "/index", "/" })
	public ModelAndView connexionPost(@RequestParam("pseudo") String pseudo,
			@RequestParam("motDePasse") String motDePasse) {

		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(pseudo, motDePasse);
		System.out.println("utilisateur: " + utilisateur);

		if (utilisateur == null) {
			ModelAndView mav = new ModelAndView("redirect:/index");
			return mav;
		} else if (utilisateur.getPseudo().equals("franck")) {
			httpSession.setAttribute("utilisateurConnecte", utilisateur);
			httpSession.setAttribute("moderateur", utilisateur);
			ModelAndView mav = new ModelAndView("redirect:/admin");
			return mav;
		} else {
			httpSession.setAttribute("utilisateurConnecte", utilisateur);
			httpSession.setAttribute("joueur", utilisateur);
			ModelAndView mav = new ModelAndView("redirect:/avis");
			return mav;
		}

	}

	/**
	 * Url mapping qui nous renvoie sur la vue inscription.jsp cette vue contient le
	 * formulaire d'inscription
	 * 
	 * @return
	 */
	@GetMapping("/inscription")
	public ModelAndView inscriptionGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("inscription");

		return mav;
	}

	/**
	 * Méthode permettant à un joueur de s'inscrire
	 * 
	 * @param pseudo
	 * @param email
	 * @param motDePasse
	 * @param dateForm
	 * @return
	 */
	@PostMapping("/inscription")
	public ModelAndView inscriptionPost(@RequestParam("pseudo") String pseudo, @RequestParam("email") String email,
			@RequestParam("motDePasse") String motDePasse, @RequestParam("dateDeNaissance") String dateForm) {

		LocalDate dateDeNaissance = LocalDate.parse(dateForm);

		utilisateurService.ajouterJoueur(pseudo, email, motDePasse, dateDeNaissance);

		return new ModelAndView("redirect:index");
	}

	/**
	 * Méthode permettant à l'utilisateur de se déconnecter
	 * 
	 * @return
	 */
	@GetMapping("/deconnexion")
	public ModelAndView deconnexionGet() {

		httpSession.invalidate();

		return new ModelAndView("redirect:/");
	}

}
