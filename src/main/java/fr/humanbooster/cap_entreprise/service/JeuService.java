package fr.humanbooster.cap_entreprise.service;

import java.time.LocalDate;
import java.util.List;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Moderateur;
import fr.humanbooster.cap_entreprise.business.Plateforme;

public interface JeuService {

	Jeu ajouterJeu(String nom, String description, LocalDate dateSortie, String image, Moderateur moderateur,
			ModeleEconomique modeleEconomique, List<Plateforme> plateformes, Editeur editeur, Genre genre,
			Classification classification);

	List<Jeu> recupererJeux();

}
