package kh.petmily.domain.adopt_review.form;

import lombok.Data;

import java.sql.Date;

@Data
public class AdoptReviewListForm {
    private int bNumber;
    private int mNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private Date wrTime;
    private String checkPublic;
    private int viewCount;

    public AdoptReviewListForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, String imgPath, Date wrTime, String checkPublic, int viewCount) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
        this.viewCount = viewCount;
    }

    public int getbNumber() {
        return bNumber;
    }

    public int getBNumber() {
        return bNumber;
    }
}