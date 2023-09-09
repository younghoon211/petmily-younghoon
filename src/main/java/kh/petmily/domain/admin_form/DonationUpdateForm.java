package kh.petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonationUpdateForm {

    private int dNumber;
    private int abNumber;
    private int mNumber;
    private int donaSum;
    private String bank;
    private String accountHolder;
    private String accountNumber;

    public DonationUpdateForm(int dNumber, int abNumber, int mNumber, int donaSum, String bank, String accountHolder, String accountNumber) {
        this.dNumber = dNumber;
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.donaSum = donaSum;
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }
}
