package petmily.domain.adopt.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MypageAdoptListForm {

    private int pk;
    private String abName;
    private String status;

    public MypageAdoptListForm(int adNumber, String abName, String status) {
        this.pk = adNumber;
        this.abName = abName;
        this.status = status;
    }
}