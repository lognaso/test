package kr.co.cwit.common.util.dao;

import java.util.List;
import java.util.Map;

public interface CommonUtilDao {

    public List<Map<String, Object>> selectFileList(Map<String, Object> paramMap);
    public String selectFileSeq(Map<String, Object> paramMap);

    public int insertFileList(Map<String, Object> paramMap);
    public int updateFileList(Map<String, Object> paramMap);
    public int deleteFileList(Map<String, Object> paramMap);
    
    public List<Map<String, Object>> selectInOutList(Map<String, Object> paramMap);
    public List<Map<String, Object>> selectInList(Map<String, String> paramMap);
    public List<Map<String, Object>> selectOutList(Map<String, String> paramMap);

    public List<Map<String, Object>> mmBaseSupplierPriceExcelDownload(Map<String, String> paramMap);    
    
    public List<Map<String, Object>> fiBaseCustomerExcelDownload(Map<String, String> paramMap);
    public List<Map<String, Object>> fiBaseAccountExcelDownload(Map<String, String> paramMap);
    public List<Map<String, Object>> fiSlipSearchExcelDownload(Map<String, String> paramMap);
    
    public List<Map<String, Object>> selectInOutDuplList(Map<String, Object> paramMap);

    public void deleteInOutDuplList(Map<String, String> map);
}
