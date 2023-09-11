package petmily.service;

import petmily.domain.reply.form.ReplyListForm;
import petmily.domain.reply.form.ReplyModifyForm;
import petmily.domain.reply.form.ReplyWriteForm;

import java.util.List;

public interface ReplyService {

    void write(ReplyWriteForm form);

    void modify(ReplyModifyForm form);

    void delete(int brNumber);

    List<ReplyListForm> getListPage(int bNumber);
}