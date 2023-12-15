package petmily.domain.member.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PwForm {

    private String pw;
    private String oldPw;
    private String newPw;
}