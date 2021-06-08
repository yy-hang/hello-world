package per.hyh.itsq.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.service.UserService;

@WebServlet("/userlogincheck")
public class UserLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UserLoginCheck() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String userPwd = request.getParameter("password");
		UserService userService = new UserService();
		if(userService.checkUser(id, userPwd)) {
			//登录成功,需要处理成功后的逻辑
			//1、获取当前登录成功的用户的基本信息，并存储在session中
			request.getSession().setAttribute("USER_INFO", userService.getUserInfo(id));
			//最后才返回响应
			response.getWriter().write("{\"result\":true}");
		}else{
			//失败
			response.getWriter().write("{\"result\":false}");
		}
	}

}
