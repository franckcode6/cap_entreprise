package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Utilisateur;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	// requète nous permettant de retrouver un utilisateur grâce à
	// son pseudo et son mot de passe
	Utilisateur findByPseudoAndMotDePasse(String pseudo, String motDePasse);
}
