package space.zhdanov.laboratory.carshop.entities.dto.v1;

import space.zhdanov.laboratory.carshop.entities.domain.Mark;

public class MarkDTO {
    private Long id;
    private String name;
    private String country;

    public MarkDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public MarkDTO() {
    }

    public MarkDTO(Mark mark) {
        this.country = mark.getCountry();
        this.id = mark.getId();
        this.name = mark.getName();
    }

    public Mark toDomain() {
        return new Mark(
                id,
                name,
                country
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
