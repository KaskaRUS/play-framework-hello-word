package space.zhdanov.laboratory.carshop.entities.dto.v1;

import space.zhdanov.laboratory.carshop.entities.domain.Model;

import java.time.LocalDate;

public class ModelDTO {
    private Long id;
    private String name;
    private LocalDate productionStartDate;
    private LocalDate productionFinishDate;

    public ModelDTO(Long id, String name, LocalDate productionStartDate, LocalDate productionFinishDate) {
        this.id = id;
        this.name = name;
        this.productionStartDate = productionStartDate;
        this.productionFinishDate = productionFinishDate;
    }

    public ModelDTO() {
    }

    public ModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.productionFinishDate = model.getProductionFinishDate();
        this.productionStartDate = model.getProductionStartDate();
    }

    public Model toDomain() {
        return new Model(
                id,
                name,
                productionStartDate,
                productionFinishDate
        );
    }

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
}
