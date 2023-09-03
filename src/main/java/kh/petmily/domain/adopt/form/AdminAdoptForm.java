package kh.petmily.domain.adopt.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminAdoptForm {

    private int mNumber;
    private int adNumber;
    private int abNumber;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;
}

