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
			//��¼�ɹ�,��Ҫ����ɹ�����߼�
			//1����ȡ��ǰ��¼�ɹ����û��Ļ�����Ϣ�����洢��session��
			request.getSession().setAttribute("USER_INFO", userService.getUserInfo(id));
			//���ŷ�����Ӧ
			response.getWriter().write("{\"result\":true}");
		}else{
			//ʧ��
			response.getWriter().write("{\"result\":false}");
		}
	}

}
