package per.hyh.itsq.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Util {

	//�ַ���ת����ʱ���ʽ
	public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter TIME_FORMATTWO = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	//ʱ��ת�����ַ�����ʽ
	public static final SimpleDateFormat STR_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	//ͼƬ�����ַ
	public static final  String PATH="E:\\apache-tomcat-9.0.36\\webapps\\ITSQ\\static\\image\\";
	//�������ͼƬ·��
	public static final String NEWPATH="static/image/";
	//�����ַ�ת��
	public static String strTranslation(String str) {
		return str.replace("\"", "&quot").replace("\\", "\\\\").replace("\r", "\\r").replace("\n", "\\n");
	}
	// ��λ���������
	public static int RANDOM = (int) (Math.random() * (9999 - 1000)) + 1000;
	
}
