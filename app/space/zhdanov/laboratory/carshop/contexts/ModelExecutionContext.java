package space.zhdanov.laboratory.carshop.contexts;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class ModelExecutionContext extends CustomExecutionContext {

    @Inject
    public ModelExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "model.service");
    }
}