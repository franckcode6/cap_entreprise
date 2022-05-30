package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Avis;

public interface AvisDao extends JpaRepository<Avis, Long>{

}
