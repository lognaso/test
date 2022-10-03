package kr.co.cwit.common.menu.service;

import java.util.List;
import java.util.Map;

public interface MenuService {
	public List<Map<String, Object>> selectSyUserAuthMenu(Map<String, Object> paramMap) throws Throwable;
	public List<Map<String, Object>> favorMenuSave(Map<String, Object> paramMap) throws Throwable;
	public Map<String, Object> selectMenuDescription(Map<String, Object> paramMap) throws Throwable;
	public void updateMenuDescription(Map<String, Object> paramMap) throws Throwable;
}
