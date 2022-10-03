package kr.co.cwit.common.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.cwit.common.security.dao.MemberDao;
import kr.co.cwit.common.security.domain.CustomUser;

@Service
public class MemberService implements UserDetailsService {
	@Resource(name = "memberDao")
	private MemberDao memberDao;

	@Override
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException 	{
		Map<String, Object> memberMap = memberDao.userLogin(username);
		
		if (memberMap == null) {
			throw new UsernameNotFoundException("USER_NAME_NOT_FOUND");
		}

		String id 			= (String) memberMap.get("ID");
		String password 	= (String) memberMap.get("PASSWORD");
		String useYn 		= (String) memberMap.get("USE_YN");
		boolean enabled 		= "Y".equals(useYn) ? true : false;
		String usertypecd 	= (String) memberMap.get("USERTYPECD");
		String usernm 		= (String) memberMap.get("USERNM");
		String userpw 		= (String) memberMap.get("USERPW");
		String deptcd 		= (String) memberMap.get("DEPTCD");
		String deptnm 		= (String) memberMap.get("DEPTNM");
		String empcd 		= (String) memberMap.get("EMPCD");
		String empnm 		= (String) memberMap.get("EMPNM");
		String plantcd 		= (String) memberMap.get("PLANTCD");
		String langcd 		= (String) memberMap.get("LANGCD");
		String costcenter 	= (String) memberMap.get("COSTCENTER");
		String costnm 		= (String) memberMap.get("COSTNM");
		String paygbn 		= (String) memberMap.get("PAYGBN");
		String figrp 		= (String) memberMap.get("FIGRP");
		String hrgrp 		= (String) memberMap.get("HRGRP");
		String leaderchk    = (String) memberMap.get("LEADERCHK");
		if(!enabled)	{
			throw new DisabledException("DISABLED_USER");
		}
		
		int apprCnt = 0;
		
		if(!"hong".equals(username)){
			apprCnt = memberDao.selectApprPopup(username);
		}
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new CustomUser(id, password, enabled, true, true, true, roles, id, usertypecd, usernm, userpw, deptcd, deptnm,
				empcd, empnm, plantcd, langcd, costcenter, costnm, paygbn, figrp, hrgrp, apprCnt, leaderchk);
	}
}
