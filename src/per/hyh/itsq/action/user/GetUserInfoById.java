package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;
import per.hyh.itsq.service.UserService;

@WebServlet("/getuserinfobyid")
public class GetUserInfoById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		UserService service=new UserService();
		User user=service.getUserInfo(userId);
		StringBuffer result= new StringBuffer();
		result.append("{\"userInfo\":");
		result.append(user.getUserInfoToJson());
		result.append("}");
		response.setContentType("text/html;chaset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result.toString());
	}

}
