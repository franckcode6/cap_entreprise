package fr.humanbooster.cap_entreprise.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@NotBlank
	protected String pseudo;

	@NotBlank
	protected String email;

	@NotBlank
	protected String motDePasse;

	public Utilisateur(String pseudo, String email, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.motDePasse = motDePasse;
	}

}