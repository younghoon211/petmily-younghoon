package petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LookBoardPageForm {

    private int total;
    private int currentPage;
    private List<LookBoardListForm> content;
    private int totalPages;
    private int startPage;
    private int endPage;

    public LookBoardPageForm(int total, int currentPage, int size, List<LookBoardListForm> content) {
        this.total = total;
        this.currentPage = currentPage;
        this.content = content;

        if (total == 0) {
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        } else {
            totalPages = total / size;

            if (total % size > 0) {
                totalPages++;
            }

            int modVal = currentPage % 5;
            startPage = currentPage / 5 * 5 + 1;

            if (modVal == 0) startPage -= 5;

            endPage = startPage + 4;

            if (endPage > totalPages) endPage = totalPages;
        }
    }
}