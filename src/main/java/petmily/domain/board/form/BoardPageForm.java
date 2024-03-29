package petmily.domain.board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardPageForm {

    private int total;
    private int currentPage;
    private List<BoardListForm> content;
    private int totalPages;
    private int startPage;
    private int endPage;

    public BoardPageForm(int total, int currentPage, int size, List<BoardListForm> content) {
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

            if (modVal == 0) {
                startPage -= 5;
            }

            endPage = startPage + 4;

            if (endPage > totalPages) {
                endPage = totalPages;
            }
        }
    }
}
