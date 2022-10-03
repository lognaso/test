package kr.co.cwit.common.scheduling.service;

public interface ScheduleService {

	public int exchangeRateSchedule(String basicDt) throws Throwable;
	
	public void hrDeptScheduleService() throws Throwable;
	
	public void hrPositionScheduleService() throws Throwable;
	
	public void hrCommuteTimeScheduleService() throws Throwable;
	
	public void leDayStockScheduleService() throws Throwable;
	
	public void leDayStockBeforeScheduleService() throws Throwable;
	
	public void leDayStockYesterDayScheduleService() throws Throwable;
	//16.10.06 TB_QM_SAMPLE 관련 삭제
	//public void qmSampleStstusScheduleService() throws Throwable;
	
	public void initSequence(String seqName) throws Throwable;
	
	public void fiApproveEMailScheduleService() throws Throwable;
}
