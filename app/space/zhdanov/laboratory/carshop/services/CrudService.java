package space.zhdanov.laboratory.carshop.services;

import org.mybatis.guice.transactional.Transactional;
import play.libs.concurrent.CustomExecutionContext;
import space.zhdanov.laboratory.carshop.entities.domain.Identity;
import space.zhdanov.laboratory.carshop.exceptions.CreatedEntityException;
import space.zhdanov.laboratory.carshop.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public abstract class CrudService<T extends Identity<K>, K> {
    private final CrudRepository<T, K> crudRepository;
    private final CustomExecutionContext context;

    public CrudService(CrudRepository<T, K> crudRepository, CustomExecutionContext context) {
        this.crudRepository = crudRepository;
        this.context = context;
    }

    public CompletionStage<List<T>> findAll() {
        return CompletableFuture.supplyAsync(crudRepository::findAll, context);
    }

    public CompletionStage<Optional<T>> findById(K id) {
        return CompletableFuture.supplyAsync(() -> crudRepository.findById(id), context);
    }

    public CompletionStage<T> save(T entity) {
        return CompletableFuture.supplyAsync(() -> saveTransaction(entity), context);
    }

    @Transactional
    public T saveTransaction(T entity) {
        final K entityId;
        if (entity.getId() == null) {
            crudRepository.insert(entity);
            entityId = crudRepository.lastInsertId();
        } else {
            crudRepository.update(entity);
            entityId = entity.getId();
        }
        return crudRepository.findById(entityId).orElseThrow(CreatedEntityException::new);
    }


    public CompletionStage<Void> deleteById(K id) {
        return CompletableFuture.runAsync(() -> crudRepository.deleteById(id), context);
    }
}

