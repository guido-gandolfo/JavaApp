package correo;

import java.io.File;
import java.util.Properties;

import javax.activation.FileDataSource;
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

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtp.host", "smtps.gmail.com");
		props.put("mail.smtp.user", "apptaller2017@gmail.com");
		props.put("mail.smtp.suth", "true");
		
		Auth auth = new Auth("apptaller2017@gmail.com", "javamail");
		
		Session session = Session.getDefaultInstance(props, auth);
		
		Message msg = new MimeMessage(session);
		
		try{
			InternetAddress[] emails = new InternetAddress[2];
			emails[0] = new InternetAddress("guidoggandolfo@gmail.com");
			emails[1] = new InternetAddress("apptaller2017@gmail.com");
			
			msg.setRecipients(Message.RecipientType.TO, emails);
			
			InternetAddress de = new InternetAddress("apptaller2017@gmail.com");
			
			msg.setFrom(de);
			
			msg.setSubject("Tester Java Mail");
			
			MimeBodyPart corpo = new MimeBodyPart();
			MimeBodyPart anexo = new MimeBodyPart();
			
			corpo.setText("Enviando");
			
			File file = new File("camino de archivo");
			FileDataSource fds = new FileDataSource(file);
			
			anexo.setFileName(file.getName());
			
			Multipart mp = new MimeMultipart();
			
			mp.addBodyPart(corpo);
			mp.addBodyPart(anexo);
			
			msg.setContent(mp);
			
			Transport tr = session.getTransport("smtps");
			
			tr.connect("smtp.gmail.com", "apptaller2017@gmail.com", "javamail");
			
			msg.saveChanges();
			
			tr.send(msg, msg.getAllRecipients());
			
			tr.close();
		}catch(AddressException e){
			e.printStackTrace();
			
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}

}
