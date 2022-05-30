package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDateTime;

/**
 * 
 */
public class Avis {

    /**
     * Default constructor
     */
    public Avis() {
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private LocalDateTime dateEnvoi;

    /**
     * 
     */
    private float note;

    /**
     * 
     */
    private LocalDateTime dateModeration;

    /**
     * 
     */
    private Modérateur moderateur;

    /**
     * 
     */
    private Joueur joueur;

    /**
     * 
     */
    private Jeu jeu;

}