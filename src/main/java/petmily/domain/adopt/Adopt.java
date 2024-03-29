package petmily.domain.adopt;

import petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Adopt implements DomainObj {

    private int adNumber; // 입양 번호
    private int mNumber;
    private int abNumber;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;

    public Adopt(int mNumber, int abNumber, String residence, String maritalStatus, String job) {
        this.mNumber = mNumber;
        this.abNumber = abNumber;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
    }

    public Adopt(int adNumber, int mNumber, int abNumber, String residence, String maritalStatus, String job, String status) {
        this.adNumber = adNumber;
        this.mNumber = mNumber;
        this.abNumber = abNumber;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
        this.status = status;
    }

    public Adopt(int mNumber, int abNumber, String residence, String maritalStatus, String job, String status) {
        this.mNumber = mNumber;
        this.abNumber = abNumber;
        this.residence = residence;
        this.maritalStatus = maritalStatus;
        this.job = job;
        this.status = status;
    }
}