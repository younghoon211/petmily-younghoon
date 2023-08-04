package kh.petmily.domain.shelter.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShelterWriteForm {

    private int sNumber;
    private String groupName;
    private String location;
    private String phone;
}
