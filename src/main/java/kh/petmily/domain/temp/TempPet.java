package kh.petmily.domain.temp;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@NoArgsConstructor
@ToString
public class TempPet implements DomainObj {

    private int tNumber; // 임보 번호
    private int abNumber;
    private int mNumber;
    private Date tempDate;
    private int tempPeriod;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;

    public TempPet(int abNumber, int mNumber, Date tempDate, int tempPeriod, String residence, String maritalStatus, String job) {
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.tempDate = tempDate;
        this.tempPeriod = tempPeriod;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
    }

    public TempPet(int abNumber, int mNumber, Date tempDate, int tempPeriod, String residence, String maritalStatus, String job, String status) {
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.tempDate = tempDate;
        this.tempPeriod = tempPeriod;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
        this.status = status;
    }

    public TempPet(int tNumber, int abNumber, int mNumber, Date tempDate, int tempPeriod, String residence, String maritalStatus, String job, String status) {
        this.tNumber = tNumber;
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.tempDate = tempDate;
        this.tempPeriod = tempPeriod;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
        this.status = status;
    }
}