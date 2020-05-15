package space.zhdanov.laboratory.carshop.contollers.v1;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import space.zhdanov.laboratory.carshop.contollers.actions.RestAction;
import space.zhdanov.laboratory.carshop.entities.dto.v1.MarkDTO;
import space.zhdanov.laboratory.carshop.services.MarkService;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@With(RestAction.class)
public class MarkController extends Controller {

    private final MarkService markService;
    private final HttpExecutionContext hc;

    @Inject
    public MarkController(MarkService markService, HttpExecutionContext hc) {
        this.markService = markService;
        this.hc = hc;
    }

    public CompletionStage<Result> getAllMarks() {
        return markService.findAll().thenApplyAsync(
                list -> {
                    final List<MarkDTO> dto = list.stream().map(MarkDTO::new).collect(Collectors.toList());
                    return ok(Json.toJson(dto));
                },
                hc.current()
        );
    }

    public CompletionStage<Result> getMark(String idArg) {
        final long id = Long.parseLong(idArg);

        return markService.findById(id).thenApplyAsync(
                mark -> ok(Json.toJson(new MarkDTO(mark))),
                hc.current()
        );
    }

    public CompletionStage<Result> create(Http.Request request) {
        final MarkDTO dto = Json.fromJson(request.body().asJson(), MarkDTO.class);
        return markService.save(dto.toDomain()).thenApplyAsync(
                mark -> ok(Json.toJson(new MarkDTO(mark))),
                hc.current()
        );
    }

    public CompletionStage<Result> put(Http.Request request, String idArg) {
        final long id = Long.parseLong(idArg);
        final MarkDTO dto = Json.fromJson(request.body().asJson(), MarkDTO.class);
        dto.setId(id);
        return markService.save(dto.toDomain()).thenApplyAsync(
                mark -> ok(Json.toJson(new MarkDTO(mark))),
                hc.current()
        );
    }

    public CompletionStage<Result> delete(String idArg) {
        final long id = Long.parseLong(idArg);

        return markService.deleteById(id).thenApplyAsync(
                nothing -> ok(),
                hc.current()
        );
    }
}
