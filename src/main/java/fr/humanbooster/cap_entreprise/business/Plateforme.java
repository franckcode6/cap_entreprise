package fr.humanbooster.cap_entreprise.business;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Plateforme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nom;

	@ManyToMany
	private List<Jeu> jeux;
	
	public Plateforme(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Plateforme [id=" + id + ", nom=" + nom + "]";
	}

}