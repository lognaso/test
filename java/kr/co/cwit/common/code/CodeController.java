package kr.co.cwit.common.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cwit.application.beans.ReturnMap;
import kr.co.cwit.common.code.service.CodeService;
import kr.co.cwit.common.util.CommandMap;


@Controller
public class CodeController {
	@Autowired
	private CodeService codeService;

	@PostMapping(value = "/code/{key}.do", consumes = { "application/json" })
	public ModelAndView selectHrBaseDepartment(CommandMap paramMap, @PathVariable String key) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		try {
			Map<String, Object> retMap = new ReturnMap();
			switch (key) {
			case "selectCommonItemCdLv1":
				retMap.put("dlt_code_lv1", codeService.selectCommonItemCdLv1(paramMap.getMap()));
				break;
			case "selectCommonItemCdLv2":
				retMap.put("dlt_code_lv2", codeService.selectCommonItemCdLv2(paramMap.getMap()));
				break;
			case "selectCommonItemCdLv3":
				retMap.put("dlt_code_lv3", codeService.selectCommonItemCdLv3(paramMap.getMap()));
				break;
			case "selectCommonItemCD":
				retMap.put("dlt_item_cd", codeService.selectCommonItemCD(paramMap.getMap()));
				break;
			case "selectCommonItemType":
				retMap.put("dlt_item_type", codeService.selectCommonItemType(paramMap.getMap()));
				break;
			case "selectCommonItemStat":
				retMap.put("dlt_item_stat", codeService.selectCommonItemStat(paramMap.getMap()));
				break;	
			case "selectSystemCodeList":
				retMap.put("dlt_SystemCodeList", codeService.selectSystemCodeList(paramMap.getMap()));
				break;	
			case "selectCommonCodeList":
				retMap.put("dlt_CommonCodeList", codeService.selectCommonCodeList(paramMap.getMap()));
				break;	
			case "selectLv1MenuList":
				retMap.put("dl_code", codeService.selectLv1MenuList(paramMap.getMap()));
				break;	
			case "selectYYYYList":
				retMap.put("dl_code", codeService.selectYYYYList(paramMap.getMap()));
				break;	
			case "selectDeptCodeList":
				retMap.put("dlt_DeptCodeList", codeService.selectDeptCodeList(paramMap.getMap()));
				break;	
				
			default:
				break;
			}
			mav.addObject("results", retMap);
		} catch (Exception e) {
			mav.addObject("results", new ReturnMap(e));
		}

		return mav;
	}
}
