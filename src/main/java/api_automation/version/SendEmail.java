package api_automation.version;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
   public static void mail(String[] args) {
    
      String to = InputData.toaddress;
      String from = InputData.fromaddress;
      
      //Enter the credentials of your mail id to send the mail
      final String username = "test.automatio";//change accordingly
      final String password = "test";//change accordingly


      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
 
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
         message.setSubject(InputData.subjectdetails);
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText(InputData.mailbody);
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         messageBodyPart = new MimeBodyPart();
         String filename = InputData.reportpath;
         String reportname = InputData.output_xlsx_path;
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
 
         BodyPart messageBodyPart2 = new MimeBodyPart();
         DataSource source1 = new FileDataSource(reportname);
         messageBodyPart2.setDataHandler(new DataHandler(source1));
         messageBodyPart2.setFileName(reportname);
         multipart.addBodyPart(messageBodyPart2);

      
         message.setContent(multipart);
         Transport.send(message);
         System.out.println("Sent message successfully....");
  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}