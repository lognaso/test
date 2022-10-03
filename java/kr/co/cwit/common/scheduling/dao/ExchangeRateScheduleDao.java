package kr.co.cwit.common.scheduling.dao;

import java.util.List;
import java.util.Map;

public interface ExchangeRateScheduleDao {
	
    public int mergeExchangeRateSchedule(Map<String, String> paramMap);
    
    public List<Map<String, String>> selectExchangeRateSchedule(Map<String, Object> paramMap);
}