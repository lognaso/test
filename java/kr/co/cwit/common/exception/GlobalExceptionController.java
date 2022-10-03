package kr.co.cwit.common.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cwit.common.util.MessageUtil;

@RestControllerAdvice
public class GlobalExceptionController {
	@Autowired
	MessageUtil messageUtil;
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView mav = new ModelAndView("wqView");
		mav.addObject("errCode", ex.getErrCode());
		mav.addObject("errStr", ex.getErrStr() == null ? messageUtil.getMessage(ex.getErrCode() + ".msg") : ex.getErrStr());

		return mav;
	}
}