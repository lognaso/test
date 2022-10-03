package kr.co.cwit.common.util.email;

import org.springframework.beans.factory.annotation.Value;

public class Email {
	private String subject;
	
    private String content;
    
    @Value("#{config['email.default_sender']}")
    private String sender;
    
    private String[] reciver;
    
    private String[] ccReciver;
    
    private String attachFileFolder;

	private String attachFileName;
	
	private String uuid;
	
	public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String[] getReciver() {
		if(reciver != null)	return reciver.clone();
		return null;
    }
    public void setReciver(String[] emailAddr) {
    	this.reciver = emailAddr.clone();
    }

    public String[] getCcReciver() {
    	if(ccReciver != null)	return ccReciver.clone();
    	return null;
    }
    public void setCcReciver(String[] emailCcAddr) {
    	this.ccReciver = emailCcAddr.clone();
    }
    
    public String getAttachFileFolder() {
		return attachFileFolder;
	}
	public void setAttachFileFolder(String attachFileFolder) {
		this.attachFileFolder = attachFileFolder;
	}
	
    public String getAttachFileName() {
		return attachFileName;
	}
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
