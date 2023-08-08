package kh.petmily.service;

import kh.petmily.dao.DonationDao;
import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.donation.Donation;
import kh.petmily.domain.donation.form.AdminDonationListForm;
import kh.petmily.domain.donation.form.AdminDonationModifyForm;
import kh.petmily.domain.donation.form.AdminDonationPageForm;
import kh.petmily.domain.donation.form.AdminDonationWriteForm;
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
    public void create(AdminDonationWriteForm form) {
        Donation donation = toWrite(form);
        donationDao.insert(donation);
    }

    // ===================== Read =====================
    // 후원 리스트 페이지
    @Override
    public AdminDonationPageForm getListPage(int pageNo) {
        int total = donationDao.selectCount();
        List<AdminDonationListForm> content = donationDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new AdminDonationPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public AdminDonationModifyForm getModifyForm(int pk) {
        Donation donation = donationDao.findByPk(pk);

        return toModifyForm(donation);
    }

    // 수정하기
    @Override
    public void modify(AdminDonationModifyForm form) {
        Donation donation = toModify(form);
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

    private Donation toWrite(AdminDonationWriteForm form) {
        return new Donation(
                form.getAbNumber(),
                form.getMNumber(),
                form.getDonaSum(),
                form.getBank(),
                form.getAccountHolder(),
                form.getAccountNumber()
        );
    }

    private AdminDonationModifyForm toModifyForm(Donation donation) {
        return new AdminDonationModifyForm(
                donation.getDNumber(),
                donation.getAbNumber(),
                donation.getMNumber(),
                donation.getDonaSum(),
                donation.getBank(),
                donation.getAccountHolder(),
                donation.getAccountNumber()
        );
    }

    private Donation toModify(AdminDonationModifyForm form) {
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
