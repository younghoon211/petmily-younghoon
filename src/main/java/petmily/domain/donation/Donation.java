package petmily.domain.donation;

import petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Donation implements DomainObj {

    private int dNumber;
    private int abNumber;
    private int mNumber;
    private int donaSum;
    private String bank;
    private String accountHolder;
    private String accountNumber;

    public Donation(int abNumber, int mNumber, int donaSum, String bank, String accountHolder, String accountNumber) {
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.donaSum = donaSum;
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }

    public Donation(int dNumber, int abNumber, int mNumber, int donaSum, String bank, String accountHolder, String accountNumber) {
        this.dNumber = dNumber;
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.donaSum = donaSum;
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }
}
