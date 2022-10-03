package kr.co.cwit.common.dashboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.cwit.common.dashboard.dao.DashBoardDao;

@SuppressWarnings("unchecked")
@Service(value = "dashBoardService")
public class DashBoardServiceImpl implements DashBoardService {

	@Resource(name = "dashBoardDao")
	private DashBoardDao dashBoardDao;

	@Override
	public List<Map<String, Object>> selectDownLeft(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return dashBoardDao.selectDownLeft(conMap);
	}

	@Override
	public List<Map<String, Object>> selectDownRight(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return dashBoardDao.selectDownRight(conMap);
	}

	@Override
	public List<Map<String, Object>> selectUpLeft(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return dashBoardDao.selectUpLeft(conMap);
	}

	@Override
	public List<Map<String, Object>> selectUpRight(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return dashBoardDao.selectUpRight(conMap);
	}

	@Override
	public List<Map<String, Object>> selectNotice(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return dashBoardDao.selectNotice(conMap);
	}

	@Override
	public List<Map<String, Object>> selectMainNotice(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return dashBoardDao.selectMainNotice(conMap);
	}
}
