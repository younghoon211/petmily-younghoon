package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.donation.form.DonationCreateForm;
import kh.petmily.domain.donation.form.DonationDetailForm;
import kh.petmily.domain.donation.form.DonationModifyForm;
import kh.petmily.domain.donation.form.DonationPageForm;

public interface DonateService {

    DonationPageForm getDonationPage(int pageNo);

    DonationDetailForm getDonation(int dNumber);

    DonationModifyForm getDonationModify(int dNumber);

    void create(DonationCreateForm donationCreateForm);

    void modify(DonationModifyForm donationModifyForm);

    void delete(int dNumber);

    void donate(DonateSubmitForm donateSubmitForm);
}
