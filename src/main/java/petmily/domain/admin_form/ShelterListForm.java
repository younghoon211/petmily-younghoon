package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShelterListForm {

    private int sNumber;
    private String groupName;
    private String location;
    private String phone;

    public ShelterListForm(int sNumber, String groupName, String location, String phone) {
        this.sNumber = sNumber;
        this.groupName = groupName;
        this.location = location;
        this.phone = phone;
    }
}