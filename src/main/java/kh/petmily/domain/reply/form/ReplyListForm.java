package kh.petmily.domain.reply.form;

import lombok.Data;

@Data
public class ReplyListForm {

    private int brNumber;
    private int mNumber;
    private String reply;
    private String wrTime;
    private String writer;
    private boolean sameWriter;

    public ReplyListForm(int brNumber, int mNumber, String reply, String wrTime, String writer) {
        this.brNumber = brNumber;
        this.mNumber = mNumber;
        this.reply = reply;
        this.wrTime = wrTime;
        this.writer = writer;
    }
}