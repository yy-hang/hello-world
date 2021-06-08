package per.hyh.itsq.action.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.service.PostService;


@WebServlet("/zf")
public class ZF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId=request.getParameter("postId");
		PostService service =new PostService();
		response.getWriter().write(service.updateZfcount(postId));
	}

}
