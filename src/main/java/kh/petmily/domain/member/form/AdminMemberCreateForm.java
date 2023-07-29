package kh.petmily.domain.member.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminMemberCreateForm {

    private String id;
    private String pw;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
}
