package per.hyh.itsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import per.hyh.itsq.model.Opinions;
import per.hyh.itsq.model.Post;

public class OpinionsDao {

	private static Connection conn = null;
	
	public List<Opinions> getOpinions() {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Opinions opinions=null;
		PreparedStatement statement = null;
		List<Opinions> list=new ArrayList<Opinions>();
		try {
			statement = conn.prepareStatement("SELECT userid,opinions,time,status,id FROM itsq_opinions where status='1' ORDER by time desc");
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				opinions = new Opinions(rs.getString(5),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4));
				list.add(opinions);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;
	}

	public int insertOpinionds(Opinions opinions) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		String id=LocalDateTime.now().toString().replaceAll("-", "").replaceAll(":", "").replace(".", "").replace("T", "");
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("INSERT INTO itsq_opinions (userid,opinions,time,status,id) VALUES (?,?,sysdate,'1',?)");
			statement.setString(1,opinions.getUserId());
			statement.setString(2,opinions.getOpinions());
			statement.setString(3,id);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
//	public static void main(String[] args) {
//		System.out.println(LocalDateTime.now().toString().replaceAll("-", "").replaceAll(":", "").replace(".", "").replace("T", ""));
//	}
}
