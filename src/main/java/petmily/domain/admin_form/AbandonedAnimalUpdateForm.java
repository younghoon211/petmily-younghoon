package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
public class AbandonedAnimalUpdateForm {

    @NotNull
    private Integer abNumber;

    private int sNumber;
    private String name;
    private String species;
    private String kind;
    private String gender;
    private int age;
    private double weight;
    private String imgPath;
    private MultipartFile file;
    private String location;
    private Date admissionDate;
    private String uniqueness;
    private String description;
    private String animalState;

    // 입양, 임보 추가
    private int adNumber;
    private int mNumber;
    private String residence;
    private String maritalStatus;
    private String job;

    // 임보 추가
    private Date tempDate;
    private int tempPeriod;

    public AbandonedAnimalUpdateForm(int abNumber, int sNumber, String name, String species, String kind, String gender, int age, double weight, String imgPath, String location, Date admissionDate, String uniqueness, String description, String animalState) {
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