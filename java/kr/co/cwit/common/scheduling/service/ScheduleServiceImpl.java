package kr.co.cwit.common.scheduling.service;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.cwit.application.hr.dao.HrBaseDao;
import kr.co.cwit.application.le.dao.LeApiDao;
import kr.co.cwit.application.qm.dao.QmCalibDao;
import kr.co.cwit.common.scheduling.dao.ExchangeRateScheduleDao;
import kr.co.cwit.common.scheduling.dao.ScheduleMapper;
import kr.co.cwit.common.util.ExchangeRate;
import kr.co.cwit.common.util.email.Email;
import kr.co.cwit.common.util.email.EmailSender;

@Service(value = "scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	
	protected static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	
	@Resource(name = "exchangeRateScheduleDao")
	private ExchangeRateScheduleDao exchangeRateScheduleDao;
	
	@Resource(name = "hrBaseDao")
	private HrBaseDao hrBaseDao;
	
	@Resource(name = "qmCalibDao")
	private QmCalibDao qmCalibDao;
	
	@Resource(name = "emailSender")
    private EmailSender emailSender;
	
	@Resource(name = "email")
	private Email email;
	
	@Resource(name = "leApiDao")
	private LeApiDao leApiDao;
	
	@Resource(name = "scheduleMapper")
	private ScheduleMapper scheduleMapper;
	
	@Value("#{config['email.exchange_error_reciver']}")
	String[] reciver;
	
    public int exchangeRateSchedule(String basicDt) throws Throwable		{
		/*
		 * www.smbs.biz/ExRate/TodayExRate.jsp 호출(post)
		 * parameter(년월일)
		 * StrSch_Year
		 * StrSch_Month
		 * StrSch_Day
		 */
		int  dataCnt = 0;
		try	{
			if(basicDt == null || basicDt.length() != 8)	{
				basicDt = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
			}
			String year = basicDt.substring(0, 4);
			String month = basicDt.substring(4, 6);
			String day = basicDt.substring(6, 8);
				
			Map<String, String> dateMap = new HashMap<>();
			dateMap.put("StrSch_Year", year);
			dateMap.put("StrSch_Month", month);
			dateMap.put("StrSch_Day", day);
		
			Document doc = Jsoup.connect("http://www.smbs.biz/ExRate/TodayExRate.jsp").data(dateMap).post();
			Elements newsHeadlines = doc.body().select(".table_type7 tr");

			List<ExchangeRate> erList = new ArrayList<ExchangeRate>();
			
			Map<String, Object> resultMap = new HashMap<String, Object>() ;
	 
			boolean blKRWContail = false;  //원화추가 
			BigDecimal bUsdBasisRate = BigDecimal.valueOf(1); //USD
			BigDecimal bCnyBasisRate = BigDecimal.valueOf(1); //위안화
			BigDecimal bBasicBasisRate = BigDecimal.valueOf(1); //기준
			
			for(Element el : newsHeadlines)	{
				List<Element> exList = el.getAllElements().stream().filter(s -> s.html().trim().startsWith("<td")).collect(Collectors.toList());
				for(Element e : exList)	{
					ExchangeRate er = new ExchangeRate();
					int i = 0;
					for(Element ee : e.children())	{
						String str = ee.data();
						if(str == null || str.length() == 0)	str = ee.text();
						if(i == 0)	{
							er.setName(getExchange(str).replaceAll("\\W", "").substring(0, 3));
						}else if(i == 1)	{
							er.setExchange_rate(getExchange(str));
						}else if(i == 3)	{
							er.setCross_rate(getExchange(str));
						}
						i++;
					}
					
	                if(er.getName().equals("KRW")) blKRWContail = true;
	                if(er.getName().equals("USD")) bUsdBasisRate = new BigDecimal(er.getExchange_rate().replaceAll(",", "")); 
	                if(er.getName().equals("CNH"))	{
	                	bCnyBasisRate = new BigDecimal(er.getExchange_rate().replaceAll(",", ""));
	                	er.setName("CNY");
	                }
	                if(er.getName().equals("JPY")) {
	                	BigDecimal jpyValue = new BigDecimal(er.getExchange_rate().replaceAll(",", ""));
	                	er.setExchange_rate(jpyValue.divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_DOWN).toString());
	                }
	                
	 				erList.add(er);
				}
			}
	 
			if(!blKRWContail){
				ExchangeRate er = new ExchangeRate();
				er.setName("KRW");
				er.setCross_rate(bUsdBasisRate.toString());
				er.setExchange_rate(bBasicBasisRate.toString());
				erList.add(er);
	 		}
	 
			List<Map<String, String>> listDtl  = exchangeRateScheduleDao.selectExchangeRateSchedule(resultMap);
			for(Map<String, String> map : listDtl)	{
				map.putAll(dateMap);
				map.putAll(erList.stream().filter(p -> p.getName().equals(map.get("MONETARY_UNIT").toString())).findFirst().get().toMap());
				
				if("USD".equals(map.get("MONETARY_UNIT")) && (map.get("CROSS_RATE") == null || "".equals(map.get("CROSS_RATE"))))	{
					map.put("CROSS_RATE", bBasicBasisRate.toString()); 
				}
	 		    if("CNY".equals(map.get("MONETARY_UNIT")))	{
	 			   if(map.get("CROSS_RATE") == null || "".equals(map.get("CROSS_RATE"))) {
	 				  map.put("CROSS_RATE", bUsdBasisRate.divide(bCnyBasisRate, 2,BigDecimal.ROUND_DOWN).toString());
	 			   }
				}
	 		    
	 		   dataCnt += exchangeRateScheduleDao.mergeExchangeRateSchedule(map);
			}
		}catch(Exception e)	{
			InetAddress Address = InetAddress.getLocalHost(); 
			String IP = Address.getHostAddress();
			StringBuilder contents = new StringBuilder();
			contents.append("<html><body>");
			contents.append("<p>Host IP : ").append(IP).append("</p>");
			contents.append("<p>Error Msg : ").append(e.getMessage()).append("</p>");
			contents.append("</body></html>");
	        email.setReciver(reciver);
	        email.setSubject("환율 정보 수집 오류");
	        email.setContent(contents.toString());
	        //중외정보기술 TEST SERVER에서는 email 발송 금지
	        //emailSender.sendEmail(email);
	        
	        e.printStackTrace();
		}
		
		return dataCnt;
	}
	
	public String getExchange(String str) throws ScriptException	{
		/*
		 * 서울 외국환 거래 사이트에서 사용한 암복호화 방식
		 * 특정 문자열 추가하여 escape로 암호화
		 * 특정 문자열 제거 후 unescape로 복호화
		 */
		String encryptionReg1 = "_Z";
		String encryptionReg2 = "_A";
		String encryptionReg3 = "_B";
		String encryptionReg4 = "_C";
		String encryptionReg5 = "_D";
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		
		if(str.startsWith("d1"))	{
			str = (String) engine.eval("unescape('" + str.substring(str.indexOf("'", 0) + 1, str.indexOf("'", str.indexOf("'", 0) + 1)).replaceAll(encryptionReg1, "") + "')");
		}else if(str.startsWith("d2"))	{
			str = (String) engine.eval("unescape('" + str.substring(str.indexOf("'", 0) + 1, str.indexOf("'", str.indexOf("'", 0) + 1)).replaceAll(encryptionReg2, "") + "')");
		}else if(str.startsWith("d3"))	{
			str = (String) engine.eval("unescape('" + str.substring(str.indexOf("'", 0) + 1, str.indexOf("'", str.indexOf("'", 0) + 1)).replaceAll(encryptionReg3, "") + "')");
		}else if(str.startsWith("d4"))	{
			str = (String) engine.eval("unescape('" + str.substring(str.indexOf("'", 0) + 1, str.indexOf("'", str.indexOf("'", 0) + 1)).replaceAll(encryptionReg4, "") + "')");
		}else if(str.startsWith("d5"))	{
			str = (String) engine.eval("unescape('" + str.substring(str.indexOf("'", 0) + 1, str.indexOf("'", str.indexOf("'", 0) + 1)).replaceAll(encryptionReg5, "") + "')");
		}
		
		return str;
	}

	public void hrDeptScheduleService() throws Throwable {
		hrBaseDao.callProcDeptSchedule();
	}

	public void hrPositionScheduleService() throws Throwable {
		hrBaseDao.callProcPositionSchedule();
	}
	
	public void hrCommuteTimeScheduleService() throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		//전일
		map.put("YMD", LocalDate.now().minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE));
		map.put("EMPCD", "SYSTEM");
		hrBaseDao.callProcCommuteTimeSchedule(map);
		//당일
		map.put("YMD", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
		map.put("EMPCD", "SYSTEM");
		hrBaseDao.callProcCommuteTimeSchedule(map);
	}

	public void leDayStockScheduleService() throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PLANT_CD", "00001");
		map.put("DATE", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
		map.put("USER_ID", "system");
		leApiDao.procLeDayStock(map);
	}
	public void leDayStockBeforeScheduleService() throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PLANT_CD", "00001");
		map.put("DATE", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
		map.put("USER_ID", "system");
		leApiDao.procLeDayStockBefore(map);
	}
	
	public void leDayStockYesterDayScheduleService() throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PLANT_CD", "00001");
		map.put("DATE", LocalDate.now().minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE));
		map.put("USER_ID", "system");
		leApiDao.procLeDayStock(map);
	}
	/*
	 * 16.10.06 TB_QM_SAMPLE 관련 삭제
	public void qmSampleStstusScheduleService() throws Throwable {
		qmCalibDao.updateTbQmStatusFromStoreDeadLine();
	}
	*/
	
	public void initSequence(String seqName) throws Throwable {
		scheduleMapper.initSequence(seqName);
	}

	@Override
	public void fiApproveEMailScheduleService() throws Throwable {
		List<Map<String, Object>> list = scheduleMapper.selectBeforeApproveEmpList();
		
		if(list.size() > 0 && list != null) {
			String title = "[ONTIC ERP] 법인카드 미결건 안내 메일";
	
			String[] emailAddr = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				emailAddr[i] = map.get("E_MAIL").toString();
				logger.info("@@@@@@@Schedule E-Mail = " + emailAddr[i].toString());
			}
			//emailAddr[list.size()] = "admin@cwit.co.kr";
			StringBuffer content = new StringBuffer();
			content.append("<html><body>");
			content.append("<p>법인카드 미결건이 존재 합니다.").append(System.lineSeparator());
			content.append("</p>");
			content.append("<p>결재 미 진행 시 경비 정산에 차질이 발생할 수 있습니다.").append(System.lineSeparator());
			content.append("</p>");
			content.append("<p>본 메일은 법인카드 미결재건이 있을 경우 매일 09시에 발송 됩니다.").append(System.lineSeparator());
			content.append("</p>");
			content.append("<p>");
			content.append("<a href='https://onticerp.cwit.co.kr:8443/'>Ontic ERP 바로가기</a>").append(System.lineSeparator());
			content.append("</p>");
			content.append("</body></html>");
			email.setSubject(title);
			email.setContent(content.toString());
			email.setReciver(emailAddr);
			emailSender.sendEmail(email);
		}
	}
}
