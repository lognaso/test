package kr.co.cwit.common.util.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.cwit.common.util.CommandMap;

public interface CommonUtilService {

    public List<Map<String, Object>> selectFileList(Map<String, Object> paramMap) throws Throwable;
    public String selectFileSeq(Map<String, Object> paramMap) throws Throwable;

    public int saveFileList(Map<String, Object> paramMap) throws Throwable;
	
	public String getThrowMsg(String msg, String msgParam) throws Throwable;
    
    public List<Map<String, Object>> selectInOutList(Map<String, Object> paramMap) throws Exception;
    public List<Map<String, Object>> selectInList(Map<String, String> paramMap) throws Exception;
    public List<Map<String, Object>> selectOutList(Map<String, String> paramMap) throws Exception;
    public List<Map<String, Object>> selectInOutDuplList(Map<String, Object> paramMap) throws Exception;
    
    public void deleteInOutDuplList(Map<String, Object> paramMap) throws Exception;
	public List<Map<String, Object>> mmBaseSupplierPriceExcelDownload(Map<String, String> paramMap) throws Exception;
	
	public List<Map<String, Object>> fiBaseCustomerExcelDownload(Map<String, String> dataMap) throws Exception;
	public List<Map<String, Object>> fiBaseAccountExcelDownload(Map<String, String> dataMap) throws Exception;
	public List<Map<String, Object>> fiSlipSearchExcelDownload(Map<String, String> dataMap) throws Exception;
	
	public void insertErrorApi(HttpServletRequest request, CommandMap paramMap) throws Exception;
	
	public String encryptAES(String s) throws Exception;
	public String decryptAES(String s) throws Exception;
}
