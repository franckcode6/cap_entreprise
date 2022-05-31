package fr.humanbooster.cap_entreprise.business;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nom;

	@OneToMany(mappedBy = "classification")
	private List<Jeu> jeux;
	
	public Classification(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Classification [id=" + id + ", nom=" + nom + "]";
	}

}