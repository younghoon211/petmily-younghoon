package kh.petmily.domain.abandoned_animal.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AbandonedAnimalListForm {

    private int abNumber;
    private int sNumber; // 보호소 번호
    private String name;
    private String species;
    private String kind;
    private char gender;
    private int age;
    private float weight;
    private String imgPath;
    private String location;
    private Date admissionDate;
    private String uniqueness;
    private String description;
    private String animalState;
    private String groupName;
    private String shelterLocation;

    public AbandonedAnimalListForm(int abNumber, int sNumber, String name, String species, String kind, char gender, int age, float weight, String imgPath, String location, Date admissionDate, String uniqueness, String description, String animalState, String groupName, String shelterLocation) {
        this.abNumber = abNumber;
        this.sNumber = sNumber;
        this.name = name;
        this.species = species;
        this.kind = kind;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.imgPath = imgPath;
        this.location = location;
        this.admissionDate = admissionDate;
        this.uniqueness = uniqueness;
        this.description = description;
        this.animalState = animalState;
        this.groupName = groupName;
        this.shelterLocation = shelterLocation;
    }
}