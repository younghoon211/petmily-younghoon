package kh.petmily.domain.board.form;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDetailForm {
    private int bNumber;
    private int mNumber;
    private String name;
    private String kindOfBoard;
    private String title;
    private String content;
    private Date wrTime;
    private String checkPublic;
    private int viewCount;
    private String condition;
    private String keyword;
    private String sort;

    public BoardDetailForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content, Date wrTime, String checkPublic, int viewCount, String condition, String keyword, String sort) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.kindOfBoard = kindOfBoard;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
        this.checkPublic = checkPublic;
        this.viewCount = viewCount;
        this.condition = condition;
        this.keyword = keyword;
        this.sort = sort;
    }
}