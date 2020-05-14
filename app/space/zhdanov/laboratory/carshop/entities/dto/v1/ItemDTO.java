package space.zhdanov.laboratory.carshop.entities.dto.v1;

import space.zhdanov.laboratory.carshop.entities.domain.Item;
import space.zhdanov.laboratory.carshop.entities.domain.Mark;

public class ItemDTO {
    private Long id;
    private Mark mark;

    public ItemDTO(Long id, Mark mark) {
        this.id = id;
        this.mark = mark;
    }

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.mark = item.getMark();
    }

    public Item toDomain() {
        return new Item(
                id,
                mark
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
