package kh.petmily.domain.look_board.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LookBoardListForm {

    private int laNumber;
    private int mNumber;
    private String memberId;
    private String name;
    private String species;
    private String kind;
    private String location;
    private String animalState;
    private String imgPath;
    private String wrTime;
    private String title;
    private int viewCount;
    private String sort;

    public LookBoardListForm(int laNumber, int mNumber, String name, String species, String kind, String location, String animalState, String imgPath, String wrTime, String title) {
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
    }

    public LookBoardListForm(int laNumber, int mNumber, String name, String species, String kind, String location, String animalState, String imgPath, String wrTime, String title, int viewCount) {
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
        this.viewCount = viewCount;
    }

    public LookBoardListForm(int laNumber, int mNumber, String memberId, String name, String species, String kind, String location, String animalState, String imgPath, String wrTime, String title, int viewCount) {
        this.laNumber = laNumber;
        this.mNumber = mNumber;
        this.memberId = memberId;
        this.name = name;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.animalState = animalState;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.title = title;
        this.viewCount = viewCount;
    }
}