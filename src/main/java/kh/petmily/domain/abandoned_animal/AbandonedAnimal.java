package kh.petmily.domain.abandoned_animal;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;
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
    private char gender;
    private int age;
    private float weight;
    private String imgPath;
    private String location;
    private Date admissionDate;
    private String uniqueness;
    private String description;
    private Blob video;
    private String animalState;

    public AbandonedAnimal(int sNumber, String name, String species, String kind, char gender, int age, float weight, String imgPath, String location, Date admissionDate, String uniqueness, String description, String animalState) {
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

    public AbandonedAnimal(int abNumber, int sNumber, String name, String species, String kind, char gender, int age, float weight, String imgPath, String location, Date admissionDate, String uniqueness, String description, String animalState) {
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