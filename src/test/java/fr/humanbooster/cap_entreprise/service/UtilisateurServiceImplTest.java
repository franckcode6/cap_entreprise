package fr.humanbooster.cap_entreprise.service;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.humanbooster.cap_entreprise.business.Utilisateur;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilisateurServiceImplTest {

	@Autowired
	private UtilisateurService utilisateurService;

	/**
	 * Test de l'ajout via UtilisateurService
	 */
	@Test
	@Order(1)
	public void testerAjout() {
		String pseudoUtilisateur = "Toto";
		String emailUtilisateur = "toto@hb.com";
		String mdpUtilisateur = "12345";
		LocalDate dateDeNaissanceUtilisateur = LocalDate.of(1995, 1, 9);
		Utilisateur utilisateur = utilisateurService.ajouterJoueur(pseudoUtilisateur, emailUtilisateur, mdpUtilisateur,
				dateDeNaissanceUtilisateur);
		assertNotNull(utilisateur);
		assertNotNull(utilisateur.getId());
		assertTrue(utilisateur.getId() > 0);
		assertNotNull(utilisateur.getPseudo());
		assertEquals(utilisateur.getPseudo(), pseudoUtilisateur);
		assertNotNull(utilisateur.getEmail());
		assertEquals(utilisateur.getEmail(), emailUtilisateur);
		assertNotNull(utilisateur.getMotDePasse());
		assertEquals(utilisateur.getMotDePasse(), mdpUtilisateur);
	}

	/**
	 * Test de la récupération d'un utilisateur via UtilisateurService
	 */
	@Test
	@Order(2)
	public void testerRecupererUtilisateur() {
		Utilisateur testUtilisateur = utilisateurService.recupererUtilisateur("Toto", "12345");
		assertEquals("Toto", testUtilisateur.getPseudo());
		assertEquals("toto@hb.com", testUtilisateur.getEmail());
		assertEquals("12345", testUtilisateur.getMotDePasse());
	}

}
