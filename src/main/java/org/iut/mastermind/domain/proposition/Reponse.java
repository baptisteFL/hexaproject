package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        char[] caracteres = essai.toCharArray();
        char carCourant;
        for (int i = 0; i < caracteres.length; i++) {
            position = i;
            carCourant = caracteres[i];
            resultat.add(evaluationCaractere(carCourant));
        }
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(Lettre.PLACEE::equals);
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant) {
        if (estPresent(carCourant)) {
            if (estPlace(carCourant)) {
                position++;
                return Lettre.PLACEE;
            } else {
                return Lettre.NON_PLACEE;
            }
        } else {
            return Lettre.INCORRECTE;
        }
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return motSecret.contains(String.valueOf(carCourant));
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        return motSecret.charAt(position) == carCourant;
    }
}
