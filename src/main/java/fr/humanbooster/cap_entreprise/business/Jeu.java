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

	@NotBlank
	@PastOrPresent
	private LocalDate dateSortie;

	private String Image;

	@ManyToOne
	private Moderateur moderateur;

	@ManyToOne
	@NotNull
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

	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateSortie=" + dateSortie
				+ ", Image=" + Image + "]";
	}

}