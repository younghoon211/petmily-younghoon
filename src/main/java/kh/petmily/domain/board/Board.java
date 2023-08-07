package kh.petmily.domain.board;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;
import java.time.LocalDateTime;

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
    private LocalDateTime wrTime;
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

    public Board(int bNumber, int mNumber, String title, String content, String checkPublic, LocalDateTime wrTime) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.title = title;
        this.content = content;
        this.checkPublic = checkPublic;
        this.wrTime = wrTime;
    }
}
