package webservice;

import javax.xml.ws.WebFault;

/**
 * Hilfsexception für die Fehlerübertragung per JAX-WS
 */
@WebFault(name = "serverFault")
public class ServerFault extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3921951829667108983L;

	public ServerFault() {
		super();
	}

	public ServerFault(String message) {
		super(message);
	}

	public ServerFault(Throwable cause) {
		super(cause);
	}

	public ServerFault(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerFault(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
