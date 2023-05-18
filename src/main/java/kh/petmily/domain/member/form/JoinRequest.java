package kh.petmily.domain.member.form;

import lombok.Data;

@Data
public class JoinRequest {

    private String id;
    private String pw;
    private String confirmPw;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;

    public boolean isPwEqualToConfirm() {
        return pw != null && pw.equals(confirmPw);
    }
}
