package per.hyh.itsq.action.user;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.hyh.itsq.model.User;
import per.hyh.itsq.service.UserService;
import per.hyh.itsq.util.Util;


@WebServlet("/uploadfile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 String timeStr=Util.TIME_FORMATTWO.format(LocalDateTime.now());
		 User user = (User)request.getSession().getAttribute("USER_INFO");
		 user.setHeadImage(Util.NEWPATH+user.getName()+"_"+timeStr+".png");
		 UserService service =new UserService();
		 String result=service.updateHead(user);
		 
	        try {
	            // 获取客户端传过来图片的二进制流             
	            InputStream stream = request.getInputStream();
	                        //最终文件存储位E
	            String imagePath = Util.PATH + user.getHeadImage().substring(12);
	            // 这里的文件格式可以自行修改，如.jpg
	            FileOutputStream fos = new FileOutputStream(imagePath);
	            byte[] bbuf = new byte[32];
	            int hasRead = 0;
	            while ((hasRead = stream.read(bbuf)) > 0) {
	                fos.write(bbuf, 0, hasRead);
	                // 将文件写入服务器的硬盘上
	            }
	            fos.close();
	            stream.close();
	            /*
	             * 但是需要注意，采用这种原始的方式写入文件时，你会发现被写入的文件内容前4行并非是读取文件的真正内容，            
	             * 从第四行开始才是正文数据。第二行是文件路径以及名称。所以通常的做法是，先将文件写入临时文件中，然后
	             * 再采用RandomAccessFile读取临时文件的第四行以后部分。写入到目标文件中。              
	             */
	            Byte n;
	            // 读取临时文件           
	            RandomAccessFile random = new RandomAccessFile(imagePath, "r");
	            int second = 1;
	            String secondLine = null;
	            while (second <= 2) {
	                secondLine = random.readLine();
	                second++;
	            }
	            int position = secondLine.lastIndexOf('\\');
	            // 获取上传文件的名称
	            String fileName = secondLine.substring(position + 1, secondLine.length() - 1);
	            random.seek(0);
	            long forthEndPosition = 0;
	            int forth = 1;
	            while ((n = random.readByte()) != -1 && (forth <= 4)) {
	                if (n == '\n') {
	                    forthEndPosition = random.getFilePointer();
	                    forth++;
	                }
	            }
	            RandomAccessFile random2 = new RandomAccessFile(imagePath, "rw");
	            random.seek(random.length());
	            long endPosition = random.getFilePointer();
	            long mark = endPosition;
	            int j = 1;
	            while ((mark >= 0) && (j <= 6)) {
	                mark--;
	                random.seek(mark);
	                n = random.readByte();
	                if (n == '\n') {
	                    endPosition = random.getFilePointer();
	                    j++;
	                }
	            }
	            random.seek(forthEndPosition);
	            long startPoint = random.getFilePointer();
	            while (startPoint < endPosition - 1) {
	                n = random.readByte();
	                random2.write(n);
	                startPoint = random.getFilePointer();
	            }
	            random.close();
	            random2.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        response.getWriter().write(result);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

}
