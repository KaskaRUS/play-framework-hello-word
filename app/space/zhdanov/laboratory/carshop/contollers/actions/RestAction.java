package space.zhdanov.laboratory.carshop.contollers.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.libs.concurrent.Futures;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import space.zhdanov.laboratory.carshop.entities.dto.v1.ExceptionMessage;
import space.zhdanov.laboratory.carshop.exceptions.ResponseStatus;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static play.mvc.Http.Status.GATEWAY_TIMEOUT;
import static play.mvc.Http.Status.NOT_ACCEPTABLE;

public class RestAction extends play.mvc.Action.Simple {
    private static final Logger logger = LoggerFactory.getLogger("application.PostAction");

    private final HttpExecutionContext ec;
    private final Futures futures;

    @Singleton
    @Inject
    public RestAction(HttpExecutionContext ec, Futures futures) {
        this.ec = ec;
        this.futures = futures;
    }

    public CompletionStage<Result> call(Http.Request request) {
        logger.trace("call: request = {}", request);

        return request.contentType().filter("application/json"::equals)
                .map(contentType -> futures.timeout(doCall(request), 1L, TimeUnit.SECONDS).exceptionally(e -> {
                    return Results.status(GATEWAY_TIMEOUT, Json.toJson(
                            new ExceptionMessage("Timeout of response", "", System.currentTimeMillis())
                    ));
                }))
                .orElseGet(() -> completedFuture(
                        status(NOT_ACCEPTABLE, Json.toJson(
                                new ExceptionMessage("Not available content type", "", System.currentTimeMillis())
                        ))
                ));
    }

    private CompletionStage<Result> doCall(Http.Request request) {
        return delegate.call(request).handleAsync((result, e) -> {
            if (e != null) {
                if (e instanceof CompletionException) {
                    final Throwable exception = e.getCause();
                    final ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
                    if (responseStatus == null) {
                        return internalServerError(Json.toJson(new ExceptionMessage(exception, null)));
                    } else {
                        return status(responseStatus.code(), Json.toJson(
                                new ExceptionMessage(exception, !responseStatus.message().isEmpty() ? responseStatus.message() : null)
                        ));
                    }
                } else {
                    return internalServerError(Json.toJson(new ExceptionMessage(e, null)));
                }

            } else {
                return result;
            }
        }, ec.current());
    }
}
