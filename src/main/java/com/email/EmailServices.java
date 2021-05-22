package com.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ankit Utkarsh
 */
public final class EmailServices {
    private EmailServices() {
    }

    private static Logger log = LogManager.getLogger(EmailServices.class);


    public void init( ){

        String methodName= "Init ";
        log.info("Enter Method : " + methodName);

    }


   public static void sendMail(){
       Properties prop = new Properties();
       prop.put("mail.smtp.auth", KeyBasket.MAIL_AUTH);
       //System.out.println(KeyBasket.MAIL_AUTH+KeyBasket.MAIL_SSL);
       prop.put("mail.smtp.ssl.enable", KeyBasket.MAIL_SSL);
       prop.put("mail.smtp.host", KeyBasket.MAIL_HOST);
       prop.put("mail.smtp.port", KeyBasket.MAIL_PORT);
       String messageException= null;
        for (int i=0; i<100 ; i++){
            Session session = Session.getInstance(prop, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(KeyBasket.MAIL_FROM, KeyBasket.MAIL_FROM_PASSWORD );
                }
               });
               session.setDebug(true);
               
               try {
                  messageException=new EmailServices().findingFile(); 
               } catch (Exception e) {
                   System.out.println("Error got");
        
               }
              
               
               System.out.println( "Messaging >> >> >> "+ messageException);
        
               Message message = prepareMessage(session, messageException);
               
               try {
                Transport.send(message);
                } catch (MessagingException e) {
                    EmailNotificationException ene =new EmailNotificationException("Transporting failed.", e);
                    log.error(ene.getExceptionTrace());
                    
        
                }
        }
       Session session = Session.getInstance(prop, new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(KeyBasket.MAIL_FROM, KeyBasket.MAIL_FROM_PASSWORD );
        }
       });
       session.setDebug(true);
       
       try {
          messageException=new EmailServices().findingFile(); 
       } catch (Exception e) {
           System.out.println("Error got");

       }
      
       
       System.out.println( "Messaging >> >> >> "+ messageException);

       Message message = prepareMessage(session, messageException);
       
       try {
        Transport.send(message);
        } catch (MessagingException e) {
            EmailNotificationException ene =new EmailNotificationException("Transporting failed.", e);
            log.error(ene.getExceptionTrace());
            

        }
    
     }

    private static Message prepareMessage(Session session, String messageException ) {
        
        Message message = new MimeMessage(session);
        String HTMLText= "<h1><b>"+messageException+"</b></h1>";

       
        try {
            message.setFrom(new InternetAddress(KeyBasket.MAIL_FROM));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(KeyBasket.MAIL_TO));
            message.setSubject(KeyBasket.MAIL_SUBJECT);
            //message.setText(KeyBasket.MAIL_TEXT);
            message.setText(messageException);
            //message.setContent(HTMLText, "text/html");

            return message;
        } catch (Exception e) {
            EmailNotificationException ene = new EmailNotificationException("Message Set Form interupted ", e);
            log.error(ene.getExceptionTrace());
        }        
        return null;
    }

    public String findingFile()throws Exception{
        String messageName=null;

        try {
            System.out.println(">>>>>>> ");
            File f = new File("D:\\EmailNotification\\f.log");
            FileInputStream fis = new FileInputStream(f);
            fis.read();


        } catch (FileNotFoundException e) {
               EmailNotificationException ene = new EmailNotificationException("File Not Found Exception", e);
            log.error(ene.getExceptionTrace());

            System.out.println(">>>>>> "+ ene.getExceptionTrace());
            messageName=ene.getExceptionTrace();
        }
        System.out.println(">>> Findinf File Name " + messageName);
        return messageName;
    }


    public static void main(String[] args){
        try {
            ResourceConfig.setResourceConfig("D:\\EmailNotification\\emailnotification\\src\\main\\java\\com\\email\\email.properties");
        } catch (FileNotFoundException e) {
            EmailNotificationException ene = new EmailNotificationException("Resource File Not found Correctly", e);
            log.error(ene.getExceptionTrace());
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        sendMail();
    }
}
