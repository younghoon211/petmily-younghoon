package kh.petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShelterInsertForm {

    private int sNumber;
    private String groupName;
    private String location;
    private String phone;
}
