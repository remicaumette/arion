package com.arionmc.arioncore.api.repository;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * @param <ID> Le type de l'identifiant.
 * @param <T>  Le model objet de la table.
 */
public interface ArionRepository<ID, T> {
    /**
     * @return Retourne toutes les objets en base de données.
     */
    CompletableFuture<Collection<T>> findAll();

    /**
     * Cherche un objet par rapport a son identifiant.
     *
     * @param id L'identifiant.
     * @return L'objet.
     */
    CompletableFuture<T> findOne(ID id);

    /**
     * Créé l'objet en base donnée.
     *
     * @param object L'objet.
     * @return L'objet créé.
     */
    CompletableFuture<T> create(T object);

    /**
     * Mets à jour l'objet en base de donnée.
     *
     * @param object L'objet.
     * @return L'objet mis à jour.
     */
    CompletableFuture<T> update(T object);

    /**
     * Créé ou mets à jour l'objet s'il existe.
     *
     * @param object L'objet.
     * @return L'objet créé ou mis à jour.
     */
    CompletableFuture<T> createOrUpdate(T object);

    /**
     * Supprime l'objet de la base donnée.
     *
     * @param object L'objet.
     * @return Si l'objet a été supprimé.
     */
    CompletableFuture<Boolean> delete(T object);
}
