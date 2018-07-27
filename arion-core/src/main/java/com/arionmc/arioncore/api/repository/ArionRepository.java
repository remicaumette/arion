package com.arionmc.arioncore.api.repository;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface ArionRepository<ID, T> {
    CompletableFuture<Collection<T>> findAll();

    CompletableFuture<T> findOne(ID id);

    CompletableFuture<T> create(T object);

    CompletableFuture<T> update(T object);

    CompletableFuture<T> createOrUpdate(T object);

    CompletableFuture<Boolean> delete(T object);
}
