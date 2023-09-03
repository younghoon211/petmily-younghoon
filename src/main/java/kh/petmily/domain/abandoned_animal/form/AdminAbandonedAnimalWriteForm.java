package kh.petmily.domain.abandoned_animal.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AdminAbandonedAnimalWriteForm {

    private int sNumber;
    private String name;
    private String species;
    private String kind;
    private char gender;
    private int age;
    private float weight;
    private String imgPath;
    private MultipartFile file;
    private String location;
    private Date admissionDate;
    private String uniqueness;
    private String description;
    private String animalState;

    // 입양, 임보 추가
    private int mNumber;
    private String residence;
    private String maritalStatus;
    private String job;

    // 임보 추가
    private Date tempDate;
    private int tempPeriod;
}