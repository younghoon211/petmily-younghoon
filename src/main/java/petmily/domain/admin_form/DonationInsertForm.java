package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonationInsertForm {

    private int abNumber;
    private int mNumber;
    private int donaSum;
    private String bank;
    private String accountHolder;
    private String accountNumber;
}
