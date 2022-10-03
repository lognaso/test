package kr.co.cwit.common.menu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MenuDao {
	public List<Map<String, Object>> selectSyUserAuthMenu(Map<String, Object> paramMap) throws Throwable;
	
	public int favorMenuUpdateN(@Param("userid") String userId) throws Throwable;
	
	public List<String> selectExistFavorMenus(@Param("userid") String userId, @Param("list") ArrayList<String> list) throws Throwable;

	public int favorMenuUpdate(@Param("userid") String userId, @Param("list") ArrayList<String> list) throws Throwable;
	
	public int favorMenuInsert(@Param("userid") String userId, @Param("list") ArrayList<String> list) throws Throwable;
	
	public Map<String, Object> selectMenuDescription(Map<String, Object> paramMap) throws Throwable;
	
	public void updateMenuDescription(Map<String, Object> paramMap) throws Throwable;
	

	/**
	 * 그리드 컬럼 개인화 조회
	 * @param paramMap
	 * @return
	 * @throws Throwable
	 */
	public List<Map<String, Object>> selectSyGrdColIndvList(Map<String, Object> paramMap) throws Throwable;

	/**
	 * 그리드 컬럼 개인화 수정
	 * @param paramMap
	 * @return
	 * @throws Throwable
	 */
	public int updateSyGrdColIndv(Map<String, Object> paramMap) throws Throwable;
	
	/**
	 * 그리드 컬럼 개인화 추가
	 * @param paramMap
	 * @return
	 * @throws Throwable
	 */
	public int insertSyGrdColIndv(Map<String, Object> paramMap) throws Throwable;
}
