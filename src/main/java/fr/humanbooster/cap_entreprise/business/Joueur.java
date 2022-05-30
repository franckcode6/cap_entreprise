package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDate;
import java.util.Set;

/**
 * 
 */
public class Joueur extends Utilisateur {

    /**
     * Default constructor
     */
    public Joueur() {
    }

    /**
     * 
     */
    private LocalDate dateDeNaissance;

    /**
     * 
     */
    private Set<Avis> avis;

}