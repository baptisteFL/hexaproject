package org.iut.mastermind.domain.partie;

import org.iut.mastermind.domain.proposition.MotSecret;
import org.iut.mastermind.domain.proposition.Reponse;

/**
 * Représente une session de jeu dans le jeu Mastermind.
 */
public class Partie {
    private static final int NB_ESSAIS_MAX = 5;
    private final Joueur joueur;
    private final String motADeviner;
    private int nbEssais;
    private boolean partieTerminee;

    private Partie(Joueur joueur, String motADeviner, int nbEssais, boolean partieTerminee) {
        this.joueur = joueur;
        this.motADeviner = motADeviner;
        this.nbEssais = nbEssais;
        this.partieTerminee = partieTerminee;
    }

    /**
     * Crée une nouvelle session de jeu avec zéro essais et non terminée.
     *
     * @param joueur le joueur
     * @param motADeviner le mot à deviner
     * @return une nouvelle session de jeu
     */
    public static Partie create(Joueur joueur, String motADeviner) {
        return new Partie(joueur, motADeviner, 0, false);
    }

    /**
     * Crée une nouvelle session de jeu avec un nombre d'essais spécifié et non terminée.
     *
     * @param joueur le joueur
     * @param motADeviner le mot à deviner
     * @param nbEssais le nombre d'essais
     * @return une nouvelle session de jeu
     */
    public static Partie create(Joueur joueur, String motADeviner, int nbEssais) {
        return new Partie(joueur, motADeviner, nbEssais, false);
    }


    public Joueur getJoueur() {
        return this.joueur;
    }

    public int getNbEssais() {
        return this.nbEssais;
    }

    public String getMot() {
        return this.motADeviner;
    }

    /**
     * Traite un tour de jeu avec le mot proposé.
     *
     * @param motPropose le mot proposé
     * @return la réponse du tour
     */
    public Reponse tourDeJeu(String motPropose) {
        this.nbEssais++;
        this.verifieNbEssais();

        Reponse reponse = new Reponse(this.motADeviner);
        if (!this.partieTerminee) {
            reponse.compare(motPropose);
            if (reponse.lettresToutesPlacees()) this.done();
        }
        return reponse;
    }

    /**
     * Vérifie si le nombre maximum d'essais a été atteint.
     */
    private void verifieNbEssais() {
        if (this.nbEssais >= NB_ESSAIS_MAX) {
            this.done();
        }
    }

    /**
     * Vérifie si la session de jeu est terminée.
     * @return true si la session de jeu est terminée, false sinon
     */
    public boolean isTerminee() {
        return this.partieTerminee;
    }

    /**
     * Marque la session de jeu comme terminée.
     */
    public void done() {
        this.partieTerminee = true;
    }
}