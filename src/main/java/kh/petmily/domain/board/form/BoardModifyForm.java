package kh.petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class BoardModifyForm {

    @NotNull
    private Integer bNumber;

    private String title;
    private String content;
    private String checkPublic;

    @NotBlank
    private String kindOfBoard;

    public BoardModifyForm(int bNumber, String title, String content, String checkPublic, String kindOfBoard) {
        this.bNumber = bNumber;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
        this.kindOfBoard = kindOfBoard;
    }
}
