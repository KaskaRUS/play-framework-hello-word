package space.zhdanov.laboratory.carshop.contexts;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class MarkExecutionContext extends CustomExecutionContext {

    @Inject
    public MarkExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "mark.service");
    }
}