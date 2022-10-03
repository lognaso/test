package kr.co.cwit.common.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletResponse response = event.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("<script type='text/javascript'>alert('동일 계정이 다른 곳에서 로그인 되었습니다. 자동 로그아웃 됩니다.');top.location.href = '/logoutProcess.do';</script>");
		response.flushBuffer();
	}
}