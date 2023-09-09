package kh.petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInsertForm {

    private String id;
    private String pw;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
}
