package fr.humanbooster.cap_entreprise.business;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Moderateur extends Utilisateur {

	@NotBlank
	private String numeroDeTelephone;

	public Moderateur(String pseudo, String email, String motDePasse, String numeroDeTelephone) {
		super(pseudo, email, motDePasse);
		this.numeroDeTelephone = numeroDeTelephone;
	}

}