package petmily.mapper;

import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.abandoned_animal.form.AbandonedAnimalConditionForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AbandonedAnimalMapper {

    // =======BasicMapper 메소드=======
    AbandonedAnimal selectByPk(int pk);

    void insert(AbandonedAnimal obj);

    void update(AbandonedAnimal obj);

    void delete(int pk);

    // ========== 회원 페이지 ==========
    int selectCountWithCondition(AbandonedAnimalConditionForm form);

    List<AbandonedAnimal> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("gender") String gender,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword,
            @Param("sort") String sort
    );

    int selectCountAdopted(AbandonedAnimalConditionForm form);

    List<AbandonedAnimal> selectIndexAdopted(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("gender") String gender,
            @Param("keyword") String keyword,
            @Param("sort") String sort
    );

    // ========== 관리자 페이지 ==========
    int selectCount(AbandonedAnimalConditionForm form);

    List<AbandonedAnimal> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("gender") String gender,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword);

    List<AbandonedAnimal> selectAll();

    List<AbandonedAnimal> selectAllOnlyProtected();

    int selectByPkMax();

    // ============== 입양 ==============
    List<AbandonedAnimal> selectAllAdoptWait();

    List<AbandonedAnimal> selectAllAdoptComplete();

    void updateToAdopt();

    void updateToProtectInAdopt();

    void updateToProtectForDeleteInAdopt(int adNumber);

    // ============== 임보 ==============
    List<AbandonedAnimal> selectAllTempWait();

    List<AbandonedAnimal> selectAllTempComplete();

    void updateToTemp();

    void updateToProtectInTemp();

    void updateToProtectForDeleteInTemp(int tNumber);
}
