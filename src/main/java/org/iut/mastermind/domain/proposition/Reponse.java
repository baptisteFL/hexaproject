package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

/**
 * Représente la réponse pour une proposition dans le jeu Mastermind.
 */
public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();

    /**
     * Constructeur pour initialiser la réponse avec le mot secret.
     *
     * @param mot le mot secret
     */
    public Reponse(String mot) {
        this.motSecret = mot;
    }

    /**
     * Récupère la lettre à la position spécifiée dans le résultat.
     *
     * @param position la position de la lettre
     * @return la lettre à la position spécifiée
     */
    public Lettre lettre(int position) {
        return resultat.get(position);
    }

    /**
     * Construit le résultat en analysant chaque lettre du mot proposé.
     *
     * @param essai le mot proposé
     */
    public void compare(String essai) {
        char[] caracteres = essai.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            resultat.add(evaluationCaractere(caracteres[i], i));
        }
    }

    /**
     * Vérifie si toutes les lettres sont placées correctement.
     *
     * @return true si toutes les lettres sont placées, false sinon
     */
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(Lettre.PLACEE::equals);
    }

    /**
     * Retourne la liste des lettres du résultat.
     *
     * @return une liste non modifiable des lettres du résultat
     */
    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    /**
     * Évalue le statut du caractère courant.
     *
     * @param carCourant le caractère courant
     * @param position la position du caractère
     * @return le statut du caractère
     */
    private Lettre evaluationCaractere(char carCourant, int position) {
        if (motSecret.indexOf(carCourant) != -1) {
            if (motSecret.charAt(position) == carCourant) {
                return Lettre.PLACEE;
            } else {
                return Lettre.NON_PLACEE;
            }
        } else {
            return Lettre.INCORRECTE;
        }
    }
}