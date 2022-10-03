package kr.co.cwit.common.util.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

@SuppressWarnings("unchecked")
public class ExListXls extends AbstractXlsDownload {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> data = (Map<String, Object>) model.get("data");

		response.setHeader("Content-Disposition", "attachment; filename=\"HS_CODE.xls" + "\"");

		Cell cell = null;

		HSSFSheet sheet1 = (HSSFSheet) workbook.createSheet("HS_CODE");

		sheet1.setDefaultColumnWidth(20);
		// 알림 cell style
		/*
		 * CellStyle notiStyle = workbook.createCellStyle();
		 * notiStyle.setFillBackgroundColor(HSSFColor.LIME.index);
		 */
		String[] sheet1Header = { "HS_CODE", "HS_DESC" };
		String[] sheet1Body = { "HS_CD", "HS_DESC" };
		// header
		for (int i = 0; i < sheet1Header.length; i++) {
			setText(getCell(sheet1, 0, i), sheet1Header[i]);
			// getCell(sheet1, 0, i).setCellStyle(notiStyle);
		}
		// body
		List<Object> categories1 = (List<Object>) data.get("list");
		for (int i = 0; i < categories1.size(); i++) {
			Map<String, String> category = (Map<String, String>) categories1.get(i);
			for (int j = 0; j < sheet1Body.length; j++) {
				cell = getCell(sheet1, 1 + i, j);
				if (category.get(sheet1Body[j]) != null)
					setText(cell, String.valueOf(category.get(sheet1Body[j])));
			}
		}
	}
}