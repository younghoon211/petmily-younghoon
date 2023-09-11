package petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardWriteForm {

    private int mNumber;
    private String kindOfBoard;
    private String title;
    private String content;
    private String checkPublic;
}