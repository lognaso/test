package kr.co.cwit.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import kr.co.cwit.application.ex.service.ExBaseHsMstService;
import kr.co.cwit.common.exception.CustomGenericException;
import kr.co.cwit.common.util.service.CommonUtilService;

@Controller
public class CommonUtilController {

	@Autowired
	private CommonUtilService commonUtilService;

	@Autowired
	private ExBaseHsMstService exBaseService;
	
	@RequestMapping("/util/insertErrorApi.do")
	public ModelAndView insertErrorApi(HttpServletRequest request, CommandMap paramMap) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		Map<String, Object> retMap = new HashMap<String, Object>();
		commonUtilService.insertErrorApi(request, paramMap);
		retMap.put("STATUS", "S");
		retMap.put("MSG", "정상적으로 처리 되었습니다.");
		mav.addObject("results", retMap);
		return mav;
	}
	/*
	 * 첨부파일 List
	 */
	@RequestMapping("/util/selectFileList.do")
	public ModelAndView selectFileList(CommandMap paramMap) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		Map<String, Object> retMap = new HashMap<String, Object>();

		try {
			List<Map<String, Object>> list = commonUtilService.selectFileList(paramMap.getMap());

			retMap.put("dlt_file_list", list);
			retMap.put("STATUS", "S");
			retMap.put("MSG", "정상적으로 처리 되었습니다.");
			mav.addObject("results", retMap);
		} catch (DataAccessException e) {
			retMap.put("STATUS", "E");
			retMap.put("MSG", "서버에서 오류가 발생 하였습니다.");
			retMap.put("DETAIL", e.getMessage());
			mav.addObject("results", retMap);
		}

		return mav;
	}

	/*
	 * 첨부파일 Save
	 */
	@RequestMapping("/util/saveFileList.do")
	public ModelAndView saveCommonUtil(CommandMap paramMap) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		Map<String, Object> retMap = new HashMap<String, Object>();

		try {
			int retCnt = commonUtilService.saveFileList(paramMap.getMap());

			retMap.put("resCnt", retCnt + "");
			retMap.put("STATUS", "S");
			retMap.put("MSG", "정상적으로 처리 되었습니다.");
			mav.addObject("results", retMap);
		} catch (DataAccessException e) {
			retMap.put("STATUS", "E");
			retMap.put("MSG", "서버에서 오류가 발생 하였습니다.");
			retMap.put("DETAIL", e.getMessage());
			mav.addObject("results", retMap);
		}

		return mav;
	}

	@RequestMapping("/sample/selectInOutList.do")
	public ModelAndView selectInOutList(CommandMap anonyMap) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		Map<String, Object> retMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = commonUtilService.selectInOutList(anonyMap.getMap());

		retMap.put("dl_master", list);
		retMap.put("STATUS", "S");
		retMap.put("MSG", "정상적으로 처리 되었습니다.");
		mav.addObject("results", retMap);

		return mav;
	}

	@RequestMapping("/sample/inListExcelDownload.do")
	public ModelAndView inListExcelDownload(HttpServletRequest req) throws Exception {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = commonUtilService.selectInList(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("date", dataMap.get("start_date"));
		data.put("list", list);

		return new ModelAndView("inListXlsView", "data", data);
	}

	@RequestMapping("/sample/outListExcelDownload.do")
	public ModelAndView outListExcelDownload(HttpServletRequest req) throws Exception {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = commonUtilService.selectOutList(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("date", dataMap.get("start_date"));
		data.put("list", list);

		return new ModelAndView("outListXlsView", "data", data);
	}

	@RequestMapping("/sample/selectInOutDuplList.do")
	public ModelAndView selectInOutDuplList(CommandMap anonyMap) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		Map<String, Object> retMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = commonUtilService.selectInOutDuplList(anonyMap.getMap());

		retMap.put("dl_dupl", list);
		retMap.put("STATUS", "S");
		retMap.put("MSG", "정상적으로 처리 되었습니다.");
		mav.addObject("results", retMap);

		return mav;
	}

	@RequestMapping("/sample/deleteInOutDuplList.do")
	public ModelAndView deleteInOutDuplList(CommandMap anonyMap) throws Throwable {
		ModelAndView mav = new ModelAndView("wqView");
		Map<String, Object> retMap = new HashMap<String, Object>();

		commonUtilService.deleteInOutDuplList(anonyMap.getMap());

		retMap.put("STATUS", "S");
		retMap.put("MSG", "정상적으로 처리 되었습니다.");
		mav.addObject("results", retMap);

		return mav;
	}

	@RequestMapping("/exExcel/exBaseMstExcelDownload.do")
	public ModelAndView sampleExcelDownload(HttpServletRequest req) throws Throwable {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = exBaseService.selectTbSdHsMstListExcel(dataMap);// selectBaseHsMstList(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);

		return new ModelAndView("exListXls", "data", data);
	}
	
	@RequestMapping("/exExcel/mmBaseSupplierPriceExcelDownload.do")
	public ModelAndView mmBaseSupplierPriceExcelDownload(HttpServletRequest req) throws Throwable {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = commonUtilService.mmBaseSupplierPriceExcelDownload(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);

		return new ModelAndView("outListXlsView", "data", data);		
	}	
	
	//재무회계 거래처 등록 엑셀 다운로드
	@RequestMapping("/exExcel/fiBaseCustomerExcelDownload.do")
	public ModelAndView fiBaseCustomerExcelDownload(HttpServletRequest req) throws Throwable {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = commonUtilService.fiBaseCustomerExcelDownload(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);

		return new ModelAndView("fiBaseCustomerListXlsView", "data", data);
	}
	
	//재무회계 계정과목 등록 엑셀 다운로드
	@RequestMapping("/exExcel/fiBaseAccountExcelDownload.do")
	public ModelAndView fiBaseAccountExcelDownload(HttpServletRequest req) throws Throwable {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = commonUtilService.fiBaseAccountExcelDownload(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);

		return new ModelAndView("fiBaseAccountListXlsView", "data", data);
	}
	
	//재무회계 전표조회 엑셀 다운로드
	@RequestMapping("/exExcel/fiSlipSearchExcelDownload.do")
	public ModelAndView fiSlipSearchExcelDownload(HttpServletRequest req) throws Throwable {
		Map<String, String> dataMap = getMapFromXmlValue(req);
		List<Map<String, Object>> list = commonUtilService.fiSlipSearchExcelDownload(dataMap);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);

		return new ModelAndView("fiSlipSearchListXlsView", "data", data);
	}
	
	private static Map<String, String> getMapFromXmlValue(HttpServletRequest req) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(req.getParameter("xmlValue"))));
			NodeList paramNodes = doc.getFirstChild().getChildNodes();

			Map<String, String> dataMap = new HashMap<String, String>();
			for (int i = 0; i < paramNodes.getLength(); i++) {
				Node param = paramNodes.item(i);
				dataMap.put(param.getNodeName(), param.getAttributes().getNamedItem("value").getNodeValue());
			}
			return dataMap;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new CustomGenericException("PARAMETER_CONVERT_FAIL");
		}
	}
}
