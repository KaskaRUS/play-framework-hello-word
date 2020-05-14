package space.zhdanov.laboratory.carshop.contollers.v1;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import space.zhdanov.laboratory.carshop.entities.dto.v1.ItemDTO;
import space.zhdanov.laboratory.carshop.services.ItemService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ItemController extends Controller {

    private final ItemService itemService;

    @Inject
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    public Result getAllItems() {
        final List<ItemDTO> all = itemService.findAll().stream().map(ItemDTO::new).collect(Collectors.toList());
        return ok(Json.toJson(all));
    }

    public Result getItem(String idArg) {
        final long id = Long.parseLong(idArg);
        return itemService.findById(id)
                .map(ItemDTO::new)
                .map(Json::toJson)
                .map(Results::ok)
                .orElseGet(Results::notFound);
    }

    public Result create(Http.Request request) {
        final ItemDTO dto = Json.fromJson(request.body().asJson(), ItemDTO.class);
        final ItemDTO created = new ItemDTO(itemService.save(dto.toDomain()));
        return ok(Json.toJson(created));
    }

    public Result put(Http.Request request, String idArg) {
        final long id = Long.parseLong(idArg);
        final ItemDTO dto = Json.fromJson(request.body().asJson(), ItemDTO.class);
        dto.setId(id);
        final ItemDTO created = new ItemDTO(itemService.save(dto.toDomain()));
        return ok(Json.toJson(created));
    }

    public Result delete(String idArg) {
        final long id = Long.parseLong(idArg);
        itemService.deleteById(id);
        return noContent();
    }
}
