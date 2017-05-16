package blog.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import blog.dao.TallyDao;
import blog.model.Tally;
import blog.startup.Tools;

@Service
public class TallyService {
	@Autowired
	private TallyDao tallyDao;
	//----------------For Chart.js--------------------------//
	public String getPastSevenDaysDataForChartJS(String userid)
	{
		int before = 14;
		List<String> timeline = new ArrayList<>();
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.add(Calendar.DATE, -before);
		Map<String,Tally> tMap = userid==null?
				this.tallyDao.lastNDays(Tools.dateTransDateyyyyMMddWithoutMinus(start.getTime()))
				:this.tallyDao.lastNDays(Tools.dateTransDateyyyyMMddWithoutMinus(start.getTime()),userid);
		for(int i=0;i<before;i++)
		{
			String timeStr = Tools.dateTransDateyyyyMMddWithoutMinus(start.getTime());
			timeline.add(timeStr);
			start.add(Calendar.DATE, 1);
		}
		
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> datasets =new ArrayList<>();
		Map<String,Object> dataset = new HashMap<>();
		List<String> labels = new ArrayList<>();
		List<Long> data = new ArrayList<>();
		
		for(String time:timeline)
		{
			Tally t = tMap.get(time);
			if(t==null)
			{
				labels.add(time);
				data.add(0L);
			}
			else
			{
				labels.add(time);
				data.add(t.getCount());
			}
		}
		
		dataset.put("label", "两周内数据");
		dataset.put("fill", false);
		dataset.put("data", data);
		datasets.add(dataset);
		
		map.put("labels", labels);
		map.put("datasets", datasets);
		
		String re = JSON.toJSONString(map);
		
		return re;
	}
	
	public String getPastSevenDaysDataForChartJS()
	{
		return this.getPastSevenDaysDataForChartJS(null);
	}
}
