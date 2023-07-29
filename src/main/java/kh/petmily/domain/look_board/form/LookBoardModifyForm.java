package kh.petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LookBoardModifyForm {

    @NotNull
    private Integer laNumber;

    private String species;
    private String kind;
    private String location;
    private String imgPath;
    private MultipartFile file;
    private String title;
    private String content;

    public LookBoardModifyForm(int laNumber, String species, String kind, String location, String imgPath, String title, String content) {
        this.laNumber = laNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }
}