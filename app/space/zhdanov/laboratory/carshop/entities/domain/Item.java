package space.zhdanov.laboratory.carshop.entities.domain;

public class Item implements Identity<Long> {
    private Long id;
    private Mark mark;
//    private LocalDate productionDate;


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
}
