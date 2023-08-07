package kh.petmily.domain.find_board;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class FindBoard implements DomainObj {

    private int faNumber;
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

    public FindBoard(int faNumber, int mNumber, String species, String kind, String location, String imgPath, String title, String content, LocalDateTime wrTime) {
        this.faNumber = faNumber;
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
        this.wrTime = wrTime;
    }

    public FindBoard(String species, int mNumber, String kind, String location, String imgPath, String title, String content) {
        this.species = species;
        this.mNumber = mNumber;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }
}