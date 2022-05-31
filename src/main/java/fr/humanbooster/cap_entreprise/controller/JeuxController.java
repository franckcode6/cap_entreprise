package fr.humanbooster.cap_entreprise.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
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

	private final HttpSession httpSession;

	private final ClassificationService classificationService;
	private final EditeurService editeurService;
	private final GenreService genreService;
	private final ModeleEconomiqueService modeleEconomiqueService;
	private final PlateformeService plateformeService;
	private final JeuService jeuService;

	@GetMapping("/admin/jeux")
	public ModelAndView avisGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("listeDesJeux");

		return mav;
	}

	@GetMapping("/admin/jeux/ajout")
	public ModelAndView ajoutJeuxGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("ajoutJeux");
		mav.addObject("classifications", classificationService.recupererClassifications());
		mav.addObject("editeurs", editeurService.recupererEditeurs());
		mav.addObject("genres", genreService.recupererGenres());
		mav.addObject("modeleEconomiques", modeleEconomiqueService.recupererModeleEconomiques());
		mav.addObject("plateformes", plateformeService.recupererPlateformes());

		return mav;

	}

	@PostMapping("/admin/jeux/ajout")
	public ModelAndView ajoutJeuxPost(@RequestParam("nom") String nom,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam("dateSortie") String dateForm, @RequestParam("image") MultipartFile multipartFile,
			@RequestParam("modeleEconomique") ModeleEconomique modeleEconomique,
			@RequestParam("plateformes") List<Plateforme> plateformes, @RequestParam("editeur") Editeur editeur,
			@RequestParam("genre") Genre genre, @RequestParam("classification") Classification classification)
			throws IOException {
		
		LocalDate dateSortie = LocalDate.parse(dateForm);

		Moderateur moderateur = (Moderateur) httpSession.getAttribute("utilisateur");
		
		String image = multipartFile.getOriginalFilename();

		enregistrerFichier(image, multipartFile);
		
		jeuService.ajouterJeu(nom, description, dateSortie, image, moderateur, modeleEconomique, plateformes, editeur, genre, classification);

		return new ModelAndView("redirect:/admin/jeux");
	}

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
			throw new IOException("Erreur d'écriture : " + nom, ioe);
		}
	}
}
