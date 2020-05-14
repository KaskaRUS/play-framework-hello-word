package space.zhdanov.laboratory.carshop.services;

import space.zhdanov.laboratory.carshop.entities.domain.Item;
import space.zhdanov.laboratory.carshop.repositories.ItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemService extends CrudService<Item, Long> {

    @Inject
    public ItemService(ItemRepository itemRepository) {
        super(itemRepository);
    }

}
