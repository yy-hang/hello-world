package per.hyh.itsq.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;
import per.hyh.itsq.service.UserService;

@WebServlet("/homepageuser")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomePage() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("USER_INFO") == null) {
			response.getWriter().write("{\"result\":false}");
			return;
		}
		User userInfo = (User)request.getSession().getAttribute("USER_INFO");
		UserService service=new UserService();
		User user=service.getUserInfo(userInfo.getId());
		StringBuffer result = new StringBuffer();
		result.append("{\"result\":true,");
		result.append(user.getUserInfoToJson().substring(1));
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result.toString());
	}

}
