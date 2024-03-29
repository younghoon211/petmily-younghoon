package petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LookBoardDetailForm {

    private int laNumber;
    private int mNumber;
    private String name;
    private String species;
    private String kind;
    private String location;
    private String animalState;
    private String imgPath;
    private String wrTime;
    private String title;
    private String content;
    private int viewCount;

    public LookBoardDetailForm(int laNumber, int mNumber, String name, String species, String kind, String location, String animalState, String imgPath, String wrTime, String title, String content, int viewCount) {
        this.laNumber = laNumber;
        this.mNumber = mNumber;
        this.name = name;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.animalState = animalState;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}