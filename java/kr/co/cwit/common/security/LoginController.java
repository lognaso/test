package kr.co.cwit.common.security;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cwit.common.exception.CustomGenericException;
import kr.co.cwit.common.security.service.MemberService;
import kr.co.cwit.common.util.CommandMap;
import kr.co.cwit.common.util.LoginUtil;
import kr.co.cwit.common.util.dao.UserUtilVO;

@Controller
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private MemberService memserService;
	
	@Inject
	@Qualifier("sas")
	private SessionAuthenticationStrategy sessionAuthenticationStrategy;

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/loginProcess.do", consumes = { "application/json" })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, CommandMap anonyMap) {
		ModelAndView mav = new ModelAndView("wqView");
		
		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, String> userInfo = (Map<String, String>) anonyMap.get("dma_login");
		String username = userInfo.get("USERID");
		String password = userInfo.get("USERPW");
		
		try {
			Authentication token = new UsernamePasswordAuthenticationToken(username, password);
			Authentication auth = authenticationManager.authenticate(token);
			sessionAuthenticationStrategy.onAuthentication(auth, request, response);

			SecurityContextHolder.getContext().setAuthentication(auth);

			map.put("success", true);
			mav.addObject("results", map);
		} catch (SessionAuthenticationException e) {
			throw new CustomGenericException("SESSION_DUPLICATE");
		}

		return mav;
	}
	
	@PostMapping(value = "/OnePassOn.do")
	public String loginCit(HttpServletRequest request, HttpServletResponse response, @RequestBody UserUtilVO userVO) throws Exception {
		UserUtilVO paramVO = new UserUtilVO();
		paramVO.setUrl("https://dev.cwit.co.kr:8443/checkAuth");
		paramVO.setToken(userVO.getAccess_token().getToken());
		paramVO.setRefreshToken(userVO.getAccess_token().getRefreshToken());
		LoginUtil loginUtil = new LoginUtil();
		UserUtilVO resultVO = loginUtil.checkAuth(paramVO);	
		if(resultVO.getStatus() != null && resultVO.getStatus().equals("fail")){
			//로그인화면 
		}
		else if(resultVO.getId() != null){ 
			//로그인처리
			try {
				Authentication token = new UsernamePasswordAuthenticationToken(resultVO.getId(), memserService.loadUserByUsername(resultVO.getId()).getPassword());
				Authentication auth = authenticationManager.authenticate(token);
				sessionAuthenticationStrategy.onAuthentication(auth, request, response);

				SecurityContextHolder.getContext().setAuthentication(auth);

			} catch (SessionAuthenticationException e) {
				throw new CustomGenericException("SESSION_DUPLICATE");
			}
			
		}
		return "redirect:/";
	}
}
