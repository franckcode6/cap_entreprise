package fr.humanbooster.cap_entreprise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Avis;

public interface AvisDao extends JpaRepository<Avis, Long>{
	
	List<Avis> findByModerateurIsNotNull();

}
