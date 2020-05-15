package space.zhdanov.laboratory.carshop.exceptions;


import static play.mvc.Http.Status.INTERNAL_SERVER_ERROR;

@ResponseStatus(code = INTERNAL_SERVER_ERROR)
public class CreatedEntityException extends RuntimeException {
    public CreatedEntityException() {
        super("Fail create entity");
    }
}
