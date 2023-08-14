package kh.petmily.domain.donation.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDonationWriteForm {

    private int abNumber;
    private int mNumber;
    private int donaSum;
    private String bank;
    private String accountHolder;
    private String accountNumber;
}