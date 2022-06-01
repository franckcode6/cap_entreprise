package fr.humanbooster.cap_entreprise.initialisation;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.business.Jeu;
import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.business.Moderateur;
import fr.humanbooster.cap_entreprise.business.Plateforme;
import fr.humanbooster.cap_entreprise.dao.ClassificationDao;
import fr.humanbooster.cap_entreprise.dao.EditeurDao;
import fr.humanbooster.cap_entreprise.dao.GenreDao;
import fr.humanbooster.cap_entreprise.dao.JeuDao;
import fr.humanbooster.cap_entreprise.dao.JoueurDao;
import fr.humanbooster.cap_entreprise.dao.ModeleEconomiqueDao;
import fr.humanbooster.cap_entreprise.dao.ModerateurDao;
import fr.humanbooster.cap_entreprise.dao.PlateformeDao;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

	private final Faker faker = new Faker(new Locale("fr-FR"));

	private final ClassificationDao classificationDao;
	private final EditeurDao editeurDao;
	private final GenreDao genreDao;
	private final JeuDao jeuDao;
	private final JoueurDao joueurDao;
	private final ModeleEconomiqueDao modeleEconomiqueDao;
	private final ModerateurDao moderateurDao;
	private final PlateformeDao plateformeDao;

	private List<Classification> classifications;
	private List<Editeur> editeurs;
	private List<Genre> genres;
	private List<Jeu> jeux;
	private List<Joueur> joueurs;
	private List<ModeleEconomique> modeleEconomiques;
	private List<Moderateur> moderateurs;
	private List<Plateforme> plateformes;
	
	private static Random random = new Random();

	@Override
	public void run(String... args) throws Exception {
		Date dateDebut = new Date();

		// CLASSIFICATIONS
		ajouterClassifications();

		// EDITEURS
		ajouterEditeurs();

		// GENRES
		ajouterGenres();
		

		// ajout des joueurs
		ajouterJoueurs();

		// MODELES ECO
		ajouterModeleEconomiques();

		// ajout des modérateurs
		ajouterModerateurs();

		// PLATEFORMES
		ajouterPlateformes();

		// JEUX
		ajouterJeux();
		
		Date dateFin = new Date();
		System.out.println("Données initiales générées en " + (dateFin.getTime() - dateDebut.getTime()) + " ms");
	}

	/**
	 * Ajout des classifications
	 */
	private void ajouterClassifications() {
		if (classificationDao.count() == 0) {
			classifications.add(new Classification("PEGI 3"));
			classifications.add(new Classification("PEGI 7"));
			classifications.add(new Classification("PEGI 12"));
			classifications.add(new Classification("PEGI 16"));
			classifications.add(new Classification("PEGI 18"));
			classificationDao.saveAll(classifications);
		}
	}

	/**
	 * Ajout des editeurs
	 */
	private void ajouterEditeurs() {
		if (editeurDao.count() == 0) {
			editeurs.add(new Editeur("Ubisoft"));
			editeurs.add(new Editeur("EA"));
			editeurs.add(new Editeur("Nintendo"));
			editeurs.add(new Editeur("Microsoft"));
			editeurs.add(new Editeur("2K"));
			editeurs.add(new Editeur("Sony"));
			editeurs.add(new Editeur("Devolver"));
			editeurs.add(new Editeur("Bethesda"));
			editeurs.add(new Editeur("Infogramme"));
			editeurs.add(new Editeur("Capcom"));
			editeurs.add(new Editeur("Indé"));
			editeurDao.saveAll(editeurs);
		}
	}

	/**
	 * Ajout des genres
	 */
	private void ajouterGenres() {
		if (genreDao.count() == 0) {
			genres.add(new Genre("FPS"));
			genres.add(new Genre("RPG"));
			genres.add(new Genre("Platformer"));
			genres.add(new Genre("Stratégie"));
			genres.add(new Genre("Sport"));
			genres.add(new Genre("Reflexion"));
			genres.add(new Genre("Casual"));
			genres.add(new Genre("Mobile"));
			genres.add(new Genre("Simulation"));
			genres.add(new Genre("Rogue Like"));
			genres.add(new Genre("Aventure"));
			genres.add(new Genre("MMO"));
			genres.add(new Genre("MOBA"));
			genres.add(new Genre("Battle Royal"));
			genres.add(new Genre("Kusoge"));
			genreDao.saveAll(genres);
		}
	}
	
	/**
	 * Méthode d'ajout de 100 jeux avec de fausses donnees en base de
	 * données
	 */
	private void ajouterJeux() {
		if (jeuDao.count() == 0) {
			for (int i = 1; i < 31; i++) {
				List<Plateforme> plateformesJeu = new ArrayList<>();
				for (int y = 1; y <= 4; y++) {
					plateformesJeu.add(plateformes.get(random.nextInt(plateformes.size())));
				}
				
				jeux.add(new Jeu("Mario " + i,
						"description random",
						faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						"565qmw2gbid81.jpg",
						moderateurs.get(random.nextInt(moderateurs.size())),
						modeleEconomiques.get(random.nextInt(modeleEconomiques.size())),
						plateformesJeu,
						editeurs.get(random.nextInt(editeurs.size())),
						genres.get(random.nextInt(genres.size())),
						classifications.get(random.nextInt(classifications.size()))
						));
			}
			jeuDao.saveAll(jeux);
		}
	}

	/**
	 * Méthode d'ajout de 100 joueurs.euses avec de fausses donnees en base de
	 * données
	 */
	private void ajouterJoueurs() {
		if (joueurDao.count() == 0) {
			for (int i = 1; i < 101; i++) {
				joueurs.add(new Joueur(faker.name().username(), faker.internet().emailAddress(),
						faker.internet().password(),
						faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
			}
			joueurDao.saveAll(joueurs);
		}
	}

	/**
	 * Ajout des modèles économiques
	 */
	private void ajouterModeleEconomiques() {
		if (modeleEconomiqueDao.count() == 0) {
			modeleEconomiques.add(new ModeleEconomique("F2P"));
			modeleEconomiques.add(new ModeleEconomique("P2P"));
			modeleEconomiques.add(new ModeleEconomique("P2W"));
			modeleEconomiques.add(new ModeleEconomique("Gatcha"));
			modeleEconomiqueDao.saveAll(modeleEconomiques);
		}
	}

	/**
	 * Méthode d'ajout d'une modératrice par défaut en base de données
	 */
	private void ajouterModerateurs() {
		if (moderateurDao.count() == 0) {
			moderateurs.add(new Moderateur("nana", "blast@hb.com", "12345", "0102030405"));
			moderateurs.add(new Moderateur("franck", "f.q@hb.com", "12345", "0203040506"));
			moderateurs.add(new Moderateur("laurent", "l.c@hb.com", "12345", "0304050607"));
			moderateurDao.saveAll(moderateurs);
		}
	}

	/**
	 * Ajout des plateformes
	 */
	private void ajouterPlateformes() {
		if (plateformeDao.count() == 0) {
			plateformes.add(new Plateforme("PC"));
			plateformes.add(new Plateforme("Mobile"));
			plateformes.add(new Plateforme("Playstation"));
			plateformes.add(new Plateforme("Playstation 2"));
			plateformes.add(new Plateforme("Playstation 3"));
			plateformes.add(new Plateforme("Playstation 4"));
			plateformes.add(new Plateforme("Playstation 5"));
			plateformes.add(new Plateforme("Atari ST"));
			plateformes.add(new Plateforme("Xbox"));
			plateformes.add(new Plateforme("Xbox 360"));
			plateformes.add(new Plateforme("Xbox One"));
			plateformes.add(new Plateforme("Xbox Series"));
			plateformes.add(new Plateforme("MegaDrive"));
			plateformes.add(new Plateforme("Dreamcast"));
			plateformes.add(new Plateforme("Wii"));
			plateformes.add(new Plateforme("GameCube"));
			plateformes.add(new Plateforme("NES"));
			plateformes.add(new Plateforme("SNES"));
			plateformes.add(new Plateforme("N64"));
			plateformes.add(new Plateforme("Gameboy"));
			plateformes.add(new Plateforme("Gameboy Advance"));
			plateformes.add(new Plateforme("DS"));
			plateformes.add(new Plateforme("PSP"));
			plateformes.add(new Plateforme("Vita"));
			plateformes.add(new Plateforme("GameGear"));
			plateformes.add(new Plateforme("Atari 2600"));
			plateformes.add(new Plateforme("Vectrex"));
			plateformes.add(new Plateforme("ColecoVision"));
			plateformes.add(new Plateforme("GameWatch"));
			plateformes.add(new Plateforme("Commodore64"));
			plateformes.add(new Plateforme("Amiga"));
			plateformes.add(new Plateforme("Amstrad CPC"));
			plateformes.add(new Plateforme("SteamDeck"));
			plateformes.add(new Plateforme("PlayDate"));
			plateformes.add(new Plateforme("Ouya"));
			plateformeDao.saveAll(plateformes);
		}
	}

}
