package kh.petmily.service;

import kh.petmily.domain.reply.form.ReadReplyForm;
import kh.petmily.domain.reply.form.ReplyModifyForm;
import kh.petmily.domain.reply.form.ReplyWriteForm;

import java.util.List;

public interface ReplyService {

    void write(ReplyWriteForm replyWriteForm);

    void modify(ReplyModifyForm replyModifyForm);

    void delete(int brNumber);

    List<ReadReplyForm> getList(int bNumber);
}