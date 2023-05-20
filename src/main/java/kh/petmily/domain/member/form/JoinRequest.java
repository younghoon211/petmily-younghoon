package kh.petmily.domain.member.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JoinRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String id;

    @NotBlank
    @Size(min = 8, max = 16)
    private String pw;

    @NotBlank
    @Size(min = 8, max = 16)
    private String confirmPw;

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @NotBlank
    private String birth;

    @NotBlank
    private String gender;

    @NotBlank
    @Email
    @Size(min = 5, max = 30)
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;

    public boolean isPwEqualToConfirm() {
        return pw != null && pw.equals(confirmPw);
    }
}
