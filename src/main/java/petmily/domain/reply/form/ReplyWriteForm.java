package petmily.domain.reply.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyWriteForm {

    private int bNumber;
    private int mNumber;
    private String reply;

    public int getbNumber() {
        return bNumber;
    }

    public int getmNumber() {
        return mNumber;
    }
}