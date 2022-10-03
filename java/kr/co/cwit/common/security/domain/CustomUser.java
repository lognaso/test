package kr.co.cwit.common.security.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	private static final long serialVersionUID = -6038903400772015407L;

	private String USERID;
	private String USERTYPECD;
	private String USERNM;
	private String USERPW;
	private String DEPTCD;
	private String DEPTNM;
	private String EMPCD;
	private String EMPNM;
	private String PLANTCD;
	private String LANGCD;
	private String COSTCENTER;
	private String COSTNM;
	private String PAYGBN;
	private String FIGRP;
	private String HRGRP;
	private int APPRCNT;
	private boolean ENABLED;
	private String LEADERCHK;
	
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String USERID, String USERTYPECD, String USERNM, String USERPW, String DEPTCD, String DEPTNM, String EMPCD, String EMPNM,String PLANTCD, String LANGCD,
			String COSTCENTER, String COSTNM, String PAYGBN, String FIGRP, String HRGRP, int APPRCNT, String LEADERCHK) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.setUSERID(USERID);
		this.setUSERTYPECD(USERTYPECD);
		this.setUSERNM(USERNM);
		this.setUSERPW(USERPW);
		this.setDEPTCD(DEPTCD);
		this.setDEPTNM(DEPTNM);
		this.setEMPCD(EMPCD);
		this.setEMPNM(EMPNM);
		this.setCOSTCENTER(COSTCENTER);
		this.setCOSTNM(COSTNM);
		this.setPAYGBN(PAYGBN);
		this.setFIGRP(FIGRP);
		this.setHRGRP(HRGRP);
		this.setAPPRCNT(APPRCNT);
/*
		this.setPLANTCD(PLANTCD);
		this.setLANGCD(LANGCD);
*/		
		this.setPLANTCD("00001");
		this.setLANGCD("KO");
		this.setENABLED(ENABLED);
		this.setLEADERCHK(LEADERCHK);
		
	}

	public boolean isENABLED() {
		return ENABLED;
	}

	public int getAPPRCNT() {
		return APPRCNT;
	}

	public void setAPPRCNT(int aPPRCNT) {
		APPRCNT = aPPRCNT;
	}

	public void setENABLED(boolean eNABLED) {
		ENABLED = eNABLED;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getUSERTYPECD() {
		return USERTYPECD;
	}

	public void setUSERTYPECD(String uSERTYPECD) {
		USERTYPECD = uSERTYPECD;
	}

	public String getUSERNM() {
		return USERNM;
	}

	public void setUSERNM(String uSERNM) {
		USERNM = uSERNM;
	}

	public String getUSERPW() {
		return USERPW;
	}

	public void setUSERPW(String uSERPW) {
		USERPW = uSERPW;
	}
	public String getDEPTCD() {
		return DEPTCD;
	}

	public void setDEPTCD(String dEPTCD) {
		DEPTCD = dEPTCD;
	}

	public String getDEPTNM() {
		return DEPTNM;
	}

	public void setDEPTNM(String dEPTNM) {
		DEPTNM = dEPTNM;
	}

	public String getEMPCD() {
		return EMPCD;
	}

	public void setEMPCD(String eMPCD) {
		EMPCD = eMPCD;
	}

	public String getEMPNM() {
		return EMPNM;
	}

	public void setEMPNM(String eMPNM) {
		EMPNM = eMPNM;
	}

	public String getPLANTCD() {
		return PLANTCD;
	}

	public void setPLANTCD(String pLANTCD) {
		PLANTCD = pLANTCD;
	}

	public String getLANGCD() {
		return LANGCD;
	}

	public void setLANGCD(String lANGCD) {
		LANGCD = lANGCD;
	}

	public String getCOSTCENTER() {
		return COSTCENTER;
	}

	public void setCOSTCENTER(String cOSTCENTER) {
		COSTCENTER = cOSTCENTER;
	}

	public String getCOSTNM() {
		return COSTNM;
	}

	public void setCOSTNM(String cOSTNM) {
		COSTNM = cOSTNM;
	}

	public String getPAYGBN() {
		return PAYGBN;
	}

	public void setPAYGBN(String pAYGBN) {
		PAYGBN = pAYGBN;
	}

	public String getFIGRP() {
		return FIGRP;
	}

	public void setFIGRP(String fIGRP) {
		FIGRP = fIGRP;
	}
	
	public String getHRGRP() {
		return HRGRP;
	}
	
	public void setHRGRP(String hRGRP) {
		HRGRP = hRGRP;
	}

	public String getLEADERCHK() {
		return LEADERCHK;
	}

	public void setLEADERCHK(String lEADERCHK) {
		LEADERCHK = lEADERCHK;
	}

	public Map<String, Object> toMap() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("USERID"	, this.getUSERID()		);
		map.put("USERTYPECD", this.getUSERTYPECD()	);
		map.put("USERNM"	, this.getUSERNM()		);
		map.put("USERPW"	, this.getUSERPW()		);
		map.put("DEPTCD"	, this.getDEPTCD()		);
		map.put("DEPTNM"	, this.getDEPTNM()		);
		map.put("EMPCD"		, this.getEMPCD()		);
		map.put("EMPNM"		, this.getEMPNM()		);
		map.put("PLANTCD"	, this.getPLANTCD()		);
		map.put("LANGCD"	, this.getLANGCD()		);
		map.put("COSTCENTER", this.getCOSTCENTER()	);
		map.put("COSTNM"	, this.getCOSTNM()		);
		map.put("PAYGBN"	, this.getPAYGBN()		);
		map.put("FIGRP"		, this.getFIGRP()		);
		map.put("HRGRP"		, this.getHRGRP()		);
		map.put("APPRCNT"	, this.getAPPRCNT()		);
		map.put("LEADERCHK"	, this.getLEADERCHK()	);

		return map;
	}
	
	@Override
	public boolean equals(Object rhs) {
		return super.equals(rhs);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return toMap().toString();
	}
}
