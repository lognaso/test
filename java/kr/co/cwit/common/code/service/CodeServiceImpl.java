package kr.co.cwit.common.code.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.cwit.common.code.dao.CodeDao;

@SuppressWarnings("unchecked")
@Service(value = "codeService")
public class CodeServiceImpl implements CodeService {
	@Resource(name = "codeDao")
	private CodeDao codeDao;

	@Override
	public List<Map<String, Object>> selectSystemCodeList(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("dma_CommonCodeSearch");
		return codeDao.selectSystemCodeList(conMap);
	}

	@Override
	public List<Map<String, Object>> selectCommonCodeList(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("dma_CommonCodeSearch");
		return codeDao.selectCommonCodeList(conMap);
	}

	@Override
	public List<Map<String, Object>> selectLv1MenuList(Map<String, Object> paramMap) throws Throwable {
		return codeDao.selectLv1MenuList();
	}

	@Override
	public List<Map<String, Object>> selectYYYYList(Map<String, Object> paramMap) throws Throwable {
		return codeDao.selectYYYYList();
	}

	@Override
	public List<Map<String, Object>> selectDeptCodeList(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("dma_CommonCodeSearch");
		return codeDao.selectDeptCodeList(conMap);
	}

	// 품목코드 대,중,소 분류
	@Override
	public List<Map<String, Object>> selectCommonItemCdLv1(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return codeDao.selectCommonItemCdLv1(conMap);
	}

	@Override
	public List<Map<String, Object>> selectCommonItemCdLv2(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return codeDao.selectCommonItemCdLv2(conMap);
	}

	@Override
	public List<Map<String, Object>> selectCommonItemCdLv3(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return codeDao.selectCommonItemCdLv3(conMap);
	}

	@Override
	public List<Map<String, Object>> selectCommonItemCD(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return codeDao.selectCommonItemCD(conMap);
	}

	// 품목유형
	@Override
	public List<Map<String, Object>> selectCommonItemType(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return codeDao.selectCommonItemType(conMap);
	}

	// 품목상태
	@Override
	public List<Map<String, Object>> selectCommonItemStat(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("data");
		return codeDao.selectCommonItemStat(conMap);
	}
}
