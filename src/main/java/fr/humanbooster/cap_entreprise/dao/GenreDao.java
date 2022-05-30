package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {

}
