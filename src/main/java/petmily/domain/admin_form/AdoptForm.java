package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdoptForm {

    private int mNumber;
    private int adNumber;
    private int abNumber;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status;
}

