package org.iut.mastermind.domain;

import org.iut.mastermind.domain.partie.Joueur;
import org.iut.mastermind.domain.partie.Partie;
import org.iut.mastermind.domain.partie.PartieRepository;
import org.iut.mastermind.domain.partie.ResultatPartie;
import org.iut.mastermind.domain.proposition.Reponse;
import org.iut.mastermind.domain.tirage.MotsRepository;
import org.iut.mastermind.domain.tirage.ServiceNombreAleatoire;
import org.iut.mastermind.domain.tirage.ServiceTirageMot;
import java.util.Optional;

/**
 * Classe principale pour gérer le jeu Mastermind.
 */
public class Mastermind {
    private final PartieRepository partieRepository;
    private final ServiceTirageMot serviceTirageMot;

    /**
     * Constructeur pour initialiser le jeu avec les repositories nécessaires.
     *
     * @param pr le repository des parties
     * @param mr le repository des mots
     * @param na le service de nombre aléatoire
     */
    public Mastermind(PartieRepository pr, MotsRepository mr, ServiceNombreAleatoire na) {
        this.partieRepository = pr;
        this.serviceTirageMot = new ServiceTirageMot(mr, na);
    }

    /**
     * Crée une nouvelle partie pour le joueur.
     *
     * @param joueur le joueur
     * @return true si une nouvelle partie est créée, false si une partie est déjà en cours
     */
    public boolean nouvellePartie(Joueur joueur) {
        if (isJeuEnCours(partieRepository.getPartieEnregistree(joueur))) {
            return false;
        } else {
            Partie partie = Partie.create(joueur, serviceTirageMot.tirageMotAleatoire());
            partieRepository.create(partie);
            return true;
        }
    }

    /**
     * Évalue le mot proposé par le joueur.
     *
     * @param joueur le joueur
     * @param motPropose le mot proposé
     * @return le résultat de la partie
     */
    public ResultatPartie evaluation(Joueur joueur, String motPropose) {
        Optional<Partie> partieEnCours = partieRepository.getPartieEnregistree(joueur);
        if (!isJeuEnCours(partieEnCours)) {
            return ResultatPartie.ERROR;
        }
        return calculeResultat(partieEnCours.get(), motPropose);
    }

    /**
     * Calcule le résultat du mot proposé pour le tour de jeu.
     *
     * @param partie la partie en cours
     * @param motPropose le mot proposé
     * @return le résultat de la partie
     */
    private ResultatPartie calculeResultat(Partie partie, String motPropose) {
        Reponse reponse = partie.tourDeJeu(motPropose);
        partieRepository.update(partie);
        return ResultatPartie.create(reponse, partie.isTerminee());
    }

    /**
     * Vérifie si une partie est en cours.
     *
     * @param partieEnCours la partie en cours
     * @return true si une partie est en cours, false sinon
     */
    private boolean isJeuEnCours(Optional<Partie> partieEnCours) {
        return partieEnCours.isPresent() && !partieEnCours.get().isTerminee();
    }
}