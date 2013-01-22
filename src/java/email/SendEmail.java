/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Prodotto;

/**
 *
 * @author andrea
 */
public class SendEmail {
    
     public SendEmail(String email, String textMessage, String subject) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = new Properties();
        props.setProperty( "mail.smtp.host", "smtp.gmail.com" );
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put( "mail.debug", "true" );
        final String username = "asteonline.progweb@gmail.com";
        final String password2 = "asteonline123";
       
        Session session;
        session = Session.getInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("asteonline.progweb@gmail.com", "asteonline123");
        }});

        Message message = new MimeMessage( session );
        InternetAddress from = new InternetAddress( "asteonline.progweb@gmail.com" );
        InternetAddress to[] = InternetAddress.parse( email );
        message.setFrom( from );
        message.setRecipients( Message.RecipientType.TO, to );
        message.setSubject( subject );
        message.setSentDate( new Date() );
        message.setText( textMessage );
        Transport.send(message);
    }
    
}
