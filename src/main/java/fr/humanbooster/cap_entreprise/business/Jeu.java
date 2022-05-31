package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nom;

	@NotBlank
	private String description;

	@NotNull
	@PastOrPresent
	private LocalDate dateSortie;

	private String image;

	@ManyToOne
	private Moderateur moderateur;

	@NotNull
	@ManyToOne
	private ModeleEconomique modeleEconomique;

	@ManyToMany
	private List<Plateforme> plateformes;

	@ManyToOne
	@NotNull
	private Editeur editeur;
	
	@ManyToOne
	@NotNull
	private Genre genre;

	@ManyToOne
	@NotNull
	private Classification classification;

	@OneToMany(mappedBy="jeu", cascade = CascadeType.REMOVE)
	private List<Avis> avis;
	
	public Jeu() {
		this.dateSortie = LocalDate.now();
	}
	
	public Jeu(String nom, String description, LocalDate dateSortie, String image, Moderateur moderateur,
            ModeleEconomique modeleEconomique, List<Plateforme> plateformes, Editeur editeur, Genre genre,
            Classification classification) {
        super();
        this.nom = nom;
        this.description = description;
        this.dateSortie = dateSortie;
        this.image = image;
        this.moderateur = moderateur;
        this.modeleEconomique = modeleEconomique;
        this.plateformes = plateformes;
        this.editeur = editeur;
        this.genre = genre;
        this.classification = classification;

    }

	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateSortie=" + dateSortie
				+ ", Image=" + image + "]";
	}

}