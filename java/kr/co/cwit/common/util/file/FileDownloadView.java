package kr.co.cwit.common.util.file;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import kr.co.cwit.common.exception.CustomGenericException;

@SuppressWarnings("unchecked")
public class FileDownloadView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> data = (Map<String, Object>) model.get("data");
    	String fileName = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".txt"; 
    	
    	if(data.get("filename") != null)	{
    		fileName = String.valueOf(data.get("filename"));
    	}

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        try(OutputStream out = response.getOutputStream();
        		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");) {
        	osw.write(data.get("contents").toString());
        	osw.flush();
		} catch (RuntimeException  e) {
			throw new CustomGenericException("FILE_DOWNLOAD_FAIL");
		}
    }
}
