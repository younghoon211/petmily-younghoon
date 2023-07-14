package kh.petmily.domain.adopt.form;

import lombok.Data;

@Data
public class MypageAdoptListForm {
    int pk;
    String abName;
    String status;

    public MypageAdoptListForm(int adNumber, String abName, String status) {
        this.pk = adNumber;
        this.abName = abName;
        this.status = status;
    }
}