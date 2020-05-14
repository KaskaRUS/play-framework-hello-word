package space.zhdanov.laboratory.carshop.contollers.v1;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import space.zhdanov.laboratory.carshop.entities.dto.v1.ItemDTO;
import space.zhdanov.laboratory.carshop.services.ItemService;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class ItemController extends Controller {

    private final ItemService itemService;
    private final HttpExecutionContext hc;

    @Inject
    public ItemController(ItemService itemService, HttpExecutionContext hc) {
        this.itemService = itemService;
        this.hc = hc;
    }

    public CompletionStage<Result> getAllItems() {
        return itemService.findAll().thenApplyAsync(
                list -> {
                    final List<ItemDTO> dto = list.stream().map(ItemDTO::new).collect(Collectors.toList());
                    return ok(Json.toJson(dto));
                },
                hc.current()
        );
    }

    public CompletionStage<Result> getItem(String idArg) {
        final long id = Long.parseLong(idArg);

        return itemService.findById(id).thenApplyAsync(
                mark -> mark.map(ItemDTO::new)
                        .map(Json::toJson)
                        .map(Results::ok).orElseGet(Results::notFound),
                hc.current()
        );
    }

    public CompletionStage<Result> create(Http.Request request) {
        final ItemDTO dto = Json.fromJson(request.body().asJson(), ItemDTO.class);
        return itemService.save(dto.toDomain()).thenApplyAsync(
                mark -> ok(Json.toJson(new ItemDTO(mark))),
                hc.current()
        );
    }

    public CompletionStage<Result> put(Http.Request request, String idArg) {
        final long id = Long.parseLong(idArg);
        final ItemDTO dto = Json.fromJson(request.body().asJson(), ItemDTO.class);
        dto.setId(id);
        return itemService.save(dto.toDomain()).thenApplyAsync(
                mark -> ok(Json.toJson(new ItemDTO(mark))),
                hc.current()
        );
    }

    public CompletionStage<Result> delete(String idArg) {
        final long id = Long.parseLong(idArg);

        return itemService.deleteById(id).thenApplyAsync(
                nothing -> ok(),
                hc.current()
        );
    }
}
