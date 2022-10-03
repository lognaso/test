package kr.co.cwit.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MessageUtil {
 
    private MessageSourceAccessor msAcc = null;
    
    public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
        this.msAcc = msAcc;
    }
     
    public String getMessage(String key) {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return msAcc.getMessage(key, httpRequest.getLocale());
    }
}