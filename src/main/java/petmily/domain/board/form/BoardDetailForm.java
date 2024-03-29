package petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDetailForm {

    private int bNumber;
    private int mNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private String wrTime;
    private String checkPublic;
    private int viewCount;

    public BoardDetailForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, String wrTime, String checkPublic, int viewCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
        this.viewCount = viewCount;
    }
}