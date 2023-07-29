package kh.petmily.domain.find_board;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

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
    private Date wrTime;
    private String title;
    private String content;
    private int viewCount;

    public FindBoard(int faNumber, String species, String kind, String location, String imgPath, String title, String content) {
        this.faNumber = faNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
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