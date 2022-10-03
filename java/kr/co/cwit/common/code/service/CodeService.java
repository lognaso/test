package kr.co.cwit.common.code.service;

import java.util.List;
import java.util.Map;

public interface CodeService {
	public List<Map<String, Object>> selectSystemCodeList(Map<String, Object> paramMap) throws Throwable;

	public List<Map<String, Object>> selectCommonCodeList(Map<String, Object> paramMap) throws Throwable;

	public List<Map<String, Object>> selectLv1MenuList(Map<String, Object> paramMap) throws Throwable;

	public List<Map<String, Object>> selectYYYYList(Map<String, Object> paramMap) throws Throwable;
	// 부서코드
	public List<Map<String, Object>> selectDeptCodeList(Map<String, Object> paramMap) throws Throwable; 
	
	//품목코드 대,중,소 분류
	public List<Map<String, Object>> selectCommonItemCdLv1(Map<String, Object> paramMap) throws Throwable;
	public List<Map<String, Object>> selectCommonItemCdLv2(Map<String, Object> paramMap) throws Throwable;
	public List<Map<String, Object>> selectCommonItemCdLv3(Map<String, Object> paramMap) throws Throwable;
	public List<Map<String, Object>> selectCommonItemCD(Map<String, Object> paramMap) throws Throwable;
	
	//품목유형
	public List<Map<String, Object>> selectCommonItemType(Map<String, Object> paramMap) throws Throwable;
	//품목상태
	public List<Map<String, Object>> selectCommonItemStat(Map<String, Object> paramMap) throws Throwable;
}
