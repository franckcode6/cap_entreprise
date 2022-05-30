package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Moderateur;

public interface ModerateurDao extends JpaRepository<Moderateur, Long> {

}
