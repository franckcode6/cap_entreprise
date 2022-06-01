package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Avis;

public interface AvisDao extends JpaRepository<Avis, Long>{
	
	Page<Avis> findAllByModerateurPseudoContaining(String pseudo, Pageable pageable);
	
	Page<Avis> findAllByModerateurPseudoNull(Pageable pageable);

}
