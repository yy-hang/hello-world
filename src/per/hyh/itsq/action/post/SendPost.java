package per.hyh.itsq.action.post;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;
import per.hyh.itsq.service.PostService;
import per.hyh.itsq.util.Util;

@WebServlet("/sendboke")
public class SendPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendPost() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userInfo = (User)request.getSession().getAttribute("USER_INFO");
		String title=Util.strTranslation(request.getParameter("title"));
		String content=Util.strTranslation(request.getParameter("content"));
		String userid=userInfo.getId();
		String likeskill=request.getParameter("likeskill");
		String status=request.getParameter("status");
		String create_time=Util.TIME_FORMAT.format(LocalDateTime.now()).toString();
		PostService postService=new PostService();
		String result=postService.insertPost(title,content,userid,likeskill,status,create_time);
		response.setContentType("text/html;chaset=utf-8");
		response.getWriter().write(result);
	}

}
