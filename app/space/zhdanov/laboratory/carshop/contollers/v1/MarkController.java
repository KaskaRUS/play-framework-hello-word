package space.zhdanov.laboratory.carshop.contollers.v1;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import space.zhdanov.laboratory.carshop.entities.dto.v1.MarkDTO;
import space.zhdanov.laboratory.carshop.services.MarkService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class MarkController extends Controller {

    private final MarkService markService;

    @Inject
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    public Result getAllMarks() {
        final List<MarkDTO> all = markService.findAll().stream().map(MarkDTO::new).collect(Collectors.toList());
        return ok(Json.toJson(all));
    }

    public Result getMark(String idArg) {
        final long id = Long.parseLong(idArg);
        return markService.findById(id)
                .map(MarkDTO::new)
                .map(Json::toJson)
                .map(Results::ok)
                .orElseGet(Results::notFound);
    }

    public Result create(Http.Request request) {
        final MarkDTO dto = Json.fromJson(request.body().asJson(), MarkDTO.class);
        final MarkDTO created = new MarkDTO(markService.save(dto.toDomain()));
        return ok(Json.toJson(created));
    }

    public Result put(Http.Request request, String idArg) {
        final long id = Long.parseLong(idArg);
        final MarkDTO dto = Json.fromJson(request.body().asJson(), MarkDTO.class);
        dto.setId(id);
        final MarkDTO created = new MarkDTO(markService.save(dto.toDomain()));
        return ok(Json.toJson(created));
    }

    public Result delete(String idArg) {
        final long id = Long.parseLong(idArg);
        markService.deleteById(id);
        return noContent();
    }
}
