package petmily.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public String sendMail(String to) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        String authCode = getAuthCode();

        message.setFrom(new InternetAddress("계정", "PETMILY"));
        message.addRecipients(RecipientType.TO, to);
        message.setSubject("petmily 회원가입 이메일 인증코드입니다.");

        String content = "";
        content += "안녕하세요. 유기동물 입양을 장려하는 petmily입니다 :)<br>";
        content += "가입을 진심으로 감사드리며, 인증코드를 보내드립니다.<br><br>";
        content += "인증코드 : " + "<b>" + authCode + "</b>";
        message.setText(content, "utf-8", "html");

        mailSender.send(message);

        return authCode;
    }

    private String getAuthCode() {
        Random random = new Random();
        StringBuilder authCode = new StringBuilder();
        int num;

        while (authCode.length() < 6) {
            num = random.nextInt(10);
            authCode.append(num);
        }

        return authCode.toString();
    }
}
