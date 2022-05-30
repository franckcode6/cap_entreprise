package fr.humanbooster.cap_entreprise.service;

import java.time.LocalDate;

import fr.humanbooster.cap_entreprise.business.Utilisateur;

public interface UtilisateurService {

	Utilisateur ajouterJoueur(String pseudo, String email, String motDePasse, LocalDate dateDeNaissance);

	Utilisateur recupererUtilisateur(String pseudo, String motDePasse);

}
