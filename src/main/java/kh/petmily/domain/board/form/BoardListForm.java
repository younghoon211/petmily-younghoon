package kh.petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BoardListForm {

    private int bNumber;
    private int mNumber;
    private String memberId;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private Date wrTime;
    private String checkPublic;
    private int viewCount;
    private int replyCount;

    public BoardListForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, Date wrTime, String checkPublic, int viewCount, int replyCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
    }

    public BoardListForm(int bNumber, int mNumber, String memberId, String name, String kindOfBoard, String title, String content, Date wrTime, String checkPublic, int viewCount, int replyCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.memberId = memberId;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
    }
}