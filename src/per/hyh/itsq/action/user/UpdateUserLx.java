package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.service.UserService;

@WebServlet("/updateuserlx")
public class UpdateUserLx extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String qqLx=request.getParameter("qqlx");
		String weLx=request.getParameter("welx");
		String telLx=request.getParameter("tellx");
		String bkLx=request.getParameter("bklx");
		UserService service= new UserService();
		response.getWriter().write(service.updateUserLx(userId, weLx, qqLx, telLx, bkLx));
	}

}
