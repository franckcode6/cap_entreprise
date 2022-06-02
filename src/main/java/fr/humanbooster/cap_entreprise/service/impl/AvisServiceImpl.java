package fr.humanbooster.cap_entreprise.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Avis;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.business.Moderateur;
import fr.humanbooster.cap_entreprise.dao.AvisDao;
import fr.humanbooster.cap_entreprise.service.AvisService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvisServiceImpl implements AvisService {

	private final AvisDao avisDao;

	@Override
	public Avis ajouterAvis(String description, LocalDateTime dateEnvoi, float note, Joueur joueur, Jeu jeu) {
		return avisDao.save(new Avis(description, dateEnvoi, note, joueur, jeu));
	}

	@Override
	public List<Avis> recupererAvis() {
		return avisDao.findAll();
	}

	@Override
	public Avis recupererAvis(Long id) {
		return avisDao.findById(id).orElse(null);
	}

	/**
	 * Méthode permettant de retrouver tous les avis modérés et les avis non modérés
	 * du joueur en session que l'on va paginer
	 */
	@Override
	public Page<Avis> recupererAvisJoueurs(String pseudoModo, String pseudoJoueur, Pageable pageable) {
		return avisDao.findAllByModerateurPseudoContainingOrJoueurPseudoContaining(pseudoModo, pseudoJoueur, pageable);
	}

	/**
	 * On récupère les avis modérés qui seront paginés
	 */
	@Override
	public Page<Avis> recupererAvisModeres(String pseudo, Pageable pageable) {
		return avisDao.findAllByModerateurPseudoContaining("franck", pageable);
	}

	/**
	 * On récupère les avis non modérés qui seront paginés
	 */
	@Override
	public Page<Avis> recupererAvisAModerer(Pageable pageable) {
		return avisDao.findAllByModerateurPseudoNull(pageable);
	}

	/**
	 * On récupère la totalité des avis à paginer
	 */
	@Override
	public Page<Avis> recupererAvis(Pageable pageable) {
		return avisDao.findAll(pageable);
	}

	@Override
	public Avis validerAvis(Long id, String description, LocalDateTime dateEnvoi, float note,
			LocalDateTime dateModeration, Moderateur moderateur, Joueur joueur, Jeu jeu) {
		Avis avis = this.recupererAvis(id);
		avis.setDescription(description);
		avis.setDateEnvoi(dateEnvoi);
		avis.setNote(note);
		avis.setDateModeration(dateModeration);
		avis.setModerateur(moderateur);
		avis.setJoueur(joueur);
		avis.setJeu(jeu);
		return avisDao.save(avis);

	}

	@Override
	public void supprimerAvis(Long id) {
		avisDao.deleteById(id);

	}

}
