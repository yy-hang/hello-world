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
	            // ��ȡ�ͻ��˴�����ͼƬ�Ķ�������             
	            InputStream stream = request.getInputStream();
	                        //�����ļ��洢λE
	            String imagePath = Util.PATH + user.getHeadImage().substring(12);
	            // ������ļ���ʽ���������޸ģ���.jpg
	            FileOutputStream fos = new FileOutputStream(imagePath);
	            byte[] bbuf = new byte[32];
	            int hasRead = 0;
	            while ((hasRead = stream.read(bbuf)) > 0) {
	                fos.write(bbuf, 0, hasRead);
	                // ���ļ�д���������Ӳ����
	            }
	            fos.close();
	            stream.close();
	            /*
	             * ������Ҫע�⣬��������ԭʼ�ķ�ʽд���ļ�ʱ����ᷢ�ֱ�д����ļ�����ǰ4�в����Ƕ�ȡ�ļ����������ݣ�            
	             * �ӵ����п�ʼ�����������ݡ��ڶ������ļ�·���Լ����ơ�����ͨ���������ǣ��Ƚ��ļ�д����ʱ�ļ��У�Ȼ��
	             * �ٲ���RandomAccessFile��ȡ��ʱ�ļ��ĵ������Ժ󲿷֡�д�뵽Ŀ���ļ��С�              
	             */
	            Byte n;
	            // ��ȡ��ʱ�ļ�           
	            RandomAccessFile random = new RandomAccessFile(imagePath, "r");
	            int second = 1;
	            String secondLine = null;
	            while (second <= 2) {
	                secondLine = random.readLine();
	                second++;
	            }
	            int position = secondLine.lastIndexOf('\\');
	            // ��ȡ�ϴ��ļ�������
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
