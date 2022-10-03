package kr.co.cwit.common.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import kr.co.cwit.common.menu.dao.MenuDao;

@SuppressWarnings({ "unchecked" })
@Service(value = "menuService")
public class MenuServiceImpl implements MenuService {
	@Resource(name = "menuDao")
	private MenuDao menuDao;

	@Override
	public List<Map<String, Object>> selectSyUserAuthMenu(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("data");
		return menuDao.selectSyUserAuthMenu(dataMap);
	}
	
	@Override
	public List<Map<String, Object>> favorMenuSave(Map<String, Object> paramMap) throws Throwable {
		String userId = (String) ((Map<String, Object>) paramMap.get("data")).get("USERID");
		ArrayList<JSONObject> favorMenuObjects = (ArrayList<JSONObject>) paramMap.get("favor_list");
		if(favorMenuObjects != null && favorMenuObjects.size() > 0)		{
			List<String> favorMenus = favorMenuObjects.stream()
														.map(e -> e.get("MENU_ID").toString())
														.distinct()
														.collect(Collectors.toList());
			ArrayList<String> existMenus = (ArrayList<String>) menuDao.selectExistFavorMenus(userId, (ArrayList<String>) favorMenus);
	
			ArrayList<String> insertMenus = new ArrayList<>(favorMenus);
			insertMenus.removeAll(existMenus);
			
			menuDao.favorMenuUpdateN(userId);
			if(existMenus.size() > 0)	menuDao.favorMenuUpdate(userId, existMenus);
			if(insertMenus.size() > 0)	menuDao.favorMenuInsert(userId, insertMenus);
		}else	{
			menuDao.favorMenuUpdateN(userId);
		}
		
		return menuDao.selectSyUserAuthMenu((Map<String, Object>) paramMap.get("data"));
	}
	
	@Override
	public Map<String, Object> selectMenuDescription(Map<String, Object> paramMap) throws Throwable {
		return menuDao.selectMenuDescription(paramMap);
	}
	
	@Override
	public void updateMenuDescription(Map<String, Object> paramMap) throws Throwable {
		menuDao.updateMenuDescription(paramMap);
	}
}
