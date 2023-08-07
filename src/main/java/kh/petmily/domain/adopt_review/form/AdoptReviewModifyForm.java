package kh.petmily.domain.adopt_review.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AdoptReviewModifyForm {

    private int mNumber;

    @NotNull
    private Integer bNumber;

    private String title;
    private String content;
    private String imgPath;
    private MultipartFile file;
    private String wrTime;

    public AdoptReviewModifyForm(int mNumber, int bNumber, String title, String content, String imgPath, String wrTime) {
        this.mNumber = mNumber;
        this.bNumber = bNumber;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
    }
}