package kr.co.cwit.common.security.dao;

import java.util.Map;

public interface MemberDao {
	public Map<String, Object> userLogin(String username);
	
	public int selectApprPopup(String username);
}