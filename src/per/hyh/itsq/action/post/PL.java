package per.hyh.itsq.action.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.service.PostService;
import per.hyh.itsq.util.Util;

@WebServlet("/pl")
public class PL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PL() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId=request.getParameter("postId");
		String content=Util.strTranslation(request.getParameter("content"));
		String likeSkill=request.getParameter("likeSkill");
		String userid=request.getParameter("userid");
		PostService service=new PostService();
		String result=service.insertPl(postId,content, userid, likeSkill, "1");
		response.setContentType("text/html;chaset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

}
