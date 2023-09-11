package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShelterUpdateForm {

    private int sNumber;
    private String groupName;
    private String location;
    private String phone;

    public ShelterUpdateForm(int sNumber, String groupName, String location, String phone) {
        this.sNumber = sNumber;
        this.groupName = groupName;
        this.location = location;
        this.phone = phone;
    }
}
