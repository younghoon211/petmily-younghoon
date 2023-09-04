package kh.petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BoardConditionForm {

    int pageNo = 1;

    @NotBlank
    String kindOfBoard;

    String condition;
    String keyword;

    @NotBlank
    String sort;
}