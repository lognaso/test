package kr.co.cwit.common.code.dao;

import java.util.List;
import java.util.Map;

public interface CodeDao {
	public List<Map<String, Object>> selectSystemCodeList(Map<String, Object> paramMap);

	public List<Map<String, Object>> selectCommonCodeList(Map<String, Object> paramMap);

	public List<Map<String, Object>> selectLv1MenuList();

	public List<Map<String, Object>> selectYYYYList();
	
	public List<Map<String, Object>> selectDeptCodeList(Map<String, Object> paramMap);
	
	//품목유형,상태, 대,중,소 코드
	public List<Map<String, Object>> selectCommonItemCdLv1(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectCommonItemCdLv2(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectCommonItemCdLv3(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectCommonItemCD(Map<String, Object> paramMap);
	
	// 품목유형
	public List<Map<String, Object>> selectCommonItemType(Map<String, Object> paramMap);
	// 품목상태
	public List<Map<String, Object>> selectCommonItemStat(Map<String, Object> paramMap);


	
}
