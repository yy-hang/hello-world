package per.hyh.itsq.action.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;
import per.hyh.itsq.service.OpinionsService;


@WebServlet("/sendopinions")
public class SendOpinions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null==(User)request.getSession().getAttribute("USER_INFO")) {
			response.getWriter().write("{\"result\":false}");
			return ;
		}
		User user=(User)request.getSession().getAttribute("USER_INFO");
		String opinions=request.getParameter("opinions");
		OpinionsService service=new OpinionsService();
		response.getWriter().write(service.insertOpinionds(user.getId(), opinions));
	}

}
