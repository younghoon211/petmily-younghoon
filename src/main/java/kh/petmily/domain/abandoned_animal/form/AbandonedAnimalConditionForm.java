package kh.petmily.domain.abandoned_animal.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AbandonedAnimalConditionForm {

    int pageNo = 1;
    String species;
    String gender;
    String animalState;
    String keyword;

    @NotBlank
    String sort;
}
