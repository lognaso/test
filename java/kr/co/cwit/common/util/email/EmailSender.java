package kr.co.cwit.common.util.email;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(Email email) throws MailAuthenticationException, MailSendException, IOException	{
		 
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setSubject(email.getSubject());
			helper.setText(email.getContent(), true);
			helper.setFrom(email.getSender());
			helper.setTo(email.getReciver());
			if(email.getAttachFileFolder() != null && email.getAttachFileName() != null) {
				helper.addAttachment(URLEncoder.encode(email.getAttachFileName(), "UTF-8")
						, new FileSystemResource(new File(email.getAttachFileFolder(), email.getAttachFileName())));
			}
			if(email.getCcReciver() != null) {
				helper.setCc(email.getCcReciver());
			}
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}