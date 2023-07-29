package kh.petmily.domain.adopt_review.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AdoptReviewDetailForm {

    private int bNumber;
    private int mNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private String imgPath;
    private Date wrTime;
    private int viewCount;

    public AdoptReviewDetailForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, String imgPath, Date wrTime, int viewCount) {
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
}