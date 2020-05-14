package space.zhdanov.laboratory.carshop.services;

import space.zhdanov.laboratory.carshop.entities.domain.Mark;
import space.zhdanov.laboratory.carshop.repositories.MarkRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MarkService extends CrudService<Mark, Long> {

    @Inject
    public MarkService(MarkRepository markRepository) { //}, ItemRepository itemRepository) {
        super(markRepository);
    }
}
