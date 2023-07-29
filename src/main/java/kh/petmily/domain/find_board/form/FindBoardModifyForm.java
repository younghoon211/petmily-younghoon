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

    private String species;
    private String kind;
    private String location;
    private String imgPath;
    private MultipartFile file;
    private String title;
    private String content;

    public FindBoardModifyForm(int faNumber, String species, String kind, String location, String imgPath, String title, String content) {
        this.faNumber = faNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }
}