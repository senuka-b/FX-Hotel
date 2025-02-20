package edu.icet.senuka.fxhotel_manager.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OTPEmailThread {

    public static void sendEmail(String content, String email, DataCallback callback) {
        new Thread(() -> {

            Properties secrets = loadProperties();

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop,
                    new jakarta.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(secrets.getProperty("email"), secrets.getProperty("password"));
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("FXHotel@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(email)
                );
                message.setSubject("FXHotel Reset Password");
                message.setContent(content , "text/html");

                Transport.send(message);

                callback.onDataReady(true);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    private static Properties loadProperties() throws RuntimeException {
        Properties properties = new Properties();
        try (InputStream input = OTPEmailThread.class.getClassLoader().getResourceAsStream("secrets.properties")) {

            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return properties;
    }

    public interface DataCallback {
        void onDataReady(Boolean data);
    }
}
