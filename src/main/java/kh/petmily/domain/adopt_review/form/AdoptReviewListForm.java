package kh.petmily.domain.adopt_review.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdoptReviewListForm {

    private int bNumber;
    private int mNumber;
    private String memberId;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private String wrTime;
    private int viewCount;

    public AdoptReviewListForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, String imgPath, String wrTime, int viewCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.viewCount = viewCount;
    }
    public AdoptReviewListForm(int bNumber, int mNumber, String memberId, String name, String kindOfBoard, String title, String content, String imgPath, String wrTime, int viewCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.memberId = memberId;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.viewCount = viewCount;
    }
}