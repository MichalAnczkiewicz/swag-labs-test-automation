package com.saucedemo.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

import static com.saucedemo.core.utils.EmailParameters.*;

public class EmailSender {

    private static final Logger LOGGER = LogManager.getLogger(EmailSender.class);
    private static final String EMAIL_CANT_BE_SENT = "Wystapil blad przy probie wyslania wiadomosci";

    private final String from;
    private final String to;
    private final String subject;
    private final String content;
    private final boolean hasAttachment;
    private final String filePath;

    public EmailSender(String from, String to, String subject, String content, boolean hasAttachment, String filePath) {

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.hasAttachment = hasAttachment;
        this.filePath = filePath;
    }

    public void sendEmail() {

        try {

            Transport.send(createMessage());
        } catch (Exception exception) {

            LOGGER.error(EMAIL_CANT_BE_SENT);
            exception.printStackTrace();
        }
    }

    private Session getSession() {

        return Session.getInstance(EmailProperties.getProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(SENDER, EMAIL_PASSWORD);
            }
        });
    }

    private Message createMessage() {

        Message message = new MimeMessage(getSession());

        try {
            message.setFrom(new InternetAddress(from, SENDER_NAME));
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setContent(createEmailMultipart());

        } catch (Exception exception) {

            exception.printStackTrace();
            LOGGER.error(EMAIL_CANT_BE_SENT);
        }

        return message;
    }

    private Multipart createEmailMultipart() {

        Multipart multipart = new MimeMultipart();

        try {

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content);

            if (hasAttachment) {

                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(filePath));
                multipart.addBodyPart(attachmentPart);
            }

            multipart.addBodyPart(messageBodyPart);

        } catch (Exception exception) {

            LOGGER.error(EMAIL_CANT_BE_SENT);
            exception.printStackTrace();
        }

        return multipart;
    }
}
