package petmily.domain.adopt_review.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class AdoptReviewWriteForm {

    private int mNumber;
    private String title;
    private String content;
    private String imgPath;
    private MultipartFile file;
}