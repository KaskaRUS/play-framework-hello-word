package space.zhdanov.laboratory.carshop.entities.dto.v1;

import space.zhdanov.laboratory.carshop.entities.domain.Item;
import space.zhdanov.laboratory.carshop.entities.domain.Mark;
import space.zhdanov.laboratory.carshop.entities.domain.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemDTO {
    private Long id;
    private Mark mark;
    private Model model;
    private LocalDate productionDate;
    private BigDecimal cost;
    private Integer mileage;

    public ItemDTO(Long id, Mark mark, Model model, LocalDate productionDate, BigDecimal cost, Integer mileage) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.productionDate = productionDate;
        this.cost = cost;
        this.mileage = mileage;
    }

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.mark = item.getMark();
        this.model = item.getModel();
        this.cost = item.getCost();
        this.mileage = item.getMileage();
        this.productionDate = item.getProductionDate();
    }

    public Item toDomain() {
        return new Item(
                id,
                mark,
                model,
                productionDate,
                cost,
                mileage
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}
