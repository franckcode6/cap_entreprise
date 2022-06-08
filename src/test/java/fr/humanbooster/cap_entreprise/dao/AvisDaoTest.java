package fr.humanbooster.cap_entreprise.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.humanbooster.cap_entreprise.business.Avis;
import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Plateforme;

@DataJpaTest
public class AvisDaoTest {

	@Autowired
	private AvisDao avisDao;
	@Autowired
	private JeuDao jeuDao;
	@Autowired
	private JoueurDao joueurDao;
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
	 * Test de la méthode save() de AvisDao
	 */
	@Test
	public void testSave() {
		String descriptionAvis = "PacMan c'est cool";
		LocalDateTime dateAvis = LocalDateTime.now();
		float noteAvis = (float) 20;

		List<Plateforme> plateformes = new ArrayList<>();
		plateformes.add(new Plateforme("NES"));
		plateformes.add(new Plateforme("SNES"));
		plateformeDao.saveAll(plateformes);

		Avis avis = new Avis(descriptionAvis, dateAvis, noteAvis,
				joueurDao.save(new Joueur("toto", "toto@hb.com", "12345", LocalDate.of(1990, 10, 20))),
				jeuDao.save(new Jeu("PacMan", "test", LocalDate.of(2008, 10, 12), "", null,
						modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
						editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
						classificationDao.save(new Classification("PEGI 3")))));
		Avis avisEnregistre = avisDao.save(avis);

		// Vérification entre les données initiales et celles qui ont été enregistrées
		// en BDD
		assertNotNull(avisEnregistre);
		assertNotNull(avisEnregistre.getId());
		assertTrue(avisEnregistre.getId() > 0);
		assertNotNull(avisEnregistre.getDescription());
		assertEquals(avisEnregistre.getDescription(), descriptionAvis);
		assertNotNull(avisEnregistre.getDateEnvoi());
		assertEquals(avisEnregistre.getDateEnvoi(), dateAvis);
		assertNotNull(avisEnregistre.getNote());
		assertEquals(avisEnregistre.getNote(), noteAvis);
	}

	/**
	 * Test de la méthode findAll() de AvisDao
	 */
	@Test
	public void testFindAll() {
		List<Plateforme> plateformes = new ArrayList<>();
		plateformes.add(new Plateforme("NES"));
		plateformes.add(new Plateforme("SNES"));
		plateformeDao.saveAll(plateformes);

		Joueur joueur = new Joueur("toto", "toto@hb.com", "12345", LocalDate.of(1990, 10, 20));
		Jeu jeu = new Jeu("PacMan", "test", LocalDate.of(2008, 10, 12), "", null,
				modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
				editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
				classificationDao.save(new Classification("PEGI 3")));

		joueurDao.save(joueur);

		jeuDao.save(jeu);

		List<Avis> avis = new ArrayList<>();

		avis.add(new Avis("test", LocalDateTime.now(), 20, joueur, jeu));

		avis.add(new Avis("test2", LocalDateTime.now(), 10, joueur, jeu));

		avis.add(new Avis("test3", LocalDateTime.now(), 2, joueur, jeu));

		avisDao.saveAll(avis);
		List<Avis> avisRecuperes = avisDao.findAll();
		assertEquals(avis, avisRecuperes);
	}
}
