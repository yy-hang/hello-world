package per.hyh.itsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import per.hyh.itsq.model.User;
import per.hyh.itsq.util.Util;



public class UserDao {

	private static Connection conn =JdbcTemplate.getConnection();

	public boolean checkUser(String id, String userPwd) {

		// 必要的数据验证
		if (id == null || "".equals(id)) {
			return false;
		}
		if (userPwd == null || "".equals(userPwd)) {
			return false;
		}
		conn=JdbcTemplate.getConnection();
		if (conn == null) {
			return false;
		}
		// 开始验证
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = conn.prepareStatement("select count(1) from itsq_user where id= ? and password = ?");
			statement.setString(1, id);
			statement.setString(2, userPwd);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(1);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result == 1;
	}
	
	
	//获取用户信息通过ID
	public User getUserInfo(String id) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		User user = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(
					"SELECT id,name,password,glystatus,sex,to_char(birthtime,'yyyy-MM-dd') birthtime,unitname,jobpost,maritalstatus,introduce,motto,likeskill,headimage FROM itsq_user where id=? ");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
			user = new User(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
					rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	
	//获取用户ID
	public String getUserInfoBName(String name) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		String userId = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("select id from itsq_user where name = ?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			rs.next();
			userId = rs.getString(1);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	return userId;
	}
	
	
	//获取用户头像地址
	public String getUserHeadImg(String id) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		String userHeadImg = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("SELECT headimage FROM itsq_user where id=?");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			userHeadImg = rs.getString(1);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	return userHeadImg;
	}
	
	
	//用户注册
	public String insertUser(User user) {
		conn=JdbcTemplate.getConnection();
		String userId=null;
		String fid=String.valueOf(Util.RANDOM*1000);
		if (conn == null) {return null;}
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(
					"INSERT INTO itsq_user (id,name,password,glystatus,sex,birthtime,unitname,jobpost,maritalstatus,introduce,motto,likeskill,headimage) \r\n" + 
					"VALUES(?+BUID_sq.nextval,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?)");
			statement.setString(1,fid);
			statement.setString(2,user.getName());
			statement.setString(3,user.getPassword());
			statement.setString(4,user.getGlyStatus());
			statement.setString(5,user.getSex());
			statement.setString(6,user.getBirthTime());
			statement.setString(7,user.getUnitName());
			statement.setString(8,user.getJobPost());
			statement.setString(9,user.getMaritalStatus());
			statement.setString(10,user.getIntroduce());
			statement.setString(11,user.getMotto() );
			statement.setString(12,user.getLikeSkill());
			statement.setString(13,user.getHeadImage());
			int rs = statement.executeUpdate();
			if(rs>0) {
				userId=getUserInfoBName(user.getName());
				insertUserLx(userId);
				return userId;
			}
			else {
				return userId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//初始化新用户用户联系方式表
	public int insertUserLx(String userId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("INSERT INTO itsq_userlx (id,welx,qqlx,tellx,bklx)VALUES (?,'','','','')");
			statement.setString(1,userId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	//获取User的联系方式
	public User getUserLx(String userId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		User user=null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(
					"SELECT id,nvl(welx,' '),nvl(qqlx,' '),nvl(tellx,' '), nvl(bklx,' ') FROM itsq_userlx where id= ?");
			statement.setString(1,userId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				user=new User(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5)
						);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	
	//修改用户头像地址
	public int updateHead(String headImgAddress,String userId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("UPDATE itsq_user SET headimage= ? where id= ? ");
			statement.setString(1,headImgAddress);
			statement.setString(2,userId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	
	//修改用户练习方式
	public int updateUserLx(String userId,String weLx,String qqLx,String telLx,String bkLx) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("UPDATE itsq_userlx SET welx= ?, qqlx=?, tellx=?, bklx=? where id= ?");
			statement.setString(1,weLx);
			statement.setString(2,qqLx);
			statement.setString(3,telLx);
			statement.setString(4,bkLx);
			statement.setString(5,userId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	
	//修改用户基本信息
	public int updateUserBassInfo(User user) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("UPDATE itsq_user SET name= ?, password=?, sex=?,BIRTHTIME=to_date(?,'yyyy-mm-dd'),UNITNAME=?,JOBPOST=?,MARITALSTATUS=? where id= ?");
			statement.setString(1,user.getName());
			statement.setString(2,user.getPassword());
			statement.setString(3,user.getSex());
			statement.setString(4,user.getBirthTime());
			statement.setString(5,user.getUnitName());
			statement.setString(6,user.getJobPost());
			statement.setString(7,user.getMaritalStatus());
			statement.setString(8,user.getId());
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	//修改用户基本信息1
	public int updateUserBassInfo1(User user) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("UPDATE itsq_user SET INTRODUCE= ?, MOTTO=?, LIKESKILL=? where id= ?");
			statement.setString(1,user.getIntroduce());
			statement.setString(2,user.getMotto());
			statement.setString(3,user.getLikeSkill());
			statement.setString(4,user.getId());
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	/**
	 *create table itsq_user(--用户表
id Varchar2(6) primary key, -- 用户id 唯一
name Varchar2(30), -- 用户名
password  Varchar2(30),--密码
glystatus char(1),--管理员判断
sex  char(1) not null,--性别
birthtime date,--出生日期
unitname Varchar2(100),--单位名称
jobpost Varchar2(30),--工作职位
maritalstatus VARCHAR2(5),--婚姻状况
introduce Varchar2(2000),--自我介绍
motto Varchar2(300),--座右铭
likeskill Varchar2(5),--感兴趣的语言
headimage Varchar2(200)--头像地址
);


create table itsq_userlx(
id Varchar2(10) primary key, -- 用户编号（唯一）
welx  Varchar2(60),
qqlx  Varchar2(20),
tellx  Varchar2(20),
bklx  Varchar2(60)
);


create table itsq_postzp(
id Varchar2(10) primary key, -- 博客编号（唯一）
zan_count  NUMBER,
pl_count  NUMBER,
sc_count  NUMBER,
zf_count  NUMBER
);
	 */
}
