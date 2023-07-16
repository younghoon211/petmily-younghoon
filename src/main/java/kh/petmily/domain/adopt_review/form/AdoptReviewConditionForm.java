package kh.petmily.domain.adopt_review.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AdoptReviewConditionForm {

    int pageNo = 1;

    @NotBlank
    String kindOfBoard;

    String searchType;
    String keyword;

    @NotBlank
    String sort;
}