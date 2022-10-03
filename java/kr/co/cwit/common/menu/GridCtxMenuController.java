/** ########################################################
 * @ Class Name   	: GridCtxMenuController.java
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2018. 07.01
 * @version 1.0
 * @see
 * @ [저작권 및 라이센스 관련 정보를 여기에 작성한다.]
 * @ Copyright© 2013 (주)중외정보기술 All Rights Reserved.
 * ######################################################### */

package kr.co.cwit.common.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cwit.application.beans.ReturnMap;
import kr.co.cwit.common.menu.service.GridCtxMenuService;
import kr.co.cwit.common.util.CommandMap;

/** ########################################################
 * @System         	: 전체공통
 * @Unit System     : 전체공통
 * @Program Name  	: 그리드 컬럼 개인화
 * @Class Name     	: GridCtxMenuController.java
 * @Description     : 그리드 컬럼틀고정, 컬럼위치, 컬럼숨김 등을 개인화 처리한다.
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------      ---------      -------------------------------
 * @ 2018.09.17    	김태진        			최초생성
 *
* ########################################################## */

@Controller
public class GridCtxMenuController {

	@Autowired
	private GridCtxMenuService gridCtxMenuService;
	
	@PostMapping(value = "/menu/selectSyGrdColIndv.do", consumes = { "application/json" })
	public ModelAndView selectSyGrdColIndv(CommandMap paramMap) throws Throwable{
		ModelAndView mav = new ModelAndView("wqView");
		mav.addObject("results", new ReturnMap("dlt_GrdColIndv", gridCtxMenuService.selectSyGrdColIndvList(paramMap.getMap())));
		return mav;
	}
	
	@PostMapping(value="/menu/saveSyGrdColIndv.do", consumes = { "application/json" })
	public ModelAndView saveSyGrdColIndv(CommandMap paramMap) throws Throwable{
		ModelAndView mav = new ModelAndView("wqView");
		mav.addObject("results", new ReturnMap(gridCtxMenuService.saveSyGrdColIndv(paramMap.getMap())));
		return mav;
	}
}
