package kr.co.cwit.common.util.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Workbook;

@SuppressWarnings("unchecked")
public class FiBaseAccountListXlsView extends AbstractXlsDownload {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> data = (Map<String, Object>) model.get("data");
		response.setHeader("Content-Disposition", "attachment; filename=\"Account.xls" + "\"");

		Cell cell = null;

		HSSFSheet sheet1 = (HSSFSheet) workbook.createSheet("계정과목 등록");
		
		//헤더색상 스타일
		CellStyle cs = workbook.createCellStyle();
		cs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		sheet1.setDefaultColumnWidth(15);
		
		String[] sheet1Header = {"계정코드", "계정명" , "상위계정코드" , "상위계정명" , "차대구분" , "게정구분" , "계정구분명" , "사용여부" , "거래처" , "시작일" ,
								 "종료일" , "과표" , "번호" , "이율" , "화폐단위" , "환율/불공제세액" , "구분" , "금융기관" , "프로젝트", "기타"};
		
		String[] sheet1Body = {"ACNT_CD", "ACNT_NAME", "UP_ACNT_CD", "UP_ACNT_NM", "DC_KIND", "ACNT_GBN", "ACNT_GBN_NM", "USE_YN" , "NEED_CUST", "NEED_ST_DT", 
							"NEED_EN_DT", "NEED_STD", "NEED_NO", "NEED_INTEREST", "NEED_MNY_UNIT", "NEED_EX_RATE", "NEED_GUBUN" , "NEED_BANK" , "NEED_PROJECT" , "NEED_ETC"};
		
		for(int i = 0 ; i < sheet1Header.length ; i++)	{
			setTextHeader(getCell(sheet1, 0, i), sheet1Header[i], cs);
		}
		
		List<Object> categories1= (List<Object>) data.get("list");
		
		for (int i = 0; i < categories1.size(); i++) {
			Map<String, String> category = (Map<String, String>) categories1.get(i);
			for(int j = 0 ; j < sheet1Body.length ; j++)	{
				cell = getCell(sheet1, 1 + i, j);
				if(category.get(sheet1Body[j]) != null)	setText(cell, String.valueOf(category.get(sheet1Body[j])));
			}
		}
	}
}