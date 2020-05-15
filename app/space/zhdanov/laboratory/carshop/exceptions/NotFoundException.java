package space.zhdanov.laboratory.carshop.exceptions;

import static play.mvc.Http.Status.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(Object id) {
        super("Not found entity with id " + id);
    }
}
