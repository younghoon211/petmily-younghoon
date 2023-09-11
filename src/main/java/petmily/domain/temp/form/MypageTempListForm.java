package petmily.domain.temp.form;

import lombok.Data;

@Data
public class MypageTempListForm {

    int pk;
    String abName;
    String status;

    public MypageTempListForm(int tNumber, String abName, String status) {
        this.pk = tNumber;
        this.abName = abName;
        this.status = status;
    }
}