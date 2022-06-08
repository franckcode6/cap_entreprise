package fr.humanbooster.cap_entreprise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Plateforme;
import fr.humanbooster.cap_entreprise.dao.ClassificationDao;
import fr.humanbooster.cap_entreprise.dao.EditeurDao;
import fr.humanbooster.cap_entreprise.dao.GenreDao;
import fr.humanbooster.cap_entreprise.dao.ModeleEconomiqueDao;
import fr.humanbooster.cap_entreprise.dao.PlateformeDao;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JeuServiceImplTest {

	@Autowired
	private JeuService jeuService;
	@Autowired
	private ModeleEconomiqueDao modeleEconomiqueDao;
	@Autowired
	private PlateformeDao plateformeDao;
	@Autowired
	private EditeurDao editeurDao;
	@Autowired
	private GenreDao genreDao;
	@Autowired
	private ClassificationDao classificationDao;

	/**
	 * Test de l'ajout de Jeu via JeuService
	 */
	@Test
	@Order(1)
	public void testerAjout() {
		// Création de variables pour la vérification post sauvegarde en BDD par JeuServiceImpl
		String nomJeu = "PacMan";
		String descriptionJeu = "test";
		LocalDate dateSortieJeu = LocalDate.of(2008, 10, 12);
		String imgJeu = "";

		// Création du tableau des plateformes
		List<Plateforme> plateformes = new ArrayList<>();
		plateformes.add(new Plateforme("NES"));
		plateformes.add(new Plateforme("SNES"));
		plateformeDao.saveAll(plateformes);

		// Ajout du jeu en BDD via jeuService
		Jeu jeu = jeuService.ajouterJeu(nomJeu, descriptionJeu, dateSortieJeu, imgJeu, null,
				modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
				editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
				classificationDao.save(new Classification("PEGI 3")));

		// Vérification entre les données initiales et celles qui ont été enregistrées
		// en BDD
		assertNotNull(jeu);
		assertNotNull(jeu.getId());
		assertTrue(jeu.getId() > 0);
		assertNotNull(jeu.getNom());
		assertEquals(jeu.getNom(), nomJeu);
		assertNotNull(jeu.getDescription());
		assertEquals(jeu.getDescription(), descriptionJeu);
		assertNotNull(jeu.getDateSortie());
		assertEquals(jeu.getDateSortie(), dateSortieJeu);
	}

	/**
	 * Test de la récupération d'un jeu via JeuService
	 */
	@Test
	@Order(2)
	public void testerRecupererJeu() {
		Jeu testJeu = jeuService.recupererJeu((long) jeuService.recupererJeux().size());
		assertEquals("PacMan", testJeu.getNom());
		assertEquals("test", testJeu.getDescription());
		assertEquals(LocalDate.of(2008, 10, 12), testJeu.getDateSortie());
	}

	/**
	 * Test de la récupération de tous les jeux via JeuService
	 */
	@Test
	@Order(3)
	public void testerRecupererJeux() {
		List<Jeu> jeux = jeuService.recupererJeux();
		assertNotNull(jeux);
		assertTrue(!jeux.isEmpty());
	}
}
