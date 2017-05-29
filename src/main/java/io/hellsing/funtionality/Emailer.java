package io.hellsing.funtionality;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Emailer implements Runnable {
	private String receiverEmail;
	private String emailSubject;
	private String emailContent;
	
	
	
	
	public Emailer(String receiverEmail, String emailSubject, String emailContent) {
		this.receiverEmail = receiverEmail;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
	}

	public void sendEmail(String receiverEmail, String emailSubject, String emailContent){
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); // added this line
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "rtdb123@gmail.com");
	    props.put("mail.smtp.password", "Run with w0lves!");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);



	    Session session = Session.getInstance(props,null);
	    MimeMessage message = new MimeMessage(session);

	    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

	    // Create the email addresses involved
	    try {
//	        InternetAddress from = new InternetAddress("google@google.com");
	        message.setSubject(emailSubject);
//	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));

	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        BodyPart messageBodyPart = new MimeBodyPart();
//	        messageBodyPart.setText("some text to send");

	        // Add the text part to the multipart
//	        multipart.addBodyPart(messageBodyPart);

	        // Create the html part
	        messageBodyPart = new MimeBodyPart();
	        String htmlMessage = emailContent;
	        messageBodyPart.setContent(htmlMessage, "text/html");


	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);

	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", "rtdb123", "Run with w0lves!");
	        System.out.println("Transport: "+transport.toString());
	        transport.sendMessage(message, message.getAllRecipients());


	    } catch (AddressException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}

	@Override
	public void run() {
		System.out.println("Emailer running");
		searchForEmailsToSend();
		sendEmail(receiverEmail, emailSubject, emailContent);
		
	}

	private void searchForEmailsToSend() {
		// TODO Auto-generated method stub
		
	}
}
