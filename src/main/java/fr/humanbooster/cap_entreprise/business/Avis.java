package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String description;

	private LocalDateTime dateEnvoi;

	@NotBlank
	private float note;

	private LocalDateTime dateModeration;

	@ManyToOne
	private Moderateur moderateur;

	@ManyToOne
	private Joueur joueur;

	@NotNull
	@ManyToOne
	private Jeu jeu;

	@Override
	public String toString() {
		return "Avis [id=" + id + ", description=" + description + ", dateEnvoi=" + dateEnvoi + ", note=" + note
				+ ", dateModeration=" + dateModeration + "]";
	}

}