package space.zhdanov.laboratory.carshop.exceptions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static play.mvc.Http.Status.INTERNAL_SERVER_ERROR;

@Retention(RUNTIME)
@Target({TYPE})
public @interface ResponseStatus {
    int code() default INTERNAL_SERVER_ERROR;

    String message() default "";
}
