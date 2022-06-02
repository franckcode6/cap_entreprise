package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Avis;

public interface AvisDao extends JpaRepository<Avis, Long> {

	// requète par dérivation qui nous permet de retrouver les avis qui ont été
	// modérés et que l'on va paginer
	Page<Avis> findAllByModerateurPseudoContaining(String pseudo, Pageable pageable);

	// requète par dérivation qui nous permet de retrouver les avis qui n'ont pas
	// été modéré et que l'on va paginer
	Page<Avis> findAllByModerateurPseudoNull(Pageable pageable);

}
