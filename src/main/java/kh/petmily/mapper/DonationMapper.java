package kh.petmily.mapper;

import kh.petmily.domain.donation.Donation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationMapper {

    // =======BasicMapper 메소드=======
    Donation selectByPk(int pk);

    void insert(Donation donation);

    void update(Donation donation);

    void delete(int pk);
    // ===============================

    int selectCount(String keyword);

    List<Donation> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword
    );
}
