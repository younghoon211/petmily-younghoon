package petmily.domain.admin_form;

import lombok.Data;

import java.util.List;

@Data
public class MemberPageForm {

    private int total;
    private int currentPage;
    private List<MemberDetailForm> content;
    private int totalPages;
    private int startPage;
    private int endPage;

    public MemberPageForm(int total, int currentPage, int size, List<MemberDetailForm> content) {
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