package kh.petmily.domain.find_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class FindBoardWriteForm {

    private int mNumber;
    private String species;
    private String kind;
    private String location;
    private String imgPath;
    private MultipartFile file;
    private String title;
    private String content;
}