package kh.petmily.domain.board;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;
import java.sql.Date;

@Getter
@NoArgsConstructor
@ToString
public class Board implements DomainObj {

    private int bNumber;
    private int mNumber;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private Blob video;
    private Date wrTime;
    private String checkPublic;
    private int viewCount;
    private int replyCount;

    public Board(int mNumber, String kindOfBoard, String title, String content, String checkPublic) {
        this.mNumber = mNumber;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
    }

    public Board(int bNumber, String title, String content, String checkPublic) {
        this.bNumber = bNumber;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
    }
}
