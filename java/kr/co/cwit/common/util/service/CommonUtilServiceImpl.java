package kr.co.cwit.common.util.service;

import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.cwit.common.exception.CustomGenericException;
import kr.co.cwit.common.util.CommandMap;
import kr.co.cwit.common.util.dao.CommonUtilDao;

@SuppressWarnings("unchecked")
@Service(value = "commonUtilService")
public class CommonUtilServiceImpl implements CommonUtilService {
	@Resource(name = "commonUtilDao")
	private CommonUtilDao commonUtilDao;

	protected static final Logger logger = LoggerFactory.getLogger(CommonUtilServiceImpl.class);
	static final String apb = "SEK6Y8B5LAUBED0BO1040F4C0F8B1FD2";
	static final String iv = "HFYFOBM3IAN6G092";
	/*
	 * 첨부파일 List
	 */
	@Override
	public List<Map<String, Object>> selectFileList(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("dma_file_search");
		return commonUtilDao.selectFileList(conMap);
	}

	/*
	 * 첨부파일 seq
	 */
	@Override
	public String selectFileSeq(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> conMap = (Map<String, Object>) paramMap.get("dma_file_search");
		String seq = "";
		seq = commonUtilDao.selectFileSeq(conMap);

		return seq;
	}

	/*
	 * 첨부파일 Save
	 */
	@Override
	public int saveFileList(Map<String, Object> paramMap) throws Throwable {
		Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("data");
		String USERID = (String) dataMap.get("USERID");
		List<Map<String, Object>> listDtl = (List<Map<String, Object>>) paramMap.get("dlt_file_list");
		int fileSeqI = 0;

		if (listDtl == null || listDtl.size() == 0) {
			return 0;
		}

		// 파일SEQ
		Map<String, Object> tmpMap = listDtl.get(0);
		String fileSeq = "";
		if (tmpMap != null && tmpMap.get("ATCH_FILE_SEQ") != null && !"".equals(tmpMap.get("ATCH_FILE_SEQ"))) {
			fileSeq = tmpMap.get("ATCH_FILE_SEQ").toString();
		}
		if (fileSeq == null || "".equals(fileSeq)) {
			fileSeq = selectFileSeq(paramMap);
		}

		// 삭제작업 먼저 실행
		for (int i = 0; i < listDtl.size(); i++) {
			Map<String, Object> map = listDtl.get(i);

			if ("D".equals(map.get("rowStatus")) || "E".equals(map.get("rowStatus"))) {
				commonUtilDao.deleteFileList(map);
				String pathname = map.get("FILE_PATH") + "/" + map.get("ATCH_REAL_FILE_NM");
				File file = new File(pathname);
				if (file.exists()) {
					if(!file.delete())	{
						throw new CustomGenericException("FILE_DELETE_FAIL");
					}
				}
			}
		}
		for (int i = 0; i < listDtl.size(); i++) {
			Map<String, Object> map = listDtl.get(i);
			map.put("CREATE_USER_ID", USERID);
			map.put("MODIFY_USER_ID", USERID);
			map.put("ATCH_FILE_SEQ", fileSeq);
			fileSeqI = Integer.parseInt(fileSeq);
			if ("C".equals(map.get("rowStatus"))) {
				commonUtilDao.insertFileList(map);
			} else if ("U".equals(map.get("rowStatus"))) {
				commonUtilDao.updateFileList(map);
			}
		}

		return fileSeqI;
	}

	/**
	 * Desc: throw된 메시지의 UI용 메시지 처리
	 * 
	 * @param String
	 *            msg : 메시지(텍스트 혹은 msg-id)
	 * @param String
	 *            msgParam : msg-id사용시의 전달할 param
	 * @return 변경된 메시지
	 */
	@Override
	public String getThrowMsg(String msg, String msgParam) {
		String sMsg = msg;
		String sParam = msgParam;
		String sParsing = "@!@";
		String sSeparater = "|";// ||v_PROC_ERR_MSG || C_SEPARATER ||
								// v_ERR_MSG_PARAM || C_SEPAORA
		return sParsing + ((sMsg != null) ? sMsg : "") + sSeparater + ((sParam != null) ? sParam : "") + sParsing;
	}

	@Override
	public List<Map<String, Object>> selectInOutList(Map<String, Object> paramMap) throws Exception {
		return commonUtilDao.selectInOutList(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectInList(Map<String, String> paramMap) throws Exception {
		return commonUtilDao.selectInList(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectOutList(Map<String, String> paramMap) throws Exception {
		return commonUtilDao.selectOutList(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectInOutDuplList(Map<String, Object> paramMap) throws Exception {
		return commonUtilDao.selectInOutDuplList(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> mmBaseSupplierPriceExcelDownload(Map<String, String> paramMap) throws Exception {
		return commonUtilDao.mmBaseSupplierPriceExcelDownload(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> fiBaseAccountExcelDownload(Map<String, String> paramMap) throws Exception {
		return commonUtilDao.fiBaseAccountExcelDownload(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> fiBaseCustomerExcelDownload(Map<String, String> paramMap) throws Exception {
		return commonUtilDao.fiBaseCustomerExcelDownload(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> fiSlipSearchExcelDownload(Map<String, String> paramMap) throws Exception {
		return commonUtilDao.fiSlipSearchExcelDownload(paramMap);
	}
	
	@Override
	public void deleteInOutDuplList(Map<String, Object> paramMap) throws Exception {
		((Collection<Map<String, String>>) paramMap.get("data")).parallelStream()
				.forEach(commonUtilDao::deleteInOutDuplList);
	}

	@Override
	public void insertErrorApi(HttpServletRequest request, CommandMap paramMap) throws Exception {
		String ip = request.getHeader("X-Forwarded-For");
		 
 
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

		Map<String, Object> map = (Map<String, Object>) paramMap.get("data");
		HttpURLConnection connection = null;
		URL url = new URL("https://sensedata.cwit.co.kr:28443/resourceWebservice/rest/serverMngt/insertErrorLog");
		connection = (HttpURLConnection) url.openConnection();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", map.get("USERID"));
		jsonObject.put("userNm", map.get("USERNM"));
		jsonObject.put("hospitalCode", map.get("HOSPITALCODE"));
		jsonObject.put("errorIp", ip);
		jsonObject.put("errorPcNm", "");
		jsonObject.put("errorCode", map.get("ERRORCODE"));
		jsonObject.put("productType", map.get("PRODUCTTYPE"));
		jsonObject.put("sqlCtn", map.get("SQLCTN"));
		jsonObject.put("errorCtn", map.get("ERRORCTN"));
		jsonObject.put("programNm", map.get("PROGRAMNM"));
		jsonObject.put("formNm", map.get("FORMNM"));
		jsonObject.put("classInfo", map.get("CLASSINFO"));
		jsonObject.put("errorRegDate", map.get("ERRORREGDATE"));
		
		logger.info("@@@@@@@ERROR LOG INSERT jsonObject = " + jsonObject.toJSONString());
		
		String sendMsg = jsonObject.toString();
		
		try {
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			OutputStream os = connection.getOutputStream();
            os.write(sendMsg.getBytes("UTF-8"));
            os.close();
//	        OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream());
//	        wr.write(sendMsg);
//	        wr.flush();
	        logger.info("@@@@@@@ERROR LOG INSERT sendMsg = " + sendMsg);
	        logger.info("@@@@@@@ERROR LOG INSERT responseCode = " + connection.getResponseCode());
	        logger.info("@@@@@@@ERROR LOG INSERT responseMsg = " + connection.getResponseMessage());
	        
		} catch (Exception e) {
			
		} finally {
			connection.disconnect();
		}
	}

	@Override
	public String encryptAES(String s) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(apb.getBytes("UTF-8"), "AES");
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
        
        byte[] encryptedData = cipher.doFinal(s.getBytes("UTF-8"));
        
        return Base64.getEncoder().encodeToString(encryptedData);
	}

	@Override
	public String decryptAES(String s) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(apb.getBytes("UTF-8"), "AES");
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
        
        byte[] byteData = Base64.getDecoder().decode(s);
        byte[] decryptedData = cipher.doFinal(byteData);
        
        return new String(decryptedData, "UTF-8");
	}
}
