package kr.co.cwit.common.scheduling.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ScheduleMapper {
	public void initSequence(@Param("seq_name") String seqName);
	public List<Map<String, Object>> selectBeforeApproveEmpList();
}