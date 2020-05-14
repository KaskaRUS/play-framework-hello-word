package space.zhdanov.laboratory.carshop.services;

import org.mybatis.guice.transactional.Transactional;
import space.zhdanov.laboratory.carshop.entities.domain.Identity;
import space.zhdanov.laboratory.carshop.exceptions.CreatedEntityException;
import space.zhdanov.laboratory.carshop.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<T extends Identity<K>, K> {
    private final CrudRepository<T, K> crudRepository;

    public CrudService(CrudRepository<T, K> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public List<T> findAll() {
        return crudRepository.findAll();
    }

    public Optional<T> findById(K id) {
        return crudRepository.findById(id);
    }

    @Transactional
    public T save(T entity) {
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

    public void deleteById(K id) {
        crudRepository.deleteById(id);
    }
}

