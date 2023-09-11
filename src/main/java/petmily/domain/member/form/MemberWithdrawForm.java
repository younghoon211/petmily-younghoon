package petmily.domain.member.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class MemberWithdrawForm {

    @NotBlank
    @Size(min = 8, max = 16)
    private String pw;

    @NotBlank
    @Size(min = 8, max = 16)
    private String confirmPw;
}