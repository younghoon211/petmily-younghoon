package petmily.service;

import petmily.domain.member.Member;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {

    String sendMailAtJoin(String to) throws MessagingException, UnsupportedEncodingException;

    void sendMailAtFindId(String to, Member member) throws MessagingException, UnsupportedEncodingException;

    String sendMailAtResetPw(String to, Member member) throws MessagingException, UnsupportedEncodingException;

}
