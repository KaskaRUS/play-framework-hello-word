package space.zhdanov.laboratory.carshop.entities.domain;

public class Mark implements Identity<Long> {
    private Long id;
    private String name;
    private String country;

    public Mark(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Mark() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
