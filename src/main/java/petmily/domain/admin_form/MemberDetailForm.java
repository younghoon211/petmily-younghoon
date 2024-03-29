package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDetailForm {

    private int mNumber;
    private String id;
    private String pw;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    private String grade;

    public MemberDetailForm(int mNumber, String id, String pw, String name, String birth, String gender, String email, String phone, String grade) {
        this.mNumber = mNumber;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
    }
}
