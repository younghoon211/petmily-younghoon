package kh.petmily.domain.find_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FindBoardModifyForm {

    @NotNull
    private Integer faNumber;

    private int mNumber;
    private String species;
    private String kind;
    private String location;
    private String imgPath;
    private MultipartFile file;
    private String title;
    private String content;
    private String wrTime;

    public FindBoardModifyForm(int faNumber, int mNumber, String species, String kind, String location, String imgPath, String title, String content, String wrTime) {
        this.faNumber = faNumber;
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
    }
}