package fr.humanbooster.cap_entreprise.service;

import java.time.LocalDateTime;
import java.util.List;

import fr.humanbooster.cap_entreprise.business.Avis;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.business.Moderateur;

public interface AvisService {

	Avis ajouterAvis(String description, LocalDateTime dateEnvoi, float note, Joueur joueur, Jeu jeu);

	List<Avis> recupererAvis();

	Avis recupererAvis(Long id);

	Avis validerAvis(Long id, String description, LocalDateTime dateEnvoi, float note, LocalDateTime dateModeration,
			Moderateur moderateur, Joueur joueur, Jeu jeu);
}
