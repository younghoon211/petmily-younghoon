package kh.petmily.domain.find_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class FindBoardConditionForm {

    int pageNo = 1;
    String species;
    String animalState;
    String keyword;

    @NotBlank
    String sort;
}