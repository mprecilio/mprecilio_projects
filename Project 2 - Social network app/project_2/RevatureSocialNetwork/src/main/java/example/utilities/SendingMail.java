package example.utilities;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class SendingMail {


	////////////////////////////////////////////Receives an EMAIL and TOKEN\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public static void sendMail(String receipient,String token) throws MessagingException {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.transport.protocl", "smtp");

		String myEmail = System.getenv("2107_EMAIL");
		String myPassword = System.getenv("2107_PASSWORD");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPassword);
			}
		});
		Message message = prepareMessage(session, myEmail, receipient,token);

		Transport.send(message);
	}


	///////////////////////// Message\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	private static Message prepareMessage(Session session, String myEmail, String receipient,String token) {


		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receipient));
			System.out.println("selected receipient: " + receipient);
			message.setSubject("RevatureSocialNetwork: Reset Your Password");
			String html = "Your password reset token is: " + token;
			message.setContent(html, "text/html");

			return message;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
