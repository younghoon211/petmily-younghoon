package kh.petmily.domain.shelter;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Shelter implements DomainObj {

    private int sNumber;
    private String groupName;
    private String location;
    private String phone;

    public Shelter(String groupName, String location, String phone) {
        this.groupName = groupName;
        this.location = location;
        this.phone = phone;
    }

    public Shelter(int sNumber, String groupName, String location, String phone) {
        this.sNumber = sNumber;
        this.groupName = groupName;
        this.location = location;
        this.phone = phone;
    }
}
