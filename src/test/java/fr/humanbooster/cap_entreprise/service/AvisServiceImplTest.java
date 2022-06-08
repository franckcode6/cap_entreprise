package fr.humanbooster.cap_entreprise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.humanbooster.cap_entreprise.business.Avis;
import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Plateforme;
import fr.humanbooster.cap_entreprise.dao.ClassificationDao;
import fr.humanbooster.cap_entreprise.dao.EditeurDao;
import fr.humanbooster.cap_entreprise.dao.GenreDao;
import fr.humanbooster.cap_entreprise.dao.JeuDao;
import fr.humanbooster.cap_entreprise.dao.JoueurDao;
import fr.humanbooster.cap_entreprise.dao.ModeleEconomiqueDao;
import fr.humanbooster.cap_entreprise.dao.PlateformeDao;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AvisServiceImplTest {

	@Autowired
	private AvisService avisService;
	@Autowired
	private JoueurDao joueurDao;
	@Autowired
	private JeuDao jeuDao;
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
	 * Test de l'ajout d'avis via AvisService
	 */
	@Test
	@Order(1)
	public void testerAjout() {
		String descriptionAvis = "PacMan c'est cool";
		LocalDateTime dateAvis = LocalDateTime.now();
		float noteAvis = (float) 20;

		List<Plateforme> plateformes = new ArrayList<>();
		plateformes.add(new Plateforme("NES"));
		plateformes.add(new Plateforme("SNES"));
		plateformeDao.saveAll(plateformes);

		Avis avis = avisService.ajouterAvis(descriptionAvis, dateAvis, noteAvis,
				joueurDao.save(new Joueur("toto", "toto@hb.com", "12345", LocalDate.of(1990, 10, 20))),
				jeuDao.save(new Jeu("PacMan", "test", LocalDate.of(2008, 10, 12), "", null,
						modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
						editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
						classificationDao.save(new Classification("PEGI 3")))));

		assertNotNull(avis);
		assertNotNull(avis.getId());
		assertTrue(avis.getId() > 0);
		assertNotNull(avis.getDescription());
		assertEquals(avis.getDescription(), descriptionAvis);
		assertNotNull(avis.getDateEnvoi());
		assertEquals(avis.getDateEnvoi(), dateAvis);
		assertNotNull(avis.getNote());
		assertEquals(avis.getNote(), noteAvis);
	}

	/**
	 * Test de la récupération d'un avis via AvisService
	 */
	@Test
	@Order(2)
	public void testerRecupererAvis() {
		Avis testAvis = avisService.recupererAvis((long) avisService.recupererAvis().size());
		assertEquals("PacMan c'est cool", testAvis.getDescription());
	}

	/**
	 * Test de la récupération de tous les avis via AvisService
	 */
	@Test
	@Order(3)
	public void testerRecupererAviss() {
		List<Avis> avis = avisService.recupererAvis();
		assertNotNull(avis);
		assertTrue(!avis.isEmpty());
	}

}
