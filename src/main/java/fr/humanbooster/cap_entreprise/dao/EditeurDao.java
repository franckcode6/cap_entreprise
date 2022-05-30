package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Editeur;

public interface EditeurDao extends JpaRepository<Editeur, Long> {

}
