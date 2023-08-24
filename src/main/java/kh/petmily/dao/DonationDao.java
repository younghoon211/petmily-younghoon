package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.donation.Donation;
import kh.petmily.domain.donation.form.AdminDonationListForm;
import kh.petmily.mapper.AbandonedAnimalMapper;
import kh.petmily.mapper.DonationMapper;
import kh.petmily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DonationDao implements BasicDao {

    private final DonationMapper mapper;
    private final MemberMapper memberMapper;
    private final AbandonedAnimalMapper abandonedAnimalMapper;

    @Override
    public Donation findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Donation) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Donation) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<AdminDonationListForm> selectIndex(int start, int end) {
        List<AdminDonationListForm> adminDonationListForms = new ArrayList<>();
        List<Donation> donations = mapper.selectIndex(start, end);

        for (Donation donation : donations) {
            AdminDonationListForm listForm = new AdminDonationListForm(
                    donation.getDNumber(),
                    donation.getAbNumber(),
                    findAnimalName(donation.getAbNumber()),
                    donation.getMNumber(),
                    findMemberName(donation.getMNumber()),
                    donation.getDonaSum(),
                    donation.getBank(),
                    donation.getAccountHolder(),
                    donation.getAccountNumber()
            );

            adminDonationListForms.add(listForm);
        }

        return adminDonationListForms;
    }

    private String findAnimalName(int abNumber) {
        return abandonedAnimalMapper.selectByPk(abNumber).getName();
    }

    private String findMemberName(int mNumber) {
        return memberMapper.selectName(mNumber);
    }
}
