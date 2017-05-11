package blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class TallyService {

	//----------------For Chart.js--------------------------//
	public String getPastSevenDaysDataForChartJS()
	{
		Map<String,List<Object>> map = new HashMap<>();
		
		return JSON.toJSONString(map);
	}
}
