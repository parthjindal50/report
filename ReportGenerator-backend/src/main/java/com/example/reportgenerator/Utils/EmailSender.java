package com.example.reportgenerator.Utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

/**
 * Utility class for sending emails using the SendGrid API.
 */
public class EmailSender {

    /**
     * Send an email to the specified recipient.
     *
     * @param to The recipient's email address.
     */
    public void sendEmail(String to) {
        String apiKey = "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";

        // Define the sender's and recipient's email addresses.
        Email from = new Email("parthjindal1998@gmail.com");
        String subject = "Report Generation Email";
        Email toMail = new Email(to);

        // Create the email content.
        //TODO Add content creation logic
        Content content = new Content("text/plain", "Hello, this is your report!");
        Mail mail = new Mail(from, subject, toMail, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            // Prepare the request to send the email.
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println("Email sent successfully! Status code: " + response.getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
