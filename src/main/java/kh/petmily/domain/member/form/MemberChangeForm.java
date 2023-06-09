package kh.petmily.domain.member.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class MemberChangeForm {

    private int mNumber;

    @NotBlank
    @Size(min = 3, max = 15)
    private String id;

    @NotBlank
    @Size(min = 8, max = 16)
    private String pw;

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @NotBlank
    @Email(message = "유효하지 않은 이메일 형식입니다.", regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @Size(min = 5, max = 30)
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;
}