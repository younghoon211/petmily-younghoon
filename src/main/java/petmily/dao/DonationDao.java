package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.donation.Donation;
import petmily.domain.admin_form.DonationListForm;
import petmily.mapper.AbandonedAnimalMapper;
import petmily.mapper.DonationMapper;
import petmily.mapper.MemberMapper;
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

    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    public List<DonationListForm> selectIndex(int start, int end, String keyword) {
        List<DonationListForm> donationListForms = new ArrayList<>();
        List<Donation> donations = mapper.selectIndex(start, end, keyword);

        for (Donation donation : donations) {
            DonationListForm listForm = new DonationListForm(
                    donation.getDNumber(),
                    donation.getAbNumber(),
                    donation.getMNumber(),
                    donation.getDonaSum(),
                    donation.getBank(),
                    donation.getAccountHolder(),
                    donation.getAccountNumber(),
                    getAnimalName(donation.getAbNumber()),
                    getMemberId(donation.getAbNumber()),
                    getMemberName(donation.getMNumber())
            );

            donationListForms.add(listForm);
        }

        return donationListForms;
    }

    private String getAnimalName(int abNumber) {
        return abandonedAnimalMapper.selectByPk(abNumber).getName();
    }

    private String getMemberId(int mNumber) {
        return memberMapper.selectMemberId(mNumber);
    }

    private String getMemberName(int mNumber) {
        return memberMapper.selectName(mNumber);
    }
}
