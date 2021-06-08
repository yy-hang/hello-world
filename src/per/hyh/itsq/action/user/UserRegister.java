package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;
import per.hyh.itsq.service.UserService;
import per.hyh.itsq.util.Util;


@WebServlet("/userregister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserRegister() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		String password=Util.strTranslation(request.getParameter("password"));
		user.setName(Util.strTranslation(request.getParameter("zusername")));
		user.setPassword(password);
		user.setSex(request.getParameter("sex"));
		user.setLikeSkill(request.getParameter("likeskill"));
		user.setBirthTime(request.getParameter("birthdate"));
		user.setUnitName(Util.strTranslation(request.getParameter("unitname")));
		user.setJobPost(Util.strTranslation(request.getParameter("jobpost")));
		user.setMaritalStatus(request.getParameter("maritalstatus"));
		UserService userService = new UserService();
		String userid=userService.insertUser(user);
		if(userid==null||"".equals(userid)) {
			response.getWriter().write("{\"zresult\":false}");
		}
		response.setContentType("text/html;chaset=utf-8");
		response.getWriter().write("{\"zresult\":true,\"userid\":\""+userid+"\",\"password\":\""+password+"\"}");
	}

}
