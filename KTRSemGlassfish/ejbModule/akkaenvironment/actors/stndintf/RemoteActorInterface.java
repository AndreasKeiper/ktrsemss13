package akkaenvironment.actors.stndintf;

import scala.Option;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;
import akka.actor.ActorRef;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActorContext;

public interface RemoteActorInterface {

	public void onReceive(Object arg0) throws Exception;

	public UntypedActorContext getContext();

	public ActorRef getSelf();

	public ActorRef getSender();

	public void postRestart(Throwable reason);

	public void postStop();

	public void preRestart(Throwable reason, Option<Object> message);

	public void preStart();

	public PartialFunction<Object, BoxedUnit> receive();

	public SupervisorStrategy supervisorStrategy();

}
