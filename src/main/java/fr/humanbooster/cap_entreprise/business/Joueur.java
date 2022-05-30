package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Joueur extends Utilisateur {

	@Past
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