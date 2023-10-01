package petmily.domain.member;

import petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Member implements DomainObj {

    private int mNumber;
    private String id;
    private String pw;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    private String grade;

    public Member(int mNumber, String pw) {
        this.mNumber = mNumber;
        this.pw = pw;
    }

    public Member(int mNumber, String name, String email, String phone) {
        this.mNumber = mNumber;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Member(int mNumber, String pw, String name, String birth, String gender, String email, String phone) {
        this.mNumber = mNumber;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public Member(String id, String pw, String name, String birth, String gender, String email, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
}
