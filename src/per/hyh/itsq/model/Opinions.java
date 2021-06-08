package per.hyh.itsq.model;

public class Opinions {
	
	
	public Opinions() {
		
	}
	private String id;
	private String userId;
	private String opinions;
	private String time;
	private String status;
	
	public Opinions(String id,String userId, String opinions, String time, String status) {
		this.userId = userId;
		this.opinions = opinions;
		this.time = time;
		this.status = status;
		this.id = id;
	}
	
	public String getPostInfoToJson() {
		return "{\"userId\":\""+this.userId+
				"\",\"opinions\":\""+this.opinions+
				"\",\"time\":\""+this.time+
				"\",\"status\":\""+this.status+
				"\",\"id\":\""+this.id+
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the opinions
	 */
	public String getOpinions() {
		return opinions;
	}
	/**
	 * @param opinions the opinions to set
	 */
	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
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
	

}
