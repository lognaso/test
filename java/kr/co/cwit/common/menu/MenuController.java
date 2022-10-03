package kr.co.cwit.common.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cwit.application.beans.ReturnMap;
import kr.co.cwit.common.menu.service.MenuService;
import kr.co.cwit.common.util.CommandMap;

@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;

	
	@PostMapping(value = "/menu/{key}.do",consumes = {"application/json" })
	public ModelAndView selectSyUserAuthMenu(CommandMap paramMap, @PathVariable String key) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		try {
			switch (key) {
			// 로그인시에 사용자의 권한에 따른 메뉴를 가져옴
			case "selectSyUserAuthMenu":	
				mav.addObject("results", new ReturnMap("dlt_menu_list", menuService.selectSyUserAuthMenu(paramMap.getMap())));
				break;
			case "favorMenuSave":
				mav.addObject("results", new ReturnMap("dlt_menu_list", menuService.favorMenuSave(paramMap.getMap())));
				break;
			case "selectMenuDescription":
				mav.addObject("results", menuService.selectMenuDescription(paramMap.getMap()));
				break;
			case "updateMenuDescription":
				menuService.updateMenuDescription(paramMap.getMap());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			mav.addObject("results", new ReturnMap(e));
		}
		return mav;
	}
}
