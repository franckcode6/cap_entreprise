package fr.humanbooster.cap_entreprise.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.business.Utilisateur;
import fr.humanbooster.cap_entreprise.dao.JoueurDao;
import fr.humanbooster.cap_entreprise.dao.UtilisateurDao;
import fr.humanbooster.cap_entreprise.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private final JoueurDao joueurDao;
	private final UtilisateurDao utilisateurDao;
	
	@Override
	public Utilisateur ajouterJoueur(String pseudo, String email, String motDePasse, LocalDate dateDeNaissance) {
		return joueurDao.save(new Joueur(pseudo, email, motDePasse, dateDeNaissance));
	}

	@Override
	public Utilisateur recupererUtilisateur(String pseudo, String motDePasse) {
		return utilisateurDao.findByPseudoAndMotDePasse(pseudo, motDePasse);
	}

}
