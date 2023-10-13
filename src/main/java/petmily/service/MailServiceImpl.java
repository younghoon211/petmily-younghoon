package petmily.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.domain.member.Member;

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
    private final String EMAIL_ADDRESS = "이메일 주소";
    private final String SENDER = "PETMILY";

    @Override
    public String sendMailAtJoin(String to) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        String authCode = getAuthCode();

        message.setFrom(new InternetAddress(EMAIL_ADDRESS, SENDER));
        message.addRecipients(RecipientType.TO, to);
        message.setSubject("petmily 회원가입 인증코드입니다.");

        String content = "";
        content += "안녕하세요. 유기동물 입양을 장려하는 petmily입니다 :)<br>";
        content += "가입을 진심으로 감사드리며, 인증코드를 보내드립니다.<br><br>";
        content += "인증코드 : " + "<b>" + authCode + "</b>";
        message.setText(content, "utf-8", "html");

        mailSender.send(message);

        return authCode;
    }

    @Override
    public void sendMailAtFindId(String to, Member member) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(EMAIL_ADDRESS, SENDER));
        message.addRecipients(RecipientType.TO, to);

        String subject = "안녕하세요 petmily입니다. " + member.getName() + "님의 아이디를 보내드립니다.";
        message.setSubject(subject);

        String content = "";
        content += "안녕하세요. petmily입니다 :)<br>";
        content += member.getName() + "님의 아이디는 " + "<b>" + member.getId() + "</b>" + "입니다.<br><br>";
        content += "감사합니다.";
        message.setText(content, "utf-8", "html");

        mailSender.send(message);
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
