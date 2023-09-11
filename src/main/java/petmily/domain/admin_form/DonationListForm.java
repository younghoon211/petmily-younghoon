package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonationListForm {

    private int dNumber;
    private int abNumber;
    private int mNumber;
    private int donaSum;
    private String bank;
    private String accountHolder;
    private String accountNumber;
    private String animalName;
    private String memberId;
    private String memberName;

    public DonationListForm(int dNumber, int abNumber, int mNumber, int donaSum, String bank, String accountHolder, String accountNumber, String animalName, String memberId, String memberName) {
        this.dNumber = dNumber;
        this.abNumber = abNumber;
        this.mNumber = mNumber;
        this.donaSum = donaSum;
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.animalName = animalName;
        this.memberId = memberId;
        this.memberName = memberName;
    }
}