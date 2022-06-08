package fr.humanbooster.cap_entreprise.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Plateforme;

@DataJpaTest
public class JeuDaoTest {

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
	 * Test de la méthode save() de JeuDao
	 */
	@Test
	public void testSave() {
		// Création de variables pour la vérification post sauvegarde en BDD par JeuDao
		String nomJeu = "PacMan";
		String descriptionJeu = "test";
		LocalDate dateSortieJeu = LocalDate.of(2008, 10, 12);
		String imgJeu = "";

		// Création du tableau des plateformes
		List<Plateforme> plateformes = new ArrayList<>();
		plateformes.add(new Plateforme("NES"));
		plateformes.add(new Plateforme("SNES"));
		plateformeDao.saveAll(plateformes);

		// Ajout du jeu en BDD via jeuDao
		Jeu jeu = new Jeu(nomJeu, descriptionJeu, dateSortieJeu, imgJeu, null,
				modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
				editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
				classificationDao.save(new Classification("PEGI 3")));
		Jeu jeuEnregistre = jeuDao.save(jeu);

		// Vérification entre les données initiales et celles qui ont été enregistrées
		// en BDD
		assertNotNull(jeuEnregistre);
		assertNotNull(jeuEnregistre.getId());
		assertTrue(jeuEnregistre.getId() > 0);
		assertNotNull(jeuEnregistre.getNom());
		assertEquals(jeuEnregistre.getNom(), nomJeu);
		assertNotNull(jeuEnregistre.getDescription());
		assertEquals(jeuEnregistre.getDescription(), descriptionJeu);
		assertNotNull(jeuEnregistre.getDateSortie());
		assertEquals(jeuEnregistre.getDateSortie(), dateSortieJeu);
	}

	/**
	 * Test de la méthode findAll() de JeuDao
	 */
	@Test
	public void testFindAll() {
		List<Plateforme> plateformes = new ArrayList<>();
		plateformes.add(new Plateforme("NES"));
		plateformes.add(new Plateforme("SNES"));
		plateformeDao.saveAll(plateformes);

		List<Jeu> jeux = new ArrayList<>();

		jeux.add(new Jeu("PacMan", "test", LocalDate.of(2008, 10, 12), "", null,
				modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
				editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
				classificationDao.save(new Classification("PEGI 3"))));

		jeux.add(new Jeu("Soulblade", "test", LocalDate.of(2008, 10, 12), "", null,
				modeleEconomiqueDao.save(new ModeleEconomique("P2P")), plateformes,
				editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
				classificationDao.save(new Classification("PEGI 16"))));

		jeux.add(new Jeu("Taico no Tatsujin", "test", LocalDate.of(2008, 10, 12), "", null,
				modeleEconomiqueDao.save(new ModeleEconomique("F2P")), plateformes,
				editeurDao.save(new Editeur("Namco")), genreDao.save(new Genre("Arcade")),
				classificationDao.save(new Classification("PEGI 3"))));

		jeuDao.saveAll(jeux);
		List<Jeu> jeuxRecuperes = jeuDao.findAll();
		assertEquals(jeux, jeuxRecuperes);
	}

}
