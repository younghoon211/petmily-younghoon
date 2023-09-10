package kh.petmily.domain.abandoned_animal.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AdoptTempSubmitForm {

    private int mNumber;
    private int abNumber;
    private String residence;
    private String maritalStatus;
    private String job;
    private Date tempDate;
    private int tempPeriod;
    private String adoptOrTemp;
}
