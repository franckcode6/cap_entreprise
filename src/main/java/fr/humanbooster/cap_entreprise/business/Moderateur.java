package fr.humanbooster.cap_entreprise.business;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Moderateur extends Utilisateur {

	@NotBlank
	private String numéroDeTelephone;

	public Moderateur(String pseudo, String email, String motDePasse, String numéroDeTelephone) {
		super(pseudo, email, motDePasse);
		this.numéroDeTelephone = numéroDeTelephone;
	}

}