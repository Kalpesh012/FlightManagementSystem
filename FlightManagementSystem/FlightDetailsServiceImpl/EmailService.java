package FlightManagementSystem.FlightDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Async
	public void sendEmail(String toEmail,String subject,String message)
	{
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom("kalpeshkumarkhatri2000@gmail.com");
		javaMailSender.send(mailMessage);
	}
	
}
