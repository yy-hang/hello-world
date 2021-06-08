package per.hyh.itsq.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Util {

	//字符串转换成时间格式
	public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter TIME_FORMATTWO = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	//时间转换成字符串格式
	public static final SimpleDateFormat STR_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	//图片保存地址
	public static final  String PATH="E:\\apache-tomcat-9.0.36\\webapps\\ITSQ\\static\\image\\";
	//更换后的图片路径
	public static final String NEWPATH="static/image/";
	//特殊字符转义
	public static String strTranslation(String str) {
		return str.replace("\"", "&quot").replace("\\", "\\\\").replace("\r", "\\r").replace("\n", "\\n");
	}
	// 四位数的随机数
	public static int RANDOM = (int) (Math.random() * (9999 - 1000)) + 1000;
	
}
