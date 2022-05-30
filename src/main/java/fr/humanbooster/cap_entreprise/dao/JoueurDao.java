package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Joueur;

public interface JoueurDao extends JpaRepository<Joueur, Long> {

}
