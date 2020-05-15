package space.zhdanov.laboratory.carshop.entities.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return Objects.equals(id, mark.id) &&
                Objects.equals(name, mark.name) &&
                Objects.equals(country, mark.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
