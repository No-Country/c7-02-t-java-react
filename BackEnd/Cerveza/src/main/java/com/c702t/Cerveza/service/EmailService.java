package com.c702t.Cerveza.service;

import com.sendgrid.Mail;

import java.io.IOException;

public interface EmailService {
    void sendEmail(Mail email) throws IOException;
    void getEmailReady(String to, String template, String content, String subject) throws IOException;
    void checkFromRequest(String to, String from) throws IOException;
    public void sendRecoverPassword(String to, String from, String passAux) throws IOException;

}
