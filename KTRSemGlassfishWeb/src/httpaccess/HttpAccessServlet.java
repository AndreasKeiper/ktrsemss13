package httpaccess;

import helper.SerializationHelper;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import akkaenvironment.Actorenvironment;

/**
 * Three types of http-POST-messages: 1. Props 2. msg + Actorid
 */
@WebServlet("/HttpAccessServlet")
public class HttpAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "ejb/Actorenvironment")
	private Actorenvironment actorsys;

	public HttpAccessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().print(actorsys.test());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mime = request.getContentType();
		if (mime.contentEquals("akka/props")) {

		} else if (mime.contentEquals("akka/msg")) {
			String actorhash = (String) request.getAttribute("id");
			byte[] buffer = new byte[request.getContentLength()];
			request.getInputStream().read(buffer);
			Object result = null;
			try {
				Object message = SerializationHelper.deserialize(buffer);
				result = actorsys.sendMessage(actorhash, message, 60);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result != null) {
				response.getOutputStream().write(
						SerializationHelper.serialize(result));
			} else {
				response.getOutputStream().print("Error");
			}

		}
	}

}
