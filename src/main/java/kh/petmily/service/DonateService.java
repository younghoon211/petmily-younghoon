package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.donation.form.AdminDonationModifyForm;
import kh.petmily.domain.donation.form.AdminDonationPageForm;
import kh.petmily.domain.donation.form.AdminDonationWriteForm;

public interface DonateService {

    void donate(DonateSubmitForm form);

    void create(AdminDonationWriteForm form);

    AdminDonationPageForm getListPage(int pageNo);

    AdminDonationModifyForm getModifyForm(int dNumber);

    void modify(AdminDonationModifyForm form);

    void delete(int dNumber);
}
