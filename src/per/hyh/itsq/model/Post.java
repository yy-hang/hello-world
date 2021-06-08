package per.hyh.itsq.model;

public class Post {

	private String id;
	private String main_id;
	private String title;
	private String content;
	private String status;
	private String create_id;
	private String create_time;
	private String update_time;
	private String update_id;
	private String kill_type;
	private String userName;
	private String userHeadImg;
	
	private int zanCount;
	private int plCount;
	private int scCount;
	private int zfCount;
	

	public Post() {
		super();
	}
	
	
	public Post(String id, String main_id, String title, String content, String status, String create_id,
			String create_time, String update_time, String update_id, String kill_type) {
		super();
		this.id = id;
		this.main_id = main_id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.create_id = create_id;
		this.create_time = create_time;
		this.update_time = update_time;
		this.update_id = update_id;
		this.kill_type = kill_type;
	}
	
	public Post(String id, String main_id, String title, String content, String status, String create_id,
			String create_time, String update_time, String update_id, String kill_type, String userName,String userHeadImg) {
		super();
		this.id = id;
		this.main_id = main_id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.create_id = create_id;
		this.create_time = create_time;
		this.update_time = update_time;
		this.update_id = update_id;
		this.kill_type = kill_type;
		this.userName = userName;
		this.userHeadImg = userHeadImg;
	}
	
	
	
	
	//对应点赞评论表
	public Post(String id, int zanCount, int plCount, int scCount, int zfCount) {
		super();
		this.id = id;
		this.zanCount = zanCount;
		this.plCount = plCount;
		this.scCount = scCount;
		this.zfCount = zfCount;
	}
	


	public Post(String id, String main_id, String title, String content, String status, String create_id,
			String create_time, String update_time, String update_id, String kill_type, String userName) {
		super();
		this.id = id;
		this.main_id = main_id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.create_id = create_id;
		this.create_time = create_time;
		this.update_time = update_time;
		this.update_id = update_id;
		this.kill_type = kill_type;
		this.userName = userName;
	}


	public String getPostInfoToJson() {
		return "{\"id\":\""+this.id+
				"\",\"main_id\":\""+this.main_id+
				"\",\"title\":\""+this.title+
				"\",\"content\":\""+this.content+
				"\",\"status\":\""+this.status+
				"\",\"create_id\":\""+this.create_id+
				"\",\"create_time\":\""+this.create_time+
				"\",\"update_time\":\""+this.update_time+
				"\",\"update_id\":\""+this.update_id+
				"\",\"kill_type\":\""+this.kill_type+
				"\"}";
	}
	
	
	
	public String getPostInfoToJsonName() {
		return "{\"id\":\""+this.id+
				"\",\"main_id\":\""+this.main_id+
				"\",\"title\":\""+this.title+
				"\",\"content\":\""+this.content+
				"\",\"status\":\""+this.status+
				"\",\"create_id\":\""+this.create_id+
				"\",\"create_time\":\""+this.create_time+
				"\",\"update_time\":\""+this.update_time+
				"\",\"update_id\":\""+this.update_id+
				"\",\"kill_type\":\""+this.kill_type+
				"\",\"userName\":\""+this.userName+
				"\"}";
	}
	
	
	public String getPostInfoToJsonNameTwo() {
		return "{\"id\":\""+this.id+
				"\",\"main_id\":\""+this.main_id+
				"\",\"title\":\""+this.title+
				"\",\"content\":\""+this.content+
				"\",\"status\":\""+this.status+
				"\",\"create_id\":\""+this.create_id+
				"\",\"create_time\":\""+this.create_time+
				"\",\"update_time\":\""+this.update_time+
				"\",\"update_id\":\""+this.update_id+
				"\",\"kill_type\":\""+this.kill_type+
				"\",\"userName\":\""+this.userName+
				"\",\"userHeadImg\":\""+this.userHeadImg+
				"\"}";
	}
	
	
	
	public String getPostZanToJson() {
		return "{\"id\":\""+this.id+
				"\",\"zanCount\":\""+this.zanCount+
				"\",\"plCount\":\""+this.plCount+
				"\",\"scCount\":\""+this.scCount+
				"\",\"zfCount\":\""+this.zfCount+
				"\"}";
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the main_id
	 */
	public String getMain_id() {
		return main_id;
	}
	/**
	 * @param main_id the main_id to set
	 */
	public void setMain_id(String main_id) {
		this.main_id = main_id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the create_id
	 */
	public String getCreate_id() {
		return create_id;
	}
	/**
	 * @param create_id the create_id to set
	 */
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	/**
	 * @return the create_time
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the update_time
	 */
	public String getUpdate_time() {
		return update_time;
	}
	/**
	 * @param update_time the update_time to set
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	/**
	 * @return the update_id
	 */
	public String getUpdate_id() {
		return update_id;
	}
	/**
	 * @param update_id the update_id to set
	 */
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}
	/**
	 * @return the kill_type
	 */
	public String getKill_type() {
		return kill_type;
	}
	/**
	 * @param kill_type the kill_type to set
	 */
	public void setKill_type(String kill_type) {
		this.kill_type = kill_type;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the zanCount
	 */
	public int getZanCount() {
		return zanCount;
	}


	/**
	 * @param zanCount the zanCount to set
	 */
	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}


	/**
	 * @return the plCount
	 */
	public int getPlCount() {
		return plCount;
	}


	/**
	 * @param plCount the plCount to set
	 */
	public void setPlCount(int plCount) {
		this.plCount = plCount;
	}


	/**
	 * @return the scCount
	 */
	public int getScCount() {
		return scCount;
	}


	/**
	 * @param scCount the scCount to set
	 */
	public void setScCount(int scCount) {
		this.scCount = scCount;
	}


	/**
	 * @return the zfCount
	 */
	public int getZfCount() {
		return zfCount;
	}


	/**
	 * @param zfCount the zfCount to set
	 */
	public void setZfCount(int zfCount) {
		this.zfCount = zfCount;
	}


	/**
	 * @return the userHeadImg
	 */
	public String getUserHeadImg() {
		return userHeadImg;
	}


	/**
	 * @param userHeadImg the userHeadImg to set
	 */
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	
	
	
	
}
