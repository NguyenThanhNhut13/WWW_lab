/*
 * @ (#) EmailServiceImpl.java       1.0     03/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/12/2024
 * @version:    1.0
 */

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendInterviewInvitation(String email, String subject, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(createHtmlContent(content), true);

        javaMailSender.send(message);

    }

    private String createHtmlContent(String content) {
        String[] parts = content.split("\n");
        StringBuilder htmlContent = new StringBuilder();

        htmlContent.append("<!DOCTYPE html>")
                .append("<html lang='en'>")
                .append("<head>")
                .append("<meta charset='UTF-8'>")
                .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>")
                .append("<title>Interview Invitation</title>")
                .append("</head>")
                .append("<body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333; margin: 0; padding: 0; background-color: #f4f4f4;'>")
                .append("<div style='max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>");

        // Header
        htmlContent.append("<div style='background-color: #0056b3; color: #ffffff; padding: 20px; text-align: center;'>")
                .append("<h1 style='margin: 0; font-size: 24px;'>Interview Invitation</h1>")
                .append("</div>");

        // Content
        htmlContent.append("<div style='padding: 20px;'>");

        for (String part : parts) {
            if (part.trim().startsWith("###")) {
                // Section headers
                htmlContent.append("<h2 style='color: #0056b3; border-bottom: 1px solid #0056b3; padding-bottom: 10px;'>")
                        .append(part.replace("###", "").trim())
                        .append("</h2>");
            } else if (part.startsWith("-")) {
                // List items
                htmlContent.append("<ul style='list-style-type: none; padding-left: 0;'>");
                for (String item : part.split("\n")) {
                    htmlContent.append("<li style='margin-bottom: 10px;'>")
                            .append(item.replace("-", "").trim())
                            .append("</li>");
                }
                htmlContent.append("</ul>");
            } else {
                // Regular paragraphs
                htmlContent.append("<p style='margin-bottom: 15px;'>")
                        .append(part.replace("\n", "<br>"))
                        .append("</p>");
            }
        }

        htmlContent.append("</div>");

        // Footer
        htmlContent.append("<div style='background-color: #f8f9fa; color: #666; text-align: center; padding: 10px; font-size: 12px;'>")
                .append("<p>This is an automated email from our recruitment platform. Please do not reply directly to this message.</p>")
                .append("</div>");

        htmlContent.append("</div>")
                .append("</body>")
                .append("</html>");

        return htmlContent.toString().replaceAll("\\*\\*(.+?)\\*\\*", "<strong style='color: #0056b3;'>$1</strong>");
    }

}
