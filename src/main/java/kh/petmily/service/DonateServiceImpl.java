package kh.petmily.service;

import kh.petmily.dao.DonationDao;
import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.donation.Donation;
import kh.petmily.domain.admin_form.DonationListForm;
import kh.petmily.domain.admin_form.DonationUpdateForm;
import kh.petmily.domain.admin_form.DonationPageForm;
import kh.petmily.domain.admin_form.DonationInsertForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DonateServiceImpl implements DonateService {

    private final DonationDao donationDao;
    private int size = 10;

    // ===================== Create =====================
    // 후원하기 (해당 메소드 제외한 나머지 : 관리자)
    @Override
    public void donate(DonateSubmitForm form) {
        Donation donation = toSubmit(form);
        donationDao.insert(donation);
    }

    // 후원 추가
    @Override
    public void insert(DonationInsertForm form) {
        Donation donation = toInsert(form);
        donationDao.insert(donation);
    }

    // ===================== Read =====================
    // 후원 리스트 페이지
    @Override
    public DonationPageForm getListPage(int pageNo, String keyword) {
        int total = donationDao.selectCount(keyword);
        List<DonationListForm> content = donationDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword);

        return new DonationPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public DonationUpdateForm getUpdateForm(int pk) {
        Donation donation = donationDao.findByPk(pk);

        return toUpdateForm(donation);
    }

    // 수정하기
    @Override
    public void update(DonationUpdateForm form) {
        Donation donation = toUpdate(form);
        donationDao.update(donation);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        donationDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================


    private Donation toSubmit(DonateSubmitForm form) {
        return new Donation(
                form.getAbNumber(),
                form.getMNumber(),
                form.getDonaSum(),
                form.getBank(),
                form.getAccountHolder(),
                form.getAccountNumber()
        );
    }

    private Donation toInsert(DonationInsertForm form) {
        return new Donation(
                form.getAbNumber(),
                form.getMNumber(),
                form.getDonaSum(),
                form.getBank(),
                form.getAccountHolder(),
                form.getAccountNumber()
        );
    }

    private DonationUpdateForm toUpdateForm(Donation donation) {
        return new DonationUpdateForm(
                donation.getDNumber(),
                donation.getAbNumber(),
                donation.getMNumber(),
                donation.getDonaSum(),
                donation.getBank(),
                donation.getAccountHolder(),
                donation.getAccountNumber()
        );
    }

    private Donation toUpdate(DonationUpdateForm form) {
        return new Donation(
                form.getDNumber(),
                form.getAbNumber(),
                form.getMNumber(),
                form.getDonaSum(),
                form.getBank(),
                form.getAccountHolder(),
                form.getAccountNumber()
        );
    }
}
