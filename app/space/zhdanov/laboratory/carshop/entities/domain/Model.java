package space.zhdanov.laboratory.carshop.entities.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Model implements Identity<Long> {
    private Long id;
    private String name;
    private LocalDate productionStartDate;
    private LocalDate productionFinishDate;

    public Model() {
    }

    public Model(Long id, String name, LocalDate productionStartDate, LocalDate productionFinishDate) {
        this.id = id;
        this.name = name;
        this.productionStartDate = productionStartDate;
        this.productionFinishDate = productionFinishDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductionStartDate() {
        return productionStartDate;
    }

    public void setProductionStartDate(LocalDate productionStartDate) {
        this.productionStartDate = productionStartDate;
    }

    public LocalDate getProductionFinishDate() {
        return productionFinishDate;
    }

    public void setProductionFinishDate(LocalDate productionFinishDate) {
        this.productionFinishDate = productionFinishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(name, model.name) &&
                Objects.equals(productionStartDate, model.productionStartDate) &&
                Objects.equals(productionFinishDate, model.productionFinishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productionStartDate, productionFinishDate);
    }
}
