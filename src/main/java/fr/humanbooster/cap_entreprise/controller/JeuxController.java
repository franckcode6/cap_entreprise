package fr.humanbooster.cap_entreprise.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Moderateur;
import fr.humanbooster.cap_entreprise.business.Plateforme;
import fr.humanbooster.cap_entreprise.service.ClassificationService;
import fr.humanbooster.cap_entreprise.service.EditeurService;
import fr.humanbooster.cap_entreprise.service.GenreService;
import fr.humanbooster.cap_entreprise.service.JeuService;
import fr.humanbooster.cap_entreprise.service.ModeleEconomiqueService;
import fr.humanbooster.cap_entreprise.service.PlateformeService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class JeuxController {

	protected final static String DOSSIER_IMAGE = "src/main/Webapp/images/";
	private static final int NB_JEUX_PAR_PAGE = 4;

	private final HttpSession httpSession;

	private final ClassificationService classificationService;
	private final EditeurService editeurService;
	private final GenreService genreService;
	private final ModeleEconomiqueService modeleEconomiqueService;
	private final PlateformeService plateformeService;
	private final JeuService jeuService;

	/**
	 * Url mapping qui nous renvoie sur la vue listeDesJeux.jsp On r??cup??re tous les
	 * jeux en base de donn??es pour les afficher dans la vue
	 * 
	 * La pagination comprend 0 ?? 4 jeux par page.
	 * 
	 * @return
	 */
	@GetMapping("/admin/jeux")
	public ModelAndView jeuxGet(@PageableDefault(size = NB_JEUX_PAR_PAGE, sort = "id") Pageable pageable) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("listeDesJeux");

		mav.addObject("jeux", jeuService.recupererJeux());
		mav.addObject("pageDeJeux", jeuService.recupererJeux(pageable));

		if (pageable != null) {
			httpSession.setAttribute("numeroDePage", pageable.getPageNumber());
		}
		return mav;
	}

	/**
	 * Url mapping qui nous renvoie sur la vue ajoutJeux.jsp, cette vue contient le
	 * formulaire d'ajout / modification d'un jeu
	 * 
	 * @return
	 */
	@GetMapping("/admin/jeux/ajout")
	public ModelAndView ajoutJeuxGet(@RequestParam(name = "id", required = false, defaultValue = "0") Long id) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("ajoutJeux");

		mav.addObject("classifications", classificationService.recupererClassifications());
		mav.addObject("editeurs", editeurService.recupererEditeurs());
		mav.addObject("genres", genreService.recupererGenres());
		mav.addObject("modeleEconomiques", modeleEconomiqueService.recupererModeleEconomiques());
		mav.addObject("plateformes", plateformeService.recupererPlateformes());
		mav.addObject("jeu", jeuService.recupererJeu(id));

		return mav;
	}

	/**
	 * M??thode pour ajouter ou modifier un jeu. On t??l??verse l'image du jeu ajout??e
	 * par le mod??rateur gr??ce ?? la m??thode enregistrerFichier()
	 * 
	 * @param id
	 * @param nom
	 * @param description
	 * @param dateForm
	 * @param multipartFile
	 * @param modeleEconomique
	 * @param plateformes
	 * @param editeur
	 * @param genre
	 * @param classification
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/admin/jeux/ajout")
	public ModelAndView ajoutJeuxPost(@RequestParam(name = "id", required = false) Long id,
			@RequestParam("nom") String nom, @RequestParam(name = "description", required = false) String description,
			@RequestParam("dateSortie") String dateForm,
			@RequestParam(name = "image", required = false) MultipartFile multipartFile,
			@RequestParam("modeleEconomique") ModeleEconomique modeleEconomique,
			@RequestParam(name = "plateformes", required = false) List<Plateforme> plateformes,
			@RequestParam("editeur") Editeur editeur, @RequestParam("genre") Genre genre,
			@RequestParam("classification") Classification classification) throws IOException {

		LocalDate dateSortie = LocalDate.parse(dateForm);

		Moderateur moderateur = (Moderateur) httpSession.getAttribute("utilisateur");

		String image = multipartFile.getOriginalFilename();

		if (image != "") {
			enregistrerFichier(image, multipartFile);
		}

		if (id == null) {
			jeuService.ajouterJeu(nom, description, dateSortie, image, moderateur, modeleEconomique, plateformes,
					editeur, genre, classification);
		} else {
			jeuService.modifierJeu(id, nom, description, dateSortie, image, moderateur, modeleEconomique, editeur,
					genre, classification);
		}

		return new ModelAndView("redirect:/admin/jeux");
	}

	/**
	 * M??thode permettant de cr??er le chemin du fichier image et de l'enregistrer
	 * 
	 * @param nom
	 * @param multipartFile
	 * @throws IOException
	 */
	protected static void enregistrerFichier(String nom, MultipartFile multipartFile) throws IOException {
		Path chemin = Paths.get(DOSSIER_IMAGE);

		if (!Files.exists(chemin)) {
			Files.createDirectories(chemin);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path cheminFichier = chemin.resolve(nom);
			System.out.println(cheminFichier);
			Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Erreur d'??criture : " + nom, ioe);
		}
	}

	/**
	 * Url mapping qui nous renvoie sur la vue detailsJeux.jsp On r??cup??re le jeu
	 * dont on veut voir le d??tail gr??ce ?? son id;
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("admin/jeux/details")
	public ModelAndView detailsJeuGet(@RequestParam(name = "id", required = true) Long id) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("detailsJeux");

		mav.addObject("jeu", jeuService.recupererJeu(id));

		return mav;
	}

	/**
	 * M??thode permettant de supprimer un jeu
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("admin/jeux/supprimer")
	public ModelAndView supprimerJeuGet(@RequestParam(name = "id", required = true) Long id) {

		jeuService.supprimerJeu(id);

		return new ModelAndView("redirect:/admin/jeux");
	}

	/**
	 * M??thode permettant d'exporter la liste des jeux au format csv
	 * 
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("admin/jeux/export")
	public void exporterJeux(HttpServletResponse response) throws Exception {

		// D??finition du type de fichier export??
		response.setContentType("application/ms-excel");

		// Ajout du titre
		response.setHeader("Content-Disposition", "attachment; filename=jeux.csv");

		try {
			OutputStream out = response.getOutputStream();
			// Mise en place des headers, correspondants ?? la ligne 1 du document export??
			String header = "Id; Nom; Description; Date de sortie; Editeur; Modele Economique; Genre; Classification"
					+ "" + "\n";
			out.write(header.toString().getBytes());
			// Pour chaque jeu en BDD on affiche une information par cellule
			// Utilisation des ";" pour g??rer la s??paration des donn??es
			for (Jeu jeu : jeuService.recupererJeux()) {
				String line = new String(jeu.getId() + ";" + jeu.getNom() + ";" + jeu.getDescription() + ";"
						+ jeu.getDateSortie() + ";" + jeu.getEditeur() + ";" + jeu.getModeleEconomique() + ";"
						+ jeu.getGenre() + ";" + jeu.getClassification() + "\n");
				out.write(line.toString().getBytes());
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
