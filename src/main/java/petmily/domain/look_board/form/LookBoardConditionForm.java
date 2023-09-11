package petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LookBoardConditionForm {

    int pageNo = 1;
    String species;
    String animalState;
    String keyword;

    @NotBlank
    String sort;
}