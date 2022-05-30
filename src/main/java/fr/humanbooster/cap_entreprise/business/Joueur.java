package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Joueur extends Utilisateur {

	@NotBlank
	private LocalDate dateDeNaissance;

	@OneToMany(mappedBy = "joueur")
	private List<Avis> avis;

	public Joueur(String pseudo, String email, String motDePasse, LocalDate dateDeNaissance) {
		super(pseudo, email, motDePasse);
		this.dateDeNaissance = dateDeNaissance;
	}

	@Override
	public String toString() {
		return "Joueur [dateDeNaissance=" + dateDeNaissance + "]";
	}

}