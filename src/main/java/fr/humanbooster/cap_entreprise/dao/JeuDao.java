package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Jeu;

public interface JeuDao extends JpaRepository<Jeu, Long> {

}
