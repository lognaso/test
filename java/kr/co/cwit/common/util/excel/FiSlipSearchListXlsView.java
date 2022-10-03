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
public class FiSlipSearchListXlsView extends AbstractXlsDownload {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> data = (Map<String, Object>) model.get("data");
		response.setHeader("Content-Disposition", "attachment; filename=\"SlipSearch.xls" + "\"");
	
		Cell cell = null;	
		
		HSSFSheet sheet1 = (HSSFSheet) workbook.createSheet("전표조회");
			
		//헤더색상 스타일
		CellStyle cs = workbook.createCellStyle();
		cs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		sheet1.setDefaultColumnWidth(15);
		
		String[] sheet1Header = {"전표번호", "발생일자" ,  "회계일자" , "작성자명" ,  "거래분류명" ,  "내수구분명" ,"거래유형명" ,
									"재화구분명" ,"작성부서명" , "금액" ,  "계정코드" , "계정명" ,"서브계정과목명" , "거래처코드",  "거래처명" ,
									"차변금액" , "대변금액" , "승인여부" , "승인일자" , "승인자" ,  "LC번호" , "관리번호", "참조LC번호", "참조관리번호" , "이율" ,  "적요" ,
									"REF_NO", "번호" , "화폐단위" , "과표", "환율" , "시작일" , "종료일" , "코스트센타" , "코스트센타명" , "업무코드"  , 
									"예산코드" , "구분" , "금융기관", "프로젝트", "차대구분" ,  "순번" 								    
								 };	
		
		String[] sheet1Body = {"SLIP_NUMB", "WRT_DT", "ACT_DT", "EMP_NM", "TRADE_FG1_NM", "TRADE_FG2_NM", "TRADE_FG3_NM", 
								"TRADE_FG4_NM" , "DEPT_NM", "AMT", "ACNT_CD", "ACNT_NM", "SUBJECT",  "CUS_CD" , "CUS_NM" ,
								"DR_AMT", "CR_AMT" ,  "STATUS" , "APPRO_DT", "APPRO_EMP_NM", "ETC_NO" , "SALE_MNG_NO", "REF_LC_NO", "REF_INV_NO" , "INTEREST", "SLIP_MEMO" ,
								 "REF_NO", "NO", "MNY_UNIT",  "STD_AMT", "EX_RATE" , "ST_DT", "ED_DT", "COST_CENTER", "COST_NM", "WORK_CD",
								 "BUDG_CD" , "GUBUN" , "BANK" , "PROJECT" , "DR_CR" , "SLIP_SEQ" 
							    };
		
		String[] sheet1Type = {"STRING", "DATE", "DATE", "STRING", "STRING", "STRING", "STRING", 
								"STRING", "STRING", "INT", "STRING", "STRING", "STRING", "STRING", "STRING",
								"INT", "INT", "STRING", "DATE", "STRING", "STRING", "STRING", "STRING", "STRING", "INT", "STRING",
								"STRING", "STRING", "STRING", "INT", "INT", "STRING", "STRING", "STRING", "STRING", "STRING",
								"STRING", "STRING", "STRING", "STRING", "STRING", "INT"};
		
		for(int i = 0 ; i < sheet1Header.length ; i++)	{			
			setTextHeader(getCell(sheet1, 0, i), sheet1Header[i], cs);
		}
		
		List<Object> categories1= (List<Object>) data.get("list");
		
		for (int i = 0; i < categories1.size(); i++) {
			Map<String, String> category = (Map<String, String>) categories1.get(i);
			for(int j = 0 ; j < sheet1Body.length ; j++)	{
				cell = getCell(sheet1, 1 + i, j);
				if(category.get(sheet1Body[j]) != null){					
					if("INT".equals(String.valueOf(sheet1Type[j]))){		
						setInt(cell, Double.parseDouble(String.valueOf(category.get(sheet1Body[j]))));
					}else{
						setText(cell, String.valueOf(category.get(sheet1Body[j])));
					}
				}
			}
		}
	}
}