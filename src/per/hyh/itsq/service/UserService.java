package per.hyh.itsq.service;

import per.hyh.itsq.dao.UserDao;
import per.hyh.itsq.model.User;
import per.hyh.itsq.util.Util;

public class UserService {

	private UserDao userDao = new UserDao();
	
	
	//用户登录检查
	public boolean checkUser(String id,String userPwd) {
		return userDao.checkUser(id, userPwd);
	}
	
	
	//获取用户信息
	public User getUserInfo(String id) {
		User user=userDao.getUserInfo(id);
		return user;
	}

	
	//用户注册，返回用户的ID
	public String insertUser(User user) {
		return userDao.insertUser(user);
	}
	
	
	//用户修改头像
	public String updateHead(User user) {
		String headImgAddress=user.getHeadImage();
		String userId=user.getId();
		int re=userDao.updateHead(headImgAddress,userId);
		String result=null;
		if(re>0) {
			result="1";
		}else {
			result="0";
		}
		return result;
		
	}
	
	
	//修改用户联系方式
	public String updateUserLx(String userId,String weLx,String qqLx,String telLx,String bkLx) {
		User user=new User(userId,weLx,qqLx,telLx,bkLx);
		String result=null;
		int rs=0;
		rs=userDao.updateUserLx(user.getId(), user.getWeLx(), user.getQqLx(), user.getTelLx(), user.getBkLx());
		if(rs<1) {
			result="{\"result\":false}";
		}else {
			result="{\"result\":true}";
		}
		return result;
		
	}
	
	
	//修改用户基本信息1
	public String updateUserJb1(String userId,String introduce,String motto,String likeskill) {
		User user=new User(userId,null,null,null,null,null,null,null,null,Util.strTranslation(introduce),Util.strTranslation(motto),likeskill,null);
		String result=null;
		int rs=0;
		rs=userDao.updateUserBassInfo1(user);
		if(rs<1) {
			result="{\"result\":false}";
		}else {
			result="{\"result\":true}";
		}
		return result;
		
	}

	
	
	//修改用户基本信息
	public String updateUserJb(String userId,String name,String password,String sex,
			String birthDate,String unitName,String jobPost,String maritalStatus) {
		User user=new User(userId,Util.strTranslation(name),Util.strTranslation(password),
							null,sex,birthDate,Util.strTranslation(unitName),Util.strTranslation(jobPost),
							maritalStatus,null,null,null,null);
		String result=null;
		int rs=0;
		rs=userDao.updateUserBassInfo(user);
		if(rs<1) {
			result="{\"result\":false}";
		}else {
			result="{\"result\":true}";
		}
		return result;
		
	}
	
	
	//获取用户联系方式
	public String getUserLx(String userId) {
		User user=new User(); 
		user =userDao.getUserLx(userId);
		System.out.println(user.getId());
		String result=null;
		result=user.getUserLxToJson();
		return result;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(new UserService().getUserLx("21"));
	}
}
