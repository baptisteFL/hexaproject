package org.iut.mastermind.domain.proposition;

/**
 * Représente le mot secret dans le jeu Mastermind.
 */
public class MotSecret {
    private final String motSecret;

    /**
     * Constructeur pour initialiser le mot secret.
     *
     * @param mot le mot secret
     */
    public MotSecret(String mot) {
        this.motSecret = mot;
    }

    /**
     * Compare le mot essayé avec le mot secret et retourne le résultat.
     *
     * @param essai le mot essayé
     * @return la réponse de la comparaison
     */
    public Reponse compareProposition(String essai) {
        Reponse reponse = new Reponse(this.motSecret);
        reponse.compare(essai);
        return reponse;
    }
}