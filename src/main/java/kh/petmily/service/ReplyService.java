package kh.petmily.service;

import kh.petmily.domain.reply.form.ReadReplyForm;
import kh.petmily.domain.reply.form.ReplyModifyForm;
import kh.petmily.domain.reply.form.ReplyWriteForm;

import java.util.List;

public interface ReplyService {

    void write(ReplyWriteForm form);

    void modify(ReplyModifyForm form);

    void delete(int brNumber);

    List<ReadReplyForm> getList(int bNumber);
}