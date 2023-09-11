package petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LookBoardModifyForm {

    @NotNull
    private Integer laNumber;

    private int mNumber;
    private String species;
    private String kind;
    private String location;
    private String imgPath;
    private MultipartFile file;
    private String title;
    private String content;
    private String wrTime;

    public LookBoardModifyForm(int laNumber, int mNumber, String species, String kind, String location, String imgPath, String title, String content, String wrTime) {
        this.laNumber = laNumber;
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