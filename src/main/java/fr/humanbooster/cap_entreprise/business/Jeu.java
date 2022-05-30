package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;

	private String description;

	private LocalDate dateSortie;

	private String Image;

	private Moderateur moderateur;

	private ModeleEconomique modeleEconomique;

	private List<Plateforme> plateformes;

	private Editeur editeur;

	private Genre genre;

	private Classification classification;

	private List<Avis> avis;

	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateSortie=" + dateSortie
				+ ", Image=" + Image + "]";
	}

}