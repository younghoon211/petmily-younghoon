package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class TempForm {

    private int tNumber;
    private int abNumber;
    private int mNumber;
    private Date tempDate;
    private int tempPeriod;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;
}
