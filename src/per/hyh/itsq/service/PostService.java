package per.hyh.itsq.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import per.hyh.itsq.dao.PostDao;
import per.hyh.itsq.model.Post;
import per.hyh.itsq.util.Util;

public class PostService {
	
	private PostDao postDao=new PostDao();

	public String insertPost(String title,String content,String userid,
			String likeskill,String status,String create_time) {
		Post post=new Post();
		String result=null;
		post.setTitle(title);
		post.setContent(content);
		post.setCreate_id(userid);
		post.setCreate_time(create_time);
		post.setStatus(status);
		post.setKill_type(likeskill);
		int b=postDao.insertPost(post);
		if(b>0) {
			String postId=postDao.getPostId();
			postDao.postZanInit(postId);
			result="{\"result\":true}";
		}
		else {
			result="{\"result\":false}";
		}
		return result;
	}
	
	
	public String insertPl(String mainId,String content,String userid,
			String likeskill,String status) {
		Post post=new Post();
		String create_time=Util.TIME_FORMAT.format(LocalDateTime.now());
		String result=null;
		post.setTitle(null);
		post.setMain_id(mainId);
		post.setContent(content);
		post.setCreate_id(userid);
		post.setCreate_time(create_time);
		post.setStatus(status);
		post.setKill_type(likeskill==null||"null".equals(likeskill)?"7":likeskill);
		int b=postDao.insertPl(post);
		if(b>0) {
			String postId=postDao.getPostId();
			//生成主贴评论点赞表数据
			postDao.postZanInit(postId);
			//评论成功插入->主贴评论数+1；
			postDao.updatePostPlCount(mainId);
			result="{\"result\":true}";
		}
		else {
			result="{\"result\":false}";
		}
		return result;
		
	}
	
	
	public String getAllPost(String pageCount){
		List<Post> list=postDao.getAllPost(pageCount);
		StringBuffer result =new StringBuffer();
		StringBuffer result1 =new StringBuffer();
		result.append("{\"postinfo\":[");
		list.stream().forEach(x->result1.append(","+x.getPostInfoToJsonNameTwo()));
		result.append(result1.toString().substring(1));
		result.append("]");
		result.append("}");
		return result.toString();
		
	}
		
	
	public String getMyPost(String userId,String pageCount) {
		List<Post> list=postDao.getMyPost(userId,pageCount);
		if(list.size()<1) {
			return "{\"rs\":11}";
		}
		StringBuffer result =new StringBuffer();
		StringBuffer result1 =new StringBuffer();
		result.append("{\"mypostinfo\":[");
		list.stream().forEach(x->result1.append(","+x.getPostInfoToJsonNameTwo()));
		result.append(result1.toString().substring(1));
		result.append("],\"rs\":1}");
		return result.toString();
	}
	
	
	public String getMyLikePost(String likeSkill,String pageCount) {
		if(likeSkill==null||"null".equals(likeSkill)) {
			return getAllPost("1").substring(0,getAllPost("1").length()-1)+",\"rs\":11}";
		}
		List<Post> list=postDao.getMyLikePost(likeSkill,pageCount);
		if(list.size()<1) {
			return getAllPost("1").substring(0,getAllPost("1").length()-1)+",\"rs\":1}";
		}
		StringBuffer result =new StringBuffer();
		StringBuffer result1 =new StringBuffer();
		result.append("{\"mylikepostinfo\":[");
		list.stream().forEach(x->result1.append(","+x.getPostInfoToJsonNameTwo()));
		result.append(result1.toString().substring(1));
		result.append("],\"rs\":111}");
		return result.toString();
	}
	
	
	//getSelectPost
	public String getSelectPost(String SelectPost,String pageCount) {
		List<Post> list=postDao.getSelectPost(SelectPost,pageCount);
		if(list.size()<1) {
			return "{\"result\":false}";
		}
		StringBuffer result =new StringBuffer();
		StringBuffer result1 =new StringBuffer();
		result.append("{\"result\":true,\"postinfo\":[");
		list.stream().forEach(x->result1.append(","+x.getPostInfoToJsonNameTwo()));
		result.append(result1.toString().substring(1));
		result.append("]}");
		return result.toString();
	}
	
	
	public String getPostById(String PostId){
		List<Post> list =postDao.getPostById(PostId);
		StringBuffer result =new StringBuffer();
		StringBuffer result1 =new StringBuffer();
		result.append("{\"postIdInfo\":[");
		list.stream().forEach(x->result1.append(","+x.getPostInfoToJsonName()));
		result.append(result1.toString().substring(1));
		result.append("]}");
		return result.toString();
	}
	
	
	public String getPostZanCount(String postId) {
		Post post =postDao.getPostZanCount(postId);
		StringBuffer result =new StringBuffer();
		result.append("{\"postZanCount\":");
		result.append(""+post.getPostZanToJson());
		result.append("}");
		return result.toString();
		
	}
	
	
	public String updateZanCount(String postId) {
		postDao.updatePostZanCount(postId);
		return getPostZanCount(postId);
	}
	
	public String updateZfcount(String postId) {
		postDao.updateZfcount(postId);
		return getPostZanCount(postId);
	}

	
	public String getUserPostById(String userId) {
		List<Post> postList=postDao.getUserPostById(userId);
		if(postList.size()<1) {
			return "{\"result\":true,\"resultCount\":0}";
		}
		StringBuffer result=new StringBuffer();
		StringBuffer result1=new StringBuffer();
		result.append("{\"result\":true,\"resultCount\":1,\"postInfo\":[");
		postList.stream().forEach(x->result1.append(","+x.getPostInfoToJson()));
		result.append(result1.toString().substring(1));
		result.append("]}");
		return result.toString();
	}
	
	
	public String deletePost(String postId) {
		if(postDao.deletePost(postId)>0) {
			List<String> postIdList=postDao.selectId(postId);
			postIdList.add(postId);
			postIdList.stream().forEach(x->postDao.deletePostZpById(x));
			//删除跟帖
			postDao.deleteFolPost(postId);
			return "{\"result\":true}";
		}else {
			return "{\"result\":false}";
		}
	}
	
	public String GetPostPage(String userId,String likeSkill) {
		List<Integer> pageList =postDao.GetPostPage(userId,likeSkill);
		//pageListRe存放页数的集合
		List<Integer> pageListRe=new ArrayList<Integer>();
		pageList.stream().forEach(x->{
			if(x%5==0) {
				x=x/5;
			}else {
				x=x/5+1;
			}
			pageListRe.add(x);
		});
		StringBuffer result=new StringBuffer();
		result.append("{\"myPostPage\":");
		result.append(pageListRe.get(0));
		result.append(",\"myLikePage\":");
		result.append(pageListRe.get(1));
		result.append(",\"allPostPage\":");
		result.append(pageListRe.get(2));
		result.append("}");
		return result.toString();
	}
	
	public String GetSelectPostPage(String SelectPost) {
		List<Integer> pageList =postDao.GetSelectPostPage(SelectPost);
		//pageListRe存放页数的集合
		List<Integer> pageListRe=new ArrayList<Integer>();
		pageList.stream().forEach(x->{
			if(x%5==0) {
				x=x/5;
			}else {
				x=x/5+1;
			}
			pageListRe.add(x);
		});
		StringBuffer result=new StringBuffer();
		result.append("{\"selectPage\":");
		result.append(pageListRe.get(0));
		result.append("}");
		return result.toString();
	}
	
//		public static void main(String[] args) {
//		System.out.println(new PostService().GetSelectPostPage("1"));
//	}
}
