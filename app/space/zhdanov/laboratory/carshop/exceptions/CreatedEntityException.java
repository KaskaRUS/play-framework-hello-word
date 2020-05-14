package space.zhdanov.laboratory.carshop.exceptions;

public class CreatedEntityException extends RuntimeException {
    public CreatedEntityException() {
        super("Fail create entity");
    }
}
