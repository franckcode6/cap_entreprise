package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Plateforme;

public interface PlateformeDao extends JpaRepository<Plateforme, Long> {

}
