package petmily.domain.reply;

import petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class Reply implements DomainObj {

    private int brNumber;
    private int bNumber;
    private int mNumber;
    private String reply;
    private LocalDateTime wrTime;

    public Reply(int bNumber, int mNumber, String reply) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.reply = reply;
    }

    public Reply(int brNumber, String reply) {
        this.brNumber = brNumber;
        this.reply = reply;
    }
}