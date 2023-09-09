package kh.petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AdminBoardConditionForm {

    int pageNo = 1;

    @NotBlank
    String kindOfBoard;

    String condition;
    String keyword;
    String species;
    String animalState;
}