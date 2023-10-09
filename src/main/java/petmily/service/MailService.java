package petmily.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    String sendMail(String to) throws MessagingException, UnsupportedEncodingException;
}
