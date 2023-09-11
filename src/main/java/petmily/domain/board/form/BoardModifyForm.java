package petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class BoardModifyForm {

    @NotNull
    private Integer bNumber;

    private int mNumber;
    private String title;
    private String content;
    private String checkPublic;

    @NotBlank
    private String kindOfBoard;

    private String wrTime;

    public BoardModifyForm(int bNumber, int mNumber, String title, String content, String checkPublic, String kindOfBoard, String wrTime) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
        this.kindOfBoard = kindOfBoard;
        this.wrTime = wrTime;
    }
}
