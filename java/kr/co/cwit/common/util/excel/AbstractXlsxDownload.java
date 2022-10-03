package kr.co.cwit.common.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public abstract class AbstractXlsxDownload extends AbstractXlsxView {
	protected Cell getCell(XSSFSheet sheet, int row, int col) {
		Row sheetRow = sheet.getRow(row);
		if (sheetRow == null) {
			sheetRow = sheet.createRow(row);
		}
		Cell cell = sheetRow.getCell(col);
		if (cell == null) {
			cell = sheetRow.createCell(col);
		}
		return cell;
	}
	
	protected void setTextHeader(Cell cell, String text, CellStyle cs) {
		cell.setCellStyle(cs);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(text);		
	}
	
	protected void setText(Cell cell, String text) {
		cell.setCellType(CellType.STRING);
		cell.setCellValue(text);
	}
	
	protected void setInt(Cell cell, double text) {
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(text);
	}
}
