package kh.petmily.domain.look_board;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class LookBoard implements DomainObj {

    private int laNumber;
    private int mNumber;
    private String species;
    private String kind;
    private String location;
    private String animalState;
    private String imgPath;
    private LocalDateTime wrTime;
    private String title;
    private String content;
    private int viewCount;

    public LookBoard(int laNumber, int mNumber, String species, String kind, String location, String imgPath, String title, String content, LocalDateTime wrTime) {
        this.laNumber = laNumber;
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
    }

    public LookBoard(String species, int mNumber, String kind, String location, String imgPath, String title, String content) {
        this.species = species;
        this.mNumber = mNumber;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }
}