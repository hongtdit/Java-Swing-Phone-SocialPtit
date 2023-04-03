package illidan.service;

import illidan.model.EmailDataModel;
import illidan.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(EmailDataModel emailDataModel) {
        log.info("(sendEmail)emailDataModel: {}", emailDataModel);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("demomiet@gmail.com");
            mimeMessageHelper.setTo("antivirut96@gmail.com");
            mimeMessageHelper.setSubject("Offline message sent by "+emailDataModel.getName());
            String text = "Site\t : \tbuyreviewsapp.com \n" +
                    "Name\t : \t"+emailDataModel.getName()+"\n" +
                    "Email\t : \t"+emailDataModel.getEmail()+"\n" +
                    "Message\t : \t" +emailDataModel.getMessage();
            mimeMessageHelper.setText(text);

            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            log.error("(sendMail)ex: {}", ExceptionUtil.getFullStackTrace(e));
        }
    }

}
