package kh.petmily.domain.adopt_review;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Blob;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class AdoptReview implements DomainObj {

    private int bNumber; // 게시판 번호
    private int mNumber;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private Blob video;
    private LocalDateTime wrTime;
    private final String checkPublic = "Y";
    private int viewCount;

    public AdoptReview(int mNumber, String kindOfBoard, String title, String content, String imgPath) {
        this.mNumber = mNumber;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
    }

    public AdoptReview(int bNumber, int mNumber, String title, String content, String imgPath, LocalDateTime wrTime) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
    }
}