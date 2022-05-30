package fr.humanbooster.cap_entreprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.cap_entreprise.business.ModeleEconomique;

public interface ModeleEconomiqueDao extends JpaRepository<ModeleEconomique, Long> {

}
