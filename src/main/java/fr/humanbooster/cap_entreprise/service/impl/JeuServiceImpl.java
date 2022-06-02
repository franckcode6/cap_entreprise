package fr.humanbooster.cap_entreprise.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Moderateur;
import fr.humanbooster.cap_entreprise.business.Plateforme;
import fr.humanbooster.cap_entreprise.dao.JeuDao;
import fr.humanbooster.cap_entreprise.service.JeuService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JeuServiceImpl implements JeuService {

	private final JeuDao jeuDao;

	@Override
	public Jeu ajouterJeu(String nom, String description, LocalDate dateSortie, String image, Moderateur moderateur,
			ModeleEconomique modeleEconomique, List<Plateforme> plateformes, Editeur editeur, Genre genre,
			Classification classification) {
		return jeuDao.save(new Jeu(nom, description, dateSortie, image, moderateur, modeleEconomique, plateformes,
				editeur, genre, classification));
	}

	@Override
	public List<Jeu> recupererJeux() {
		return jeuDao.findAll();
	}
	
	/**
	 * On récupère la totalité des avis à paginer
	 */
	@Override
	public Page<Jeu> recupererJeux(Pageable pageable) {
		return jeuDao.findAll(pageable);
	}

	@Override
	public Jeu recupererJeu(Long id) {
		return jeuDao.findById(id).orElse(null);
	}

	@Override
	public Jeu modifierJeu(Long id, String nom, String description, LocalDate dateSortie, String image,
			Moderateur moderateur, ModeleEconomique modeleEconomique, Editeur editeur, Genre genre,
			Classification classification) {
		Jeu jeu = this.recupererJeu(id);
		jeu.setNom(nom);
		jeu.setDescription(description);
		jeu.setDateSortie(dateSortie);
		jeu.setImage(image);
		jeu.setModerateur(moderateur);
		jeu.setModeleEconomique(modeleEconomique);
		jeu.setEditeur(editeur);
		jeu.setGenre(genre);
		jeu.setClassification(classification);
		return jeuDao.save(jeu);
	}

	@Override
	public void supprimerJeu(Long id) {
		jeuDao.deleteById(id);
	}

}
