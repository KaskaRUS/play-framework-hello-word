package space.zhdanov.laboratory.carshop.contollers.v1;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import space.zhdanov.laboratory.carshop.contollers.actions.RestAction;
import space.zhdanov.laboratory.carshop.entities.dto.v1.ModelDTO;
import space.zhdanov.laboratory.carshop.services.ModelService;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@With(RestAction.class)
public class ModelController extends Controller {

    private final ModelService modelService;
    private final HttpExecutionContext hc;

    @Inject
    public ModelController(ModelService modelService, HttpExecutionContext hc) {
        this.modelService = modelService;
        this.hc = hc;
    }

    public CompletionStage<Result> getAllModel() {
        return modelService.findAll().thenApplyAsync(
                list -> {
                    final List<ModelDTO> dto = list.stream().map(ModelDTO::new).collect(Collectors.toList());
                    return ok(Json.toJson(dto));
                },
                hc.current()
        );
    }

    public CompletionStage<Result> getModel(String idArg) {
        final long id = Long.parseLong(idArg);

        return modelService.findById(id).thenApplyAsync(
                model -> ok(Json.toJson(new ModelDTO(model))),
                hc.current()
        );
    }

    public CompletionStage<Result> create(Http.Request request) {
        final ModelDTO dto = Json.fromJson(request.body().asJson(), ModelDTO.class);
        return modelService.save(dto.toDomain()).thenApplyAsync(
                model -> ok(Json.toJson(new ModelDTO(model))),
                hc.current()
        );
    }

    public CompletionStage<Result> put(Http.Request request, String idArg) {
        final long id = Long.parseLong(idArg);
        final ModelDTO dto = Json.fromJson(request.body().asJson(), ModelDTO.class);
        dto.setId(id);
        return modelService.save(dto.toDomain()).thenApplyAsync(
                model -> ok(Json.toJson(new ModelDTO(model))),
                hc.current()
        );
    }

    public CompletionStage<Result> delete(String idArg) {
        final long id = Long.parseLong(idArg);

        return modelService.deleteById(id).thenApplyAsync(
                nothing -> ok(),
                hc.current()
        );
    }
}
