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
public class FiBaseCustomerListXlsView extends AbstractXlsDownload {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> data = (Map<String, Object>) model.get("data");
		response.setHeader("Content-Disposition", "attachment; filename=\"Customer.xls" + "\"");

		Cell cell = null;

		HSSFSheet sheet1 = (HSSFSheet) workbook.createSheet("거래처 등록");
		
		//헤더색상 스타일
		CellStyle cs = workbook.createCellStyle();
		cs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		sheet1.setDefaultColumnWidth(15);
		
		String[] sheet1Header = {"거래처 코드", "거래처명" , "사업자번호" , "법인번호" , "거래구분" , "거래구분명" , "사용여부" , "생년월일" ,
								 "국내외" , "국내외명" , "국가" , "국가명" , "대표자" , "우편번호" , "주소" , "업태" , "업종"};
		
		String[] sheet1Body = {"CUST_CD", "CUST_NM", "BIZ_REG_NO", "CORP_REG_NO", "TRANS_GBN", "TRANS_NM", "USE_YN", "REPRESENT_REG_NO1"
							, "DOMESTIC", "DOMESTIC_NM", "COUNTRY_CD", "COUNTRY_CD_NM", "REPRESENT", "ZIP_CD", "JUSO", "BIZ_CONDITION", "BIZ_TYPE"};
		
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