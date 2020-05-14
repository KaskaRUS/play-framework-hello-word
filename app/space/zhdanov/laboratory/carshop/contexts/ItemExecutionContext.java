package space.zhdanov.laboratory.carshop.contexts;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class ItemExecutionContext extends CustomExecutionContext {

    @Inject
    public ItemExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "item.service");
    }
}