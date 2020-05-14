package space.zhdanov.laboratory.carshop.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    List<T> findAll();

    Optional<T> findById(K id);

    void update(T entity);

    long insert(T entity);

    K lastInsertId();

    void deleteById(K id);
}
