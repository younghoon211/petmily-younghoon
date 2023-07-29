package kh.petmily.domain.donation.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDonationListForm {

    private int dNumber;
    private int abNumber;
    private String animalName;
    private int mNumber;
    private String memberName;
    private int donaSum;
    private String bank;
    private String accountHolder;
    private String accountNumber;

    public AdminDonationListForm(int dNumber, int abNumber, String animalName, int mNumber, String memberName, int donaSum, String bank, String accountHolder, String accountNumber) {
        this.dNumber = dNumber;
        this.abNumber = abNumber;
        this.animalName = animalName;
        this.mNumber = mNumber;
        this.memberName = memberName;
        this.donaSum = donaSum;
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }
}