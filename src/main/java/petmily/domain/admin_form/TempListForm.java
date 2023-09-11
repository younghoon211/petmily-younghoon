package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class TempListForm {

    private int tNumber;
    private int abNumber;
    private int mNumber;
    private Date tempDate;
    private int tempPeriod;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;
    private String animalName;
    private String memberName;
    private String memberId;

    public TempListForm(int tNumber, int abNumber, int mNumber, Date tempDate, int tempPeriod, String residence, String maritalStatus, String job, String status, String animalName, String memberName, String memberId) {
        this.tNumber = tNumber;
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.tempDate = tempDate;
        this.tempPeriod = tempPeriod;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
        this.status = status;
        this.animalName = animalName;
        this.memberName = memberName;
        this.memberId = memberId;
    }
}
