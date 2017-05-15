package blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import blog.dao.TallyDao;
import blog.model.Tally;

@Service
public class TallyService {
	@Autowired
	private TallyDao tallyDao;
	//----------------For Chart.js--------------------------//
	public String getPastSevenDaysDataForChartJS()
	{
		Map<String,List<Object>> map = new HashMap<>();
		
		List<Tally> list = this.tallyDao.lastSevenDays();
		
		for(Tally t:list)
		{
			System.out.println(t.getDateString());
		}
		
		return JSON.toJSONString(map);
	}
}
