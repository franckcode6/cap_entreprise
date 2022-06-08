package fr.humanbooster.cap_entreprise.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.humanbooster.cap_entreprise.business.Utilisateur;

@DataJpaTest
public class UtilisateurDaoTest {

	@Autowired
	private UtilisateurDao utilisateurDao;

	/**
	 * Test de la méthode save() de UtilisateurDao
	 */
	@Test
	public void testSave() {
		String pseudoUtilisateur = "pseudoTest";
		String emailUtilisateur = "test@hb.com";
		String mdpUtilisateur = "12345";
		Utilisateur utilisateur = new Utilisateur(pseudoUtilisateur, emailUtilisateur, mdpUtilisateur);
		Utilisateur utilisateurEnregistre = utilisateurDao.save(utilisateur);

		assertNotNull(utilisateurEnregistre);
		assertNotNull(utilisateurEnregistre.getId());
		assertTrue(utilisateurEnregistre.getId() > 0);
		assertNotNull(utilisateurEnregistre.getPseudo());
		assertEquals(utilisateurEnregistre.getPseudo(), pseudoUtilisateur);
		assertNotNull(utilisateurEnregistre.getEmail());
		assertEquals(utilisateurEnregistre.getEmail(), emailUtilisateur);
		assertNotNull(utilisateurEnregistre.getMotDePasse());
		assertEquals(utilisateurEnregistre.getMotDePasse(), mdpUtilisateur);
	}

	/**
	 * Test de la méthode findAll() de UtilisateurDao
	 */
	@Test
	public void testFindAll() {
		List<Utilisateur> utilisateurs = new ArrayList<>();

		utilisateurs.add(new Utilisateur("Riri", "riri@hb.com", "12345"));

		utilisateurs.add(new Utilisateur("Fifi", "fifi@hb.com", "12345"));

		utilisateurs.add(new Utilisateur("Loulou", "loulou@hb.com", "12345"));

		utilisateurDao.saveAll(utilisateurs);
		List<Utilisateur> utilisateursRecuperes = utilisateurDao.findAll();
		assertEquals(utilisateurs, utilisateursRecuperes);
	}
}
