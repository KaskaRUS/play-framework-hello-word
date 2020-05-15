package space.zhdanov.laboratory.carshop.entities.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Item implements Identity<Long> {
    private Long id;
    private Mark mark;
    private Model model;
    private LocalDate productionDate;
    private BigDecimal cost;
    private Integer mileage;

    public Item() {
    }

    public Item(Long id, Mark mark) {
        this.id = id;
        this.mark = mark;
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(mark, item.mark) &&
                Objects.equals(model, item.model) &&
                Objects.equals(productionDate, item.productionDate) &&
                Objects.equals(cost, item.cost) &&
                Objects.equals(mileage, item.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, productionDate, cost, mileage);
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}
