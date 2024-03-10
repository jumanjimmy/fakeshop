package training.jimmy.FakeShop.event.listener;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import training.jimmy.FakeShop.event.RegistrationCompleteEvent;
import training.jimmy.FakeShop.model.User;
import training.jimmy.FakeShop.token.VerificationTokenService;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final VerificationTokenService tokenService;
    private final JavaMailSender mailSender;
    private User user;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        user = event.getUser();
        String vToken = UUID.randomUUID().toString();
        tokenService.saveVerificationTokenFromUser(user, vToken);
        String url = event.getConfirmationUrl()+"/registration/verifyEmail?token="+vToken;
        try {
            sendVerificationEmail(url);
        } catch (MessagingException  | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "FakeShop Verification Service";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ". </p>"+
                "<p>Thank you for registering with us,</p>" +
                "<p>Please, follow the link below to complete your registration:</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> FakeShop Registration Portal Service";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }



    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User theUser)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("dailycodeworks@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
