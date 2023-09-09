package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.form.DonateSubmitForm;
import kh.petmily.domain.admin_form.DonationUpdateForm;
import kh.petmily.domain.admin_form.DonationPageForm;
import kh.petmily.domain.admin_form.DonationInsertForm;

public interface DonateService {

    void donate(DonateSubmitForm form);

    void insert(DonationInsertForm form);

    DonationPageForm getListPage(int pageNo, String keyword);

    DonationUpdateForm getUpdateForm(int dNumber);

    void update(DonationUpdateForm form);

    void delete(int dNumber);
}
