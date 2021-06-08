package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;

@WebServlet("/getuserinfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 User user = (User)request.getSession().getAttribute("USER_INFO");
		 response.getWriter().write("{\"benUserId\":\""+user.getId()+"\"}");
	}

}
