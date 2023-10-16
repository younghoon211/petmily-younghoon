package petmily.domain.member.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ResetPwForm {

    @NotBlank
    @Size(min = 3, max = 15)
    private String id;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$")
    private String pw;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$")
    private String pwCheck;

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$")
    private String inputCode;

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$")
    private String authCode;
}