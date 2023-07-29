package kh.petmily.domain.adopt_review.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AdoptReviewModifyForm {

    @NotNull
    private Integer bNumber;

    private String title;
    private String content;
    private String imgPath;
    private MultipartFile file;

    public AdoptReviewModifyForm(int bNumber, String title, String content, String imgPath) {
        this.bNumber = bNumber;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
    }
}