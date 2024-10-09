package org.iut.mastermind.domain.tirage;

/**
 * Service pour tirer un mot aléatoire dans le jeu Mastermind.
 */
public class ServiceTirageMot {
    private final MotsRepository repository;
    private final ServiceNombreAleatoire nbAleatoire;

    /**
     * Constructeur pour initialiser le service avec un repository de mots et un service de nombre aléatoire.
     *
     * @param repository le repository de mots
     * @param nbAleatoire le service de nombre aléatoire
     */
    public ServiceTirageMot(MotsRepository repository, ServiceNombreAleatoire nbAleatoire) {
        this.repository = repository;
        this.nbAleatoire = nbAleatoire;
    }

    /**
     * Retourne le mot à l'indice renvoyé par le service de nombre aléatoire.
     *
     * @return un mot aléatoire
     */
    public String tirageMotAleatoire() {
        return repository.getMotByIndex(nbAleatoire.next(repository.nbMaxMots()));
    }
}