package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.abandoned_animal.form.AbandonedAnimalConditionForm;
import petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import petmily.domain.admin_form.*;
import petmily.domain.adopt.Adopt;
import petmily.domain.adopt_review.form.AdoptReviewPageForm;
import petmily.domain.board.form.BoardPageForm;
import petmily.domain.find_board.form.FindBoardPageForm;
import petmily.domain.look_board.form.LookBoardPageForm;
import petmily.domain.member.Member;
import petmily.domain.member.form.MemberPageForm;
import petmily.domain.shelter.Shelter;
import petmily.domain.temp.TempPet;
import petmily.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final AbandonedAnimalService abandonedAnimalService;
    private final AdoptReviewService adoptReviewService;
    private final AdoptTempService adoptTempService;
    private final BoardService boardService;
    private final DonateService donateService;
    private final FindBoardService findBoardService;
    private final LookBoardService lookBoardService;
    private final MemberService memberService;
    private final ShelterService shelterService;

    // 관리자 메인 페이지
    @GetMapping
    public String adminPage() {
        return "/admin/admin_page";
    }

    // =============== 회원정보 관리 ===============
    // 회원 리스트
    @GetMapping("/member")
    public String memberList(@RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(required = false) String keyword,
                             Model model) {
        MemberPageForm pageForm = memberService.getAdminListPage(pageNo, keyword);
        model.addAttribute("pageForm", pageForm);

        return "/admin/member/member_list";
    }

    // 회원 추가 (insert)
    @GetMapping("/member/insert")
    public String memberInsertPage() {
        return "admin/member/member_insert";
    }

    @PostMapping("/member/insert")
    public String memberInsert(@ModelAttribute MemberInsertForm memberInsertForm) {
        log.info("POST MemberInsertForm = {}", memberInsertForm);

        memberService.insert(memberInsertForm);

        return "/alert/admin/member_insert";
    }

    // 회원정보 수정 (update)
    @GetMapping("/member/update")
    public String memberUpdatePage(@RequestParam int mNumber, Model model) {
        MemberUpdateForm updateForm = memberService.getUpdateForm(mNumber);
        log.info("GET MemberUpdateForm = {}", updateForm);

        model.addAttribute("updateForm", updateForm);

        return "/admin/member/member_update";
    }

    @PostMapping("/member/update")
    public String memberUpdate(@ModelAttribute MemberUpdateForm updateForm) {
        memberService.update(updateForm);
        log.info("POST MemberUpdateForm = {}", updateForm);

        return "/alert/admin/member_update";
    }

    // 회원정보 삭제 (delete)
    @GetMapping("/member/delete")
    public String memberDelete(@RequestParam int mNumber) {
        memberService.delete(mNumber);

        return "redirect:/admin/member";
    }

    // =============== 게시판 관리 ===============
    // 게시판 리스트 (CUD는 기존 회원 폼 이용)
    @GetMapping("/board")
    public String boardList(@Validated @ModelAttribute AdminBoardConditionForm conditionForm,
                            Model model) {
        String kindOfBoard = conditionForm.getKindOfBoard();

        if ("free".equals(kindOfBoard)) {
            BoardPageForm freeBoardForm = boardService.getAdminListPage(conditionForm);
            model.addAttribute("boardForm", freeBoardForm);
        } else if ("inquiry".equals(kindOfBoard)) {
            BoardPageForm inquiryBoardForm = boardService.getAdminListPage(conditionForm);
            model.addAttribute("boardForm", inquiryBoardForm);
        } else if ("adoptReview".equals(kindOfBoard)) {
            AdoptReviewPageForm adoptReviewBoardForm = adoptReviewService.getAdminListPage(conditionForm);
            model.addAttribute("boardForm", adoptReviewBoardForm);
        } else if ("find".equals(kindOfBoard)) {
            FindBoardPageForm findBoardForm = findBoardService.getAdminListPage(conditionForm);
            model.addAttribute("boardForm", findBoardForm);
        } else if ("look".equals(kindOfBoard)) {
            LookBoardPageForm lookBoardForm = lookBoardService.getAdminListPage(conditionForm);
            model.addAttribute("boardForm", lookBoardForm);
        }

        return "/admin/board/board_list";
    }

    // =============== 유기동물 관리 ===============
    // 유기동물 리스트
    @GetMapping("/abandonedAnimal")
    public String abandonedAnimalList(@ModelAttribute AbandonedAnimalConditionForm conditionForm,
                                      @RequestParam(defaultValue = "1") int pageNo,
                                      Model model) {
        AbandonedAnimalPageForm pageForm = abandonedAnimalService.getAdminListPage(conditionForm, pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/abandoned.animal/abandoned_animal_list";
    }

    // 유기동물 추가 (insert)
    @GetMapping("/abandonedAnimal/insert")
    public String abandonedAnimalInsertPage(Model model) {
        List<Shelter> shelters = shelterService.getShelterListNotSNumber0();
        List<Member> members = memberService.getMemberList();
        List<String> residences = abandonedAnimalService.getResidenceList();

        model.addAttribute("shelters", shelters);
        model.addAttribute("members", members);
        model.addAttribute("residences", residences);

        return "/admin/abandoned.animal/abandoned_animal_insert";
    }

    @PostMapping("/abandonedAnimal/insert")
    public String abandonedAnimalInsert(@ModelAttribute AbandonedAnimalInsertForm insertForm,
                                        HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);
        String newFile = abandonedAnimalService.storeFile(insertForm.getFile(), fullPath);

        if (newFile == null) {
            log.info("이미지파일 업로드 x");
        } else {
            log.info("업로드 될 newFile = {}", newFile);
        }

        insertForm.setImgPath(newFile);
        log.info("POST AbandonedAnimalInsertForm = {}", insertForm);

        abandonedAnimalService.insert(insertForm);

        if ("입양".equals(insertForm.getAnimalState())) {
            abandonedAnimalService.insertWithAdopt(insertForm);
        } else if ("임보".equals(insertForm.getAnimalState())) {
            abandonedAnimalService.insertWithTemp(insertForm);
        }

        return "/alert/admin/abandoned_animal_insert";
    }

    // 유기동물 수정 (update)
    @GetMapping("/abandonedAnimal/update")
    public String abandonedAnimalUpdatePage(@RequestParam int abNumber, Model model) {
        AbandonedAnimalUpdateForm updateForm = abandonedAnimalService.getUpdateForm(abNumber);
        log.info("GET AbandonedAnimalUpdateForm = {}", updateForm);

        List<Shelter> shelters = shelterService.getShelterListNotSNumber0();
        List<Member> members = memberService.getMemberList();
        Adopt selectedAdopt = abandonedAnimalService.getAdoptCompleteByPk(abNumber);
        TempPet selectedTemp = abandonedAnimalService.getTempCompleteByPk(abNumber);
        List<String> residences = abandonedAnimalService.getResidenceList();

        model.addAttribute("updateForm", updateForm);
        model.addAttribute("shelters", shelters);
        model.addAttribute("members", members);
        model.addAttribute("selectedAdopt", selectedAdopt);
        model.addAttribute("selectedTemp", selectedTemp);
        model.addAttribute("residences", residences);

        return "/admin/abandoned.animal/abandoned_animal_update";
    }

    @PostMapping("/abandonedAnimal/update")
    public String abandonedAnimalUpdate(@Validated @ModelAttribute AbandonedAnimalUpdateForm updateForm,
                                        HttpServletRequest request) throws IOException {
        log.info("POST AbandonedAnimalUpdateForm = {}", updateForm);

        String fullPath = getFullPath(request);

        String initFile = abandonedAnimalService.getAbAnimal(updateForm.getAbNumber()).getImgPath();
        log.info("initFile(기존 업로드 파일명) = {}", initFile);

        String newFile = adoptReviewService.storeFile(updateForm.getFile(), fullPath);
        log.info("newFile(새로 업로드한 파일명) = {}", newFile);

        boolean hasExistingImage = !"no_image.png".equals(initFile);

        if (newFile != null && !hasExistingImage) {
            updateForm.setImgPath(newFile);
        }

        if (newFile != null && hasExistingImage) {
            String deletePath = fullPath + initFile;
            deleteFile(deletePath); // 예전사진 삭제
            updateForm.setImgPath(newFile);
        }

        abandonedAnimalService.update(updateForm);

        if ("입양".equals(updateForm.getAnimalState())) {
            abandonedAnimalService.updateWithAdopt(updateForm);
        } else if ("임보".equals(updateForm.getAnimalState())) {
            abandonedAnimalService.updateWithTemp(updateForm);
        } else {
            abandonedAnimalService.deleteAdoptAndTemp(updateForm.getAbNumber());
        }

        return "/alert/admin/abandoned_animal_update";
    }

    // 수정 전 업로드됐던 이미지파일 삭제
    @ResponseBody
    @DeleteMapping("/{imgPath}")
    public String responseDeleteFile(@PathVariable String imgPath, HttpServletRequest request) {
        String fullPath = getFullPath(request) + imgPath;

        File file = new File(fullPath);

        if (file.exists()) {
            if (file.delete()) {
                log.info("이미지 파일 삭제 성공");
            } else {
                log.info("이미지 파일 삭제 실패");
            }
        }

        return "SUCCESS";
    }

    // 유기동물 삭제 (delete)
    @GetMapping("/abandonedAnimal/delete")
    public String abandonedAnimalDelete(@RequestParam int abNumber, HttpServletRequest request) {
        String filename = abandonedAnimalService.getAbAnimal(abNumber).getImgPath();
        boolean existInitFile = filename != null && !"no_image.png".equals(filename);

        if (existInitFile) {
            String fullPath = getFullPath(request) + filename;
            deleteFile(fullPath);
        }

        abandonedAnimalService.delete(abNumber);

        return "redirect:/admin/abandonedAnimal";
    }

    // =============== 입양 관리 ===============
    // 입양 리스트
    @GetMapping("/adopt")
    public String adoptList(@RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(required = false) String keyword,
                            Model model) {
        AdoptPageForm pageForm = adoptTempService.getAdminAdoptListPage(pageNo, keyword);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_list";
    }

    // 입양 추가 (insert)
    @GetMapping("/adopt/insert")
    public String adoptInsertPage(Model model) {
        List<Member> members = memberService.getMemberList();
        List<AbandonedAnimal> onlyProtectedAnimals = adoptTempService.getAnimalListOnlyProtect();
        List<String> residences = adoptTempService.getResidenceList();

        model.addAttribute("members", members);
        model.addAttribute("onlyProtectedAnimals", onlyProtectedAnimals);
        model.addAttribute("residences", residences);

        return "/admin/adopt/adopt_insert";
    }

    @PostMapping("/adopt/insert")
    public String adoptInsert(@ModelAttribute AdoptForm adoptForm) {
        log.info("POST insert AdoptForm = {}", adoptForm);
        adoptTempService.adoptInsert(adoptForm);

        return "/alert/admin/adopt_insert";
    }

    // 입양 수정 (update)
    @GetMapping("/adopt/update")
    public String adoptUpdatePage(@RequestParam int adNumber, Model model) {
        List<Member> members = memberService.getMemberList();
        Adopt selectedAdopt = adoptTempService.getAdoptByPk(adNumber);

        List<AbandonedAnimal> onlyProtectedAnimals = adoptTempService.getAnimalListOnlyProtect();
        List<AbandonedAnimal> adoptWaitingAnimals = adoptTempService.getAnimalListAdoptWait();
        List<AbandonedAnimal> adoptCompleteAnimals = adoptTempService.getAnimalListAdoptComplete();
        List<String> residences = adoptTempService.getResidenceList();

        model.addAttribute("members", members);
        model.addAttribute("selectedAdopt", selectedAdopt);

        model.addAttribute("onlyProtectedAnimals", onlyProtectedAnimals);
        model.addAttribute("adoptWaitingAnimals", adoptWaitingAnimals);
        model.addAttribute("adoptCompleteAnimals", adoptCompleteAnimals);
        model.addAttribute("residences", residences);

        return "/admin/adopt/adopt_update";
    }

    @PostMapping("/adopt/update")
    public String adoptUpdate(@ModelAttribute AdoptForm adoptForm) {
        adoptTempService.adminAdoptUpdate(adoptForm);
        log.info("POST update AdoptForm = {}", adoptForm);

        return "/alert/admin/adopt_insert";
    }

    // 입양 삭제 (delete)
    @GetMapping("/adopt/delete")
    public String adoptDelete(@RequestParam int adNumber) {
        adoptTempService.deleteAdopt(adNumber);

        return "redirect:/admin/adopt";
    }

    // 입양 승인 관리 페이지
    @GetMapping("/adopt/wait")
    public String adoptWaitList(@RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(defaultValue = "처리중") String status,
                                Model model) {
        AdoptPageForm pageForm = adoptTempService.getAdminAdoptWaitPage(pageNo, keyword, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_wait_list";
    }

    // 입양 승인 버튼
    @GetMapping("/adopt/wait/approve")
    public String adoptApprove(@RequestParam int adNumber) {
        adoptTempService.adoptApproveButton(adNumber);

        return "redirect:/admin/adopt/wait";
    }

    // 입양 거절 버튼
    @GetMapping("/adopt/wait/refuse")
    public String adoptRefuse(@RequestParam int adNumber) {
        adoptTempService.adoptRefuseButton(adNumber);

        return "redirect:/admin/adopt/wait";
    }

    // 입양 완료된 리스트
    @GetMapping("/adopt/complete")
    public String adoptCompleteList(@RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(required = false) String keyword,
                                    @RequestParam(defaultValue = "완료") String status,
                                    Model model) {
        AdoptPageForm pageForm = adoptTempService.getAdminAdoptWaitPage(pageNo, keyword, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_complete_list";
    }

    // 입양 거절된 리스트
    @GetMapping("/adopt/refuse")
    public String adoptRefuseList(@RequestParam(defaultValue = "1") int pageNo,
                                  @RequestParam(required = false) String keyword,
                                  @RequestParam(defaultValue = "거절") String status,
                                  Model model) {

        AdoptPageForm pageForm = adoptTempService.getAdminAdoptWaitPage(pageNo, keyword, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_refuse_list";
    }

    // =============== 임시보호 관리 ===============
    // 임시보호 리스트
    @GetMapping("/temp")
    public String tempList(@RequestParam(defaultValue = "1") int pageNo,
                           @RequestParam(required = false) String  keyword,
                           Model model) {
        TempPageForm pageForm = adoptTempService.getAdminTempListPage(pageNo, keyword);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_list";
    }

    // 임시보호 추가 (insert)
    @GetMapping("/temp/insert")
    public String tempInsertPage(Model model) {
        List<Member> members = memberService.getMemberList();
        List<AbandonedAnimal> onlyProtectedAnimals = adoptTempService.getAnimalListOnlyProtect();
        List<String> residences = adoptTempService.getResidenceList();

        model.addAttribute("members", members);
        model.addAttribute("onlyProtectedAnimals", onlyProtectedAnimals);
        model.addAttribute("residences", residences);

        return "/admin/temp/temp_insert";
    }

    @PostMapping("/temp/insert")
    public String tempInsert(@ModelAttribute TempForm tempForm) {
        log.info("POST insert TempForm={}", tempForm);
        adoptTempService.tempInsert(tempForm);

        return "/alert/admin/temp_insert";
    }

    // 임시보호 수정 (update)
    @GetMapping("/temp/update")
    public String tempUpdatePage(@RequestParam int tNumber, Model model) {
        List<Member> members = memberService.getMemberList();
        TempPet selectedTemp = adoptTempService.getTempByPk(tNumber);

        List<AbandonedAnimal> onlyProtectedAnimals = adoptTempService.getAnimalListOnlyProtect();
        List<AbandonedAnimal> tempWaitingAnimals = adoptTempService.getAnimalListTempWait();
        List<AbandonedAnimal> tempCompleteAnimals = adoptTempService.getAnimalListTempComplete();
        List<String> residences = adoptTempService.getResidenceList();

        model.addAttribute("members", members);
        model.addAttribute("selectedTemp", selectedTemp);

        model.addAttribute("onlyProtectedAnimals", onlyProtectedAnimals);
        model.addAttribute("tempWaitingAnimals", tempWaitingAnimals);
        model.addAttribute("tempCompleteAnimals", tempCompleteAnimals);
        model.addAttribute("residences", residences);

        return "/admin/temp/temp_update";
    }

    @PostMapping("/temp/update")
    public String tempUpdate(@ModelAttribute TempForm tempForm) {
        adoptTempService.adminTempUpdate(tempForm);
        log.info("POST update TempForm={}", tempForm);

        return "/alert/admin/temp_update";
    }

    // 임시보호 삭제 (delete)
    @GetMapping("/temp/delete")
    public String tempDelete(@RequestParam int tNumber) {
        adoptTempService.deleteTemp(tNumber);

        return "redirect:/admin/temp";
    }

    // 임시보호 승인 관리 페이지
    @GetMapping("/temp/wait")
    public String tempWaitList(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(defaultValue = "처리중") String status,
                               Model model) {

        TempPageForm pageForm = adoptTempService.getAdminTempWaitPage(pageNo, keyword, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_wait_list";
    }

    // 임시보호 승인 버튼
    @GetMapping("/temp/wait/approve")
    public String tempApprove(@RequestParam int tNumber) {
        adoptTempService.tempApproveButton(tNumber);

        return "redirect:/admin/temp/wait";
    }

    // 임시보호 거절 버튼
    @GetMapping("/temp/wait/refuse")
    public String tempRefuse(@RequestParam int tNumber) {
        adoptTempService.tempRefuseButton(tNumber);

        return "redirect:/admin/temp/wait";
    }

    // 임시보호 완료된 리스트
    @GetMapping("/temp/complete")
    public String tempCompleteList(@RequestParam(defaultValue = "1") int pageNo,
                                   @RequestParam(required = false) String keyword,
                                   @RequestParam(defaultValue = "완료") String status,
                                   Model model) {

        TempPageForm pageForm = adoptTempService.getAdminTempWaitPage(pageNo, keyword, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_complete_list";
    }

    // 임시보호 거절된 리스트
    @GetMapping("/temp/refuse")
    public String tempRefuseList(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(required = false) String keyword,
                                 @RequestParam(defaultValue = "거절") String status,
                                 Model model) {

        TempPageForm pageForm = adoptTempService.getAdminTempWaitPage(pageNo, keyword, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_refuse_list";
    }

    // =============== 후원 관리 ===============
    // 후원 리스트
    @GetMapping("/donation")
    public String donationList(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(required = false) String keyword,
                               Model model) {
        DonationPageForm pageForm = donateService.getListPage(pageNo, keyword);
        model.addAttribute("pageForm", pageForm);

        return "/admin/donation/donation_list";
    }

    // 후원 추가
    @GetMapping("donation/insert")
    public String donationInsertPage(Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.getAbAnimalList();
        List<Member> members = memberService.getMemberList();

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);

        return "/admin/donation/donation_insert";
    }

    @PostMapping("donation/insert")
    public String donationInsert(@ModelAttribute DonationInsertForm insertForm) {
        log.info("POST DonationInsertForm = {}", insertForm);
        donateService.insert(insertForm);

        return "/alert/admin/donation_insert";
    }

    // 후원 수정
    @GetMapping("donation/update")
    public String donationUpdatePage(@RequestParam int dNumber, Model model) {
        List<Member> members = memberService.getMemberList();
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.getAbAnimalList();
        DonationUpdateForm updateForm = donateService.getUpdateForm(dNumber);

        model.addAttribute("members", members);
        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("updateForm", updateForm);

        return "/admin/donation/donation_update";
    }

    @PostMapping("donation/update")
    public String donationUpdate(@ModelAttribute DonationUpdateForm updateForm) {
        log.info("POST DonationUpdateForm = {}", updateForm);
        donateService.update(updateForm);

        return "/alert/admin/donation_update";
    }

    // 후원 삭제
    @GetMapping("/donation/delete")
    public String donationDelete(@RequestParam("dNumber") int dNumber) {
        donateService.delete(dNumber);

        return "redirect:/admin/donation";
    }

    // =============== 보호소 관리 ===============
    // 보호소 리스트
    @GetMapping("/shelter")
    public String shelterList(@RequestParam(defaultValue = "1") int pageNo,
                              @RequestParam(required = false) String keyword,
                              Model model) {
        ShelterPageForm pageForm = shelterService.getListPage(pageNo, keyword);
        model.addAttribute("pageForm", pageForm);

        return "/admin/shelter/shelter_list";
    }

    // 보호소 추가
    @GetMapping("/shelter/insert")
    public String shelterInsertPage() {
        return "/admin/shelter/shelter_insert";
    }

    @PostMapping("/shelter/insert")
    public String shelterInsert(@ModelAttribute ShelterInsertForm insertForm) {
        log.info("POST ShelterInsertForm = {}", insertForm);
        shelterService.insert(insertForm);

        return "/alert/admin/shelter_insert";
    }

    // 보호소 수정
    @GetMapping("/shelter/update")
    public String shelterUpdatePage(@RequestParam int sNumber, Model model) {
        ShelterUpdateForm updateForm = shelterService.getUpdateForm(sNumber);
        log.info("GET ShelterUpdateForm = {}", updateForm);
        model.addAttribute("updateForm", updateForm);

        return "/admin/shelter/shelter_update";
    }

    @PostMapping("/shelter/update")
    public String shelterUpdate(@ModelAttribute ShelterUpdateForm updateForm) {
        shelterService.update(updateForm);
        log.info("POST ShelterUpdateForm = {}", updateForm);

        return "/alert/admin/shelter_update";
    }

    // 보호소 삭제
    @GetMapping("/shelter/delete")
    public String shelterDelete(@RequestParam int sNumber) {
        shelterService.delete(sNumber);

        return "redirect:/admin/shelter";
    }

    private String getFullPath(HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        return fullPath;
    }

    private void deleteFile(String deletePath) {
        File fileToDelete = new File(deletePath);
        if (fileToDelete.delete()) {
            log.info("이미지파일 삭제 성공 = {}", deletePath);
        }
    }
}
