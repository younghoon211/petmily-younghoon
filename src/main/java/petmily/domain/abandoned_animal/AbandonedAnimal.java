package petmily.domain.abandoned_animal;

import petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@NoArgsConstructor
@ToString
public class AbandonedAnimal implements DomainObj {

    private int abNumber; // 유기동물 번호
    private int sNumber;
    private String name;
    private String species;
    private String kind;
    private String gender;
    private int age;
    private double weight;
    private String imgPath;
    private String location;
    private Date admissionDate;
    private String uniqueness;
    private String description;
    private String animalState;

    public AbandonedAnimal(int sNumber, String name, String species, String kind, String gender, int age, double weight, String imgPath, String location, Date admissionDate, String uniqueness, String description, String animalState) {
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
    }

    public AbandonedAnimal(int abNumber, int sNumber, String name, String species, String kind, String gender, int age, double weight, String imgPath, String location, Date admissionDate, String uniqueness, String description, String animalState) {
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
    }
}