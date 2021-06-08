package per.hyh.itsq.service;

import java.util.List;

import per.hyh.itsq.dao.OpinionsDao;
import per.hyh.itsq.model.Opinions;

public class OpinionsService {

	private OpinionsDao opinionsDao=new OpinionsDao();
	
	public String getOpinions() {
		List<Opinions> list=opinionsDao.getOpinions();
		StringBuffer result =new StringBuffer();
		StringBuffer result1 =new StringBuffer();
		if(list.size()<1) {
			return "{\"result\":false}";
		}else {
			result.append("{\"result\":true,\"opinionsinfo\":[");
			list.stream().forEach(x->result1.append(","+x.getPostInfoToJson()));
			result.append(result1.toString().substring(1));
			result.append("]");
			result.append("}");
			return result.toString();
		}
	}
	
	public String insertOpinionds(String userId,String opinions) {
		Opinions opinionss=new Opinions(null,userId, opinions, null, null);
		if(opinionsDao.insertOpinionds(opinionss)>0) {
			return "{\"result\":true}";
		}
		return "{\"result\":false}";
		
	}
}
