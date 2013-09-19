package akkaenvironment.wrapper;

import akka.actor.Props;

/**
 * Wrapperklasse um die gekapselten Daten mit einer Verfallszeit zu versehen.
 */
public class PropsPreAvailableWrapper {

	private Props props;
	private String actorName;
	private String description;

	public PropsPreAvailableWrapper(String actorName, String description,
			Props props) {
		this.props = props;
		this.actorName = actorName;
		this.description = description;
	}

	public Props getProps() {
		return props;
	}

	public void setProps(Props props) {
		this.props = props;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
