package fr.humanbooster.cap_entreprise.business;

import java.time.LocalDate;
import java.util.Set;

/**
 * 
 */
public class Jeu {

    /**
     * Default constructor
     */
    public Jeu() {
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private LocalDate dateSortie;

    /**
     * 
     */
    private String Image;

    /**
     * 
     */
    private Modérateur moderateur;







    /**
     * 
     */
    private ModeleEconomique modeleEconomique;

    /**
     * 
     */
    private Set<Plateforme> plateformes;

    /**
     * 
     */
    private Editeur editeur;

    /**
     * 
     */
    private Genre genre;

    /**
     * 
     */
    private Classification classification;

    /**
     * 
     */
    private Set<Avis> avis;

}