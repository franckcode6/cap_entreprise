package fr.humanbooster.cap_entreprise.business;

import java.util.List;

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
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nom;

	@OneToMany(mappedBy = "genre")
	private List<Jeu> jeux;

	public Genre(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + "]";
	}

}