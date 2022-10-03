package kr.co.cwit.common.dashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cwit.application.beans.ReturnMap;
import kr.co.cwit.common.dashboard.service.DashBoardService;
import kr.co.cwit.common.util.CommandMap;

@Controller
public class DashBoardController {
	@Autowired
	private DashBoardService dashBoardService;

	@PostMapping(value = "/dashboard/{key}.do", consumes = { "application/json" })
	public ModelAndView selectDashBoard(CommandMap paramMap, @PathVariable String key) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");

		try {
			Map<String, Object> retMap = new ReturnMap();

			switch (key) {

			// dashBoard downLeft 공지사항
			case "selectdownleft":
				retMap.put("dl_downleft", dashBoardService.selectDownLeft(paramMap.getMap()));
				break;

			// dashBoard downRight
			case "selectdownright":
				retMap.put("dl_downright", dashBoardService.selectDownRight(paramMap.getMap()));
				break;

			// dashBoard upLeft
			case "selectupleft":
				retMap.put("dl_upleft", dashBoardService.selectUpLeft(paramMap.getMap()));
				break;

			// dashBoard upRight
			case "selectupright":
				retMap.put("dl_upright", dashBoardService.selectUpRight(paramMap.getMap()));
				break;

			// notice
			case "selectnotice":
				retMap.put("dl_notice", dashBoardService.selectNotice(paramMap.getMap()));
				break;

			default:
				break;
			}

			mav.addObject("results", retMap);
		} catch (DataAccessException e) {
			mav.addObject("results", new ReturnMap(e));
		}

		return mav;
	}

}
