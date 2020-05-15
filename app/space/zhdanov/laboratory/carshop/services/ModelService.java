package space.zhdanov.laboratory.carshop.services;

import space.zhdanov.laboratory.carshop.contexts.ModelExecutionContext;
import space.zhdanov.laboratory.carshop.entities.domain.Model;
import space.zhdanov.laboratory.carshop.repositories.ModelRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ModelService extends CrudService<Model, Long> {

    @Inject
    public ModelService(ModelRepository modelRepository, ModelExecutionContext context) {
        super(modelRepository, context);
    }
}
