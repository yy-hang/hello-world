package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.service.UserService;

@WebServlet("/getuserlx")
public class getUserLx extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getUserLx() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId =request.getParameter("userId");
		UserService service =new UserService();
		response.setContentType("text/html;chaset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(service.getUserLx(userId));
	}

}
