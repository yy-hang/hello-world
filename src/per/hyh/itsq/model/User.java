package per.hyh.itsq.model;

public class User {

	/**
	 *     
	 *     id,
    name,
    password,
    glystatus,
    sex,
    birthtime,
    unitname,
    jobpost,
    maritalstatus,
    introduce,
    motto,
    likeskill,
    headimage
	 */
	private String id;//�û�id
	private String name;//�û���
	private String password;//�û�����
	private String glyStatus;//����Ա�ж�
	private String sex;//�Ա�
	private String birthTime;//��������
	private String unitName;//��λ����
	private String jobPost;//����ְλ
	private String maritalStatus;//����״̬
	private String introduce;//���ҽ���
	private String motto;//������
	private String likeSkill;//����Ȥ�ļ���
	private String headImage;//ͷ���ַ
	private String qqLx;//qq��ϵ��ʽ
	private String weLx;//΢����ϵ��ʽ
	private String telLx;//�绰����ϵ��ʽ
	private String bkLx;//��������ϵ��ʽ

	public User() {
		super();
	}
		
	
	public User(String id, String name, String password, String glyStatus, String sex, String birthTime,
			String unitName, String jobPost, String maritalStatus, String introduce, String motto, String likeSkill,
			String headImage) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.glyStatus = glyStatus;
		this.sex = sex;
		this.birthTime = birthTime;
		this.unitName = unitName;
		this.jobPost = jobPost;
		this.maritalStatus = maritalStatus;
		this.introduce = introduce;
		this.motto = motto;
		this.likeSkill = likeSkill;
		this.headImage = headImage;
	}
	
	//��ȡ��ϵ��ʽ����
	public User(String id, String qqLx, String weLx, String telLx, String bkLx) {
		super();
		
		this.id = id;
		this.weLx = weLx;
		this.qqLx = qqLx;
		this.telLx = telLx;
		this.bkLx = bkLx;
	}

	
	

	public String getUserInfoToJson() {
		return "{\"id\":\""+this.id+
				"\",\"name\":\""+this.name+
				"\",\"password\":\""+this.password+
				"\",\"glyStatus\":\""+this.glyStatus+
				"\",\"sex\":\""+this.sex+
				"\",\"birthTime\":\""+this.birthTime+
				"\",\"unitName\":\""+this.unitName+
				"\",\"jobPost\":\""+this.jobPost+
				"\",\"maritalStatus\":\""+this.maritalStatus+
				"\",\"introduce\":\""+this.introduce+
				"\",\"motto\":\""+this.motto+
				"\",\"likeSkill\":\""+this.likeSkill+
				"\",\"headImage\":\""+this.headImage+
				"\"}";
	}
	
	
	public String getUserLxToJson() {
		return "{\"id\":\""+this.id+
				"\",\"weLx\":\""+this.weLx+
				"\",\"qqLx\":\""+this.qqLx+
				"\",\"telLx\":\""+this.telLx+
				"\",\"bkLx\":\""+this.bkLx+
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the glyStatus
	 */
	public String getGlyStatus() {
		return glyStatus;
	}
	/**
	 * @param glyStatus the glyStatus to set
	 */
	public void setGlyStatus(String glyStatus) {
		this.glyStatus = glyStatus;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the birthTime
	 */
	public String getBirthTime() {
		return birthTime;
	}
	/**
	 * @param birthTime the birthTime to set
	 */
	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}
	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @return the jobPost
	 */
	public String getJobPost() {
		return jobPost;
	}
	/**
	 * @param jobPost the jobPost to set
	 */
	public void setJobPost(String jobPost) {
		this.jobPost = jobPost;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}
	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	/**
	 * @return the motto
	 */
	public String getMotto() {
		return motto;
	}
	/**
	 * @param motto the motto to set
	 */
	public void setMotto(String motto) {
		this.motto = motto;
	}
	/**
	 * @return the likeSkill
	 */
	public String getLikeSkill() {
		return likeSkill;
	}
	/**
	 * @param likeSkill the likeSkill to set
	 */
	public void setLikeSkill(String likeSkill) {
		this.likeSkill = likeSkill;
	}
	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}
	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}


	/**
	 * @return the qqLx
	 */
	public String getQqLx() {
		return qqLx;
	}


	/**
	 * @param qqLx the qqLx to set
	 */
	public void setQqLx(String qqLx) {
		this.qqLx = qqLx;
	}


	/**
	 * @return the weLx
	 */
	public String getWeLx() {
		return weLx;
	}


	/**
	 * @param weLx the weLx to set
	 */
	public void setWeLx(String weLx) {
		this.weLx = weLx;
	}


	/**
	 * @return the telLx
	 */
	public String getTelLx() {
		return telLx;
	}


	/**
	 * @param telLx the telLx to set
	 */
	public void setTelLx(String telLx) {
		this.telLx = telLx;
	}


	/**
	 * @return the bkLx
	 */
	public String getBkLx() {
		return bkLx;
	}


	/**
	 * @param bkLx the bkLx to set
	 */
	public void setBkLx(String bkLx) {
		this.bkLx = bkLx;
	}
	
	
	
	
}
