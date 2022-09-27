package com.c702t.Cerveza.service.impl;

import com.c702t.Cerveza.service.EmailService;
import com.c702t.Cerveza.utils.EmailUtils;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${beermatch.email.sender}")
    private String organizationId;

    @Value("${beermatch.email.apikey}")
    private String sendGridKey;

    @Value("${beermatch.email.templateid}")
    private String templateId;

    @Value("${beermatch.email.templateidcontact}")
    private String templateContactId;

    public void sendEmail(Mail email) throws IOException {
        SendGrid sendGrid = new SendGrid(sendGridKey);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("/mail/send");
            request.setBody(email.build());
            Response response = sendGrid.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public void getEmailReady(String to, String template, String contentValue, String subject) throws IOException {
        Email fromEmail = new Email(organizationId);
        Email toEmail = new Email(to);
        Content content = new Content("text/html", contentValue);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        sendEmail(mail);
    }

    public void checkFromRequest(String to, String from) throws IOException {
        if (from.equalsIgnoreCase("userRegistered"))
            getEmailReady(to, templateId, EmailUtils.content("Bienvenido!", "Gracias por registrarse!"), "Mi Tienda");
        else
            getEmailReady(to, templateContactId, EmailUtils.content("Solicitud recibida",
                    "Muchas gracias por \n " + "contactarte con nosotros \n " +
                            "te mandaremos un mensaje a la brevedad"), "Somos Beer Match");
    }
}