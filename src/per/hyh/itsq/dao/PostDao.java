package per.hyh.itsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import per.hyh.itsq.model.Post;
public class PostDao {
	
	private static Connection conn = null;

	
	//��������
	public int insertPost(Post post) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("insert into itsq_post(id,main_id,title,content,status,create_id,create_time,update_id,update_time,kill_type)"
					+ "VALUES(BPOID_sq.nextval,null,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),null,null,?)");
			statement.setString(1,post.getTitle());
			statement.setString(2,post.getContent());
			statement.setString(3,post.getStatus());
			statement.setString(4,post.getCreate_id());
			statement.setString(5,post.getCreate_time());
			statement.setString(6,post.getKill_type());
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	//���۲����
	public int insertPl(Post post) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("insert into itsq_post(id,main_id,title,content,status,create_id,create_time,update_id,update_time,kill_type)"
					+ "VALUES(BPOID_sq.nextval,?,null,?,'1',?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),null,null,?)");
			statement.setString(1,post.getMain_id());
			statement.setString(2,post.getContent());
			statement.setString(3,post.getCreate_id());
			statement.setString(4,post.getCreate_time());
			statement.setString(5,post.getKill_type());
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	
	//��ѯ�ղ����Ĳ��͵�id
	public String getPostId() {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		
		PreparedStatement statement = null;
		String result = null;
		try {
			statement = conn.prepareStatement("select id from (select c.*,ROWNUM rr from (select b.* from itsq_post b ORDER BY create_time desc) c) where rr<=1");
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getString(1);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return result;
	}
	
	
	//���ȫ������
	public List<Post> getAllPost(String pageCount) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		PreparedStatement statement = null;
		List<Post> list=new ArrayList<Post>();
		try {
			statement = conn.prepareStatement(
					"select * from (select b.*,ROWNUM rr from (select a.*,b.name,b.HEADIMAGE from itsq_post a,itsq_user b  where a.create_id=b.id and a.status='1' and a.main_id is null ORDER BY a.create_time desc) b)where rr>=? and rr<=?");
			statement.setInt(1,(Integer.valueOf(pageCount)-1)*5+1);
			statement.setInt(2,Integer.valueOf(pageCount)*5);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post = new Post(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
					    rs.getString(7),
					    rs.getString(8),
					    rs.getString(9),
					    rs.getString(10),
					    rs.getString(11),
					    rs.getString(12));
				list.add(post);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;
	}
	
	
	//����ҵĲ���
	public List<Post> getMyPost(String userId,String pageCount){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		PreparedStatement statement = null;
		List<Post> list=new ArrayList<Post>();
		try {
			statement = conn.prepareStatement(
					"select * from (select b.*,ROWNUM rr from (select a.*,b.name,b.HEADIMAGE from itsq_post a,itsq_user b  where a.create_id=b.id and a.create_id=? and  a.MAIN_ID is null ORDER BY a.create_time desc) b) where rr>=? and rr<=?");
			statement.setString(1,userId);
			statement.setInt(2,(Integer.valueOf(pageCount)-1)*5+1);
			statement.setInt(3,Integer.valueOf(pageCount)*5);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post = new Post(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
					    rs.getString(7),
					    rs.getString(8),
					    rs.getString(9),
					    rs.getString(10),
					    rs.getString(11),
					    rs.getString(12));
				list.add(post);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;		
	}
	
	
	//getSelectPost
	public List<Post> getSelectPost(String SelectPost,String pageCount){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		String parm="%"+SelectPost+"%";
		PreparedStatement statement = null;
		List<Post> list=new ArrayList<Post>();
		try {
			statement = conn.prepareStatement(
					"select * from \r\n" + 
					"(select b.*,ROWNUM rr from \r\n" + 
					"(select a.*,b.name,b.HEADIMAGE from itsq_post a,itsq_user b  where a.status='1' and a.main_id is null  and (a.title like ? or a.content like ?)  ORDER BY a.create_time desc) b\r\n" + 
					")where rr>=? and rr<=?");
			statement.setString(1,parm);
			statement.setString(2,parm);
			statement.setInt(3,((Integer.valueOf(pageCount)-1)*5+1));
			statement.setInt(4,(Integer.valueOf(pageCount)*5));
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post = new Post(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
					    rs.getString(7),
					    rs.getString(8),
					    rs.getString(9),
					    rs.getString(10),
					    rs.getString(11),
					    rs.getString(12));
				list.add(post);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;		
	}
	
	
	//����ҿ��ܸ���Ȥ�Ĳ���(���û������ʾȫ��)
	public List<Post> getMyLikePost(String likeSkill,String pageCount){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		PreparedStatement statement = null;
		List<Post> list=new ArrayList<Post>();
		try {
			statement = conn.prepareStatement(
					"select * from (select b.*,ROWNUM rr from (select a.*,b.name,b.HEADIMAGE from itsq_post a,itsq_user b where a.CREATE_ID=b.id and a.kill_type= ? and a.status='1' and a.main_id is null ORDER BY a.create_time desc) b) where rr>=? and rr<=?");
			statement.setString(1,likeSkill);
			statement.setInt(2,(Integer.valueOf(pageCount)-1)*5+1);
			statement.setInt(3,Integer.valueOf(pageCount)*5);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post = new Post(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
					    rs.getString(7),
					    rs.getString(8),
					    rs.getString(9),
					    rs.getString(10),
					    rs.getString(11),
				        rs.getString(12));
				list.add(post);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;		
	}
	
	
	//���ݲ���Id��ȡ������Ϣ
	public List<Post> getPostById(String PostId){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		PreparedStatement statement = null;
		List<Post> list=new ArrayList<Post>();
		try {
			statement = conn.prepareStatement(
					"select a.*,name from itsq_post a,itsq_user b\r\n" + 
					"where a.id = ? and\r\n" + 
					"a.create_id=b.id\r\n" + 
					"UNION all \r\n" + 
					"select * from(\r\n" + 
					"SELECT a.*,name FROM itsq_post a , itsq_user b \r\n" + 
					"where main_id=? and\r\n" + 
					"a.create_id=b.id order by a.create_time desc)");
			statement.setString(1,PostId);
			statement.setString(2,PostId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post = new Post(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
					    rs.getString(7),
					    rs.getString(8),
					    rs.getString(9),
					    rs.getString(10),
				        rs.getString(11));
				list.add(post);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;
	}
	
	
	//��ò��͵�����
	public Post getPostZanCount(String postId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("select * from itsq_postzp where id=?");
			statement.setString(1, postId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post=new Post(postId, rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return post;
	}
	
	
	//���۵�������ʼ��
	public int postZanInit(String postIs) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("insert into itsq_postzp VALUES(?,0,0,0,0)");
			statement.setString(1,postIs);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	//������֮��Ĳ���
	public int updatePostPlCount(String postId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int nowrs=getPlCount(postId);
		int rs=0;
		try {
			statement = conn.prepareStatement("update itsq_postzp set pl_count=? where id=?");
			statement.setInt(1,nowrs);
			statement.setString(2,postId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	//���������ı��
	public int updatePostZanCount(String postId){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("update itsq_postzp set ZAN_COUNT=ZAN_COUNT+1 where id=?");
			statement.setString(1,postId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	//ת�����ı��updateZfcount
	public int updateZfcount(String postId){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("update itsq_postzp set zf_count=zf_count+1 where id=?");
			statement.setString(1,postId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	//��ȡ������
	public int getPlCount(String postId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		// ��ʼ��֤
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = conn.prepareStatement("select count(1) from itsq_post where main_id=?");
			statement.setString(1, postId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(1);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return result;
	}
	
	
	//ͨ��userId��ȡ�û�������Ϣ
	public List<Post> getUserPostById(String userId){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		Post post=null;
		PreparedStatement statement = null;
		List<Post> list=new ArrayList<Post>();
		try {
			statement = conn.prepareStatement("select a.* from itsq_post a where create_id =? and main_id is null ORDER BY create_time desc");
			statement.setString(1,userId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				post = new Post(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
					    rs.getString(7),
					    rs.getString(8),
					    rs.getString(9),
					    rs.getString(10));
				list.add(post);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;
	}
	
	
	/////////////////////////////////////////////------ɾ��post--------//////////////////////////////////////////////////////////////////
	
	
	//ɾ������
	public int deletePost(String postId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("DELETE FROM itsq_post where id=?");
			statement.setString(1,postId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	//ɾ������
	public int deleteFolPost(String postId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("DELETE FROM itsq_post where main_id=?");
			statement.setString(1,postId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	
	//��ѯ��Ҫ��ɾ�������������۵�id
	public List<String> selectId(String postId){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		PreparedStatement statement = null;
		List<String> postIdList=new ArrayList<String>();
		try {
			statement = conn.prepareStatement("select id from itsq_post where main_id=?");
			statement.setString(1, postId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				postIdList.add(rs.getString(1));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return postIdList;
	}

	
	//ɾ��itsq_postzp���Ӧ��Ϣ
	public int deletePostZpById(String postId) {
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return 0;}
		PreparedStatement statement = null;
		int rs=0;
		try {
			statement = conn.prepareStatement("DELETE FROM itsq_postzp where id=?");
			statement.setString(1,postId);
			rs = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	
	}
	
	
	//��ȡҳ��
	public List<Integer> GetPostPage(String userId,String likeSkill){
		conn=JdbcTemplate.getConnection();
		if (conn == null) {return null;}
		PreparedStatement statement = null;
		List<Integer> list=new ArrayList<Integer>();
		try {
			statement = conn.prepareStatement(
					"select Count(1) from itsq_post where main_id is null and create_id=? and main_id is null\r\n" + 
					"union all\r\n" + 
					"select count(1) from itsq_post where kill_type=? and  status ='1' and main_id is null\r\n" + 
					"union all\r\n" + 
					"select count(1) from itsq_post where status ='1' and main_id is null");
			statement.setString(1,userId);
			statement.setString(2,likeSkill);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				list.add(rs.getInt(1));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return list;
	}
	
	
	//GetSelectPostPage
	//��ȡ����������ҳ��
		public List<Integer> GetSelectPostPage(String SelectPost){
			conn=JdbcTemplate.getConnection();
			if (conn == null) {return null;}
			PreparedStatement statement = null;
			String parm="%"+SelectPost+"%";
			List<Integer> list=new ArrayList<Integer>();
			try {
				statement = conn.prepareStatement(
						"select COUNT(*) from (select b.*,ROWNUM rr from \r\n" + 
						"(select a.*,b.name,b.HEADIMAGE from itsq_post a,itsq_user b  where a.status='1' and a.main_id is null  and (a.title like ? or a.content like ?)  ORDER BY a.create_time desc) b\r\n" + 
						")");
				statement.setString(1,parm);
				statement.setString(2,parm);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					list.add(rs.getInt(1));
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}	
			return list;
		}
	
	
//		public static void main(String[] args) {
//		System.out.println(new PostDao().getSelectPost("12","1"));
//	}

}
