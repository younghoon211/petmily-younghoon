package kh.petmily.domain.adopt.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AdminAdoptForm {

    private int adNumber;
    private int mNumber;
    private int abNumber;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;
}
