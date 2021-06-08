package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.service.UserService;


@WebServlet("/updatejb")
public class UpdateJb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String birthDate=request.getParameter("birthDate");
		String unitName=request.getParameter("unitName");
		String jobPost=request.getParameter("jobPost");
		String maritalStatus=request.getParameter("maritalStatus");
		UserService service =new UserService();
		String result =service.updateUserJb(userId,name,password,sex,birthDate,unitName,jobPost,maritalStatus);
		response.getWriter().write(result);
	}

}
