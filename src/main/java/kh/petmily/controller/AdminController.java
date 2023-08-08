package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AdminAbandonedAnimalModifyForm;
import kh.petmily.domain.abandoned_animal.form.AdminAbandonedAnimalWriteForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdminAdoptForm;
import kh.petmily.domain.adopt.form.AdminAdoptPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewPageForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.donation.form.AdminDonationModifyForm;
import kh.petmily.domain.donation.form.AdminDonationPageForm;
import kh.petmily.domain.donation.form.AdminDonationWriteForm;
import kh.petmily.domain.find_board.form.FindBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.AdminMemberCreateForm;
import kh.petmily.domain.member.form.AdminMemberModifyForm;
import kh.petmily.domain.member.form.MemberPageForm;
import kh.petmily.domain.shelter.form.ShelterModifyForm;
import kh.petmily.domain.shelter.form.ShelterPageForm;
import kh.petmily.domain.shelter.form.ShelterWriteForm;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.AdminTempForm;
import kh.petmily.domain.temp.form.AdminTempPageForm;
import kh.petmily.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
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
    public String memberPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        MemberPageForm pageForm = memberService.getAdminListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/member/member_list";
    }

    // 회원 정보 추가(insert)
    @GetMapping("/member/insert")
    public String memberCreateForm() {
        return "admin/member/member_insert";
    }

    @PostMapping("/member/insert")
    public String join(@ModelAttribute AdminMemberCreateForm adminMemberCreateForm) {
        log.info("adminMemberCreateForm = {}", adminMemberCreateForm);

        memberService.create(adminMemberCreateForm);

        return "/admin/member/alert_insert";
    }

    // 회원 정보 수정(update)
    @GetMapping("/member/modify")
    public String memberDetailModify(@RequestParam int mNumber, Model model) {
        AdminMemberModifyForm modifyForm = memberService.getModifyForm(mNumber);
        log.info("수정 전 adminMemberModifyForm = {}", modifyForm);

        model.addAttribute("modifyForm", modifyForm);

        return "admin/member/member_modify";
    }

    @PostMapping("/member/modify")
    public String memberModify(@ModelAttribute AdminMemberModifyForm modifyForm) {
        memberService.modify(modifyForm);
        log.info("수정 후 adminMemberModifyForm = {}", modifyForm);

        return "/admin/member/alert_modify";
    }

    // 회원 정보 삭제(delete)
    @GetMapping("/member/delete")
    public String MemberDelete(@RequestParam int mNumber) {
        memberService.delete(mNumber);

        return "redirect:/admin/member";
    }

    // =============== 게시판 관리 ===============
    // 게시판 리스트 (CUD는 기존 회원 폼 이용)
    @GetMapping("/board")
    public String boardPage(@RequestParam String kindOfBoard,
                            @RequestParam(defaultValue = "1") int pageNo,
                            Model model) {
        if (kindOfBoard.equals("free")) {
            BoardPageForm freeBoardForm = boardService.getAdminListPage("free", pageNo);
            model.addAttribute("boardForm", freeBoardForm);
        }
        else if (kindOfBoard.equals("inquiry")) {
            BoardPageForm inquiryBoardForm = boardService.getAdminListPage("inquiry", pageNo);
            model.addAttribute("boardForm", inquiryBoardForm);
        }
        else if (kindOfBoard.equals("adoptReview")) {
            AdoptReviewPageForm adoptReviewBoardForm = adoptReviewService.getAdminListPage("adoptReview", pageNo);
            model.addAttribute("boardForm", adoptReviewBoardForm);
        }
        else if (kindOfBoard.equals("find")) {
            FindBoardPageForm findBoardForm = findBoardService.getAdminListPage(pageNo);
            model.addAttribute("boardForm", findBoardForm);
        }
        else if (kindOfBoard.equals("look")) {
            LookBoardPageForm lookBoardForm = lookBoardService.getAdminListPage(pageNo);
            model.addAttribute("boardForm", lookBoardForm);
        }

        return "/admin/board/board_list";
    }

    // =============== 유기동물 관리 ===============
    // 유기동물 리스트
    @GetMapping("/abandonedAnimal")
    public String abandonedAnimalList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AbandonedAnimalPageForm pageForm = abandonedAnimalService.getAdminListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/abandoned.animal/abandoned_animal_list";
    }

    // 유기동물 정보 추가(insert)
    @GetMapping("/abandonedAnimal/write")
    public String adminAbandonedWriteForm() {
        return "/admin/abandoned.animal/abandoned_animal_write";
    }

    @PostMapping("/abandonedAnimal/write")
    public String adminAbandonedWrite(@ModelAttribute AdminAbandonedAnimalWriteForm writeForm,
                                      HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = abandonedAnimalService.storeFile(writeForm.getFile(), fullPath);
        writeForm.setImgPath(filename);

        log.info("adminAbandonedAnimalWriteForm = {}", writeForm);

        abandonedAnimalService.write(writeForm);

        return "/admin/abandoned.animal/alert_write";
    }

    // 유기동물 정보 수정(update)
    @GetMapping("/abandonedAnimal/modify")
    public String adminAbandonedModifyForm(@RequestParam int abNumber, Model model) {
        AdminAbandonedAnimalModifyForm modifyForm = abandonedAnimalService.getModifyForm(abNumber);
        log.info("수정 전 adminAbandonedAnimalModifyForm = {}", modifyForm);

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("shelter", shelterService.selectAll());

        return "/admin/abandoned.animal/abandoned_animal_modify";
    }

    @PostMapping("/abandonedAnimal/modify")
    public String adminAbandonedModify(@Validated @ModelAttribute AdminAbandonedAnimalModifyForm modifyForm,
                                       HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = abandonedAnimalService.storeFile(modifyForm.getFile(), fullPath);
        modifyForm.setImgPath(filename);

        abandonedAnimalService.modify(modifyForm);
        log.info("수정 후 abandonedAnimalModifyForm = {}", modifyForm);

        return "/admin/abandoned.animal/alert_modify";
    }

    // 유기동물 정보 삭제(delete)
    @GetMapping("/abandonedAnimal/delete")
    public String adminAbandonedDelete(@RequestParam int abNumber) {
        abandonedAnimalService.delete(abNumber);

        return "redirect:/admin/abandonedAnimal";
    }

    // =============== 입양 정보 관리 ===============
    // 입양 리스트
    @GetMapping("/adopt")
    public String adoptList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AdminAdoptPageForm pageForm = adoptTempService.getAdminAdoptListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_list";
    }

    // 입양 정보 추가(insert)
    @GetMapping("/adopt/write")
    public String adoptWrite(Model model) {
        List<Member> member = adoptTempService.selectAllMemberAdopt();
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllExcludeAdoptStatus();

        model.addAttribute("member", member);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/adopt/adopt_write";
    }

    @PostMapping("/adopt/write")
    public String adoptWriteForm(@ModelAttribute AdminAdoptForm adminAdoptForm) {
        log.info("adminAdoptForm = {}", adminAdoptForm);
        adoptTempService.adminAdoptWrite(adminAdoptForm);
        adoptTempService.updateStatusToAdopt();

        return "/admin/adopt/alert_write";
    }

    // 입양 정보 수정(update)
    @GetMapping("/adopt/modify")
    public String adoptModify(@RequestParam int adNumber, Model model) {
        List<Member> member = adoptTempService.selectAllMemberAdopt();
        Adopt adopt = adoptTempService.getAdoptByPk(adNumber);
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllAbandonedAnimalAdopt();

        model.addAttribute("member", member);
        model.addAttribute("adopt", adopt);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/adopt/adopt_modify";
    }

    @PostMapping("/adopt/modify")
    public String adoptModifyForm(@ModelAttribute AdminAdoptForm adminAdoptForm) {
        adoptTempService.adminAdoptUpdate(adminAdoptForm);
        adoptTempService.updateStatusToAdopt();

        log.info("수정 후 adminAdoptForm = {}", adminAdoptForm);

        return "/admin/adopt/alert_modify";
    }

    // 입양 정보 삭제(delete)
    @GetMapping("/adopt/delete")
    public String adoptDelete(@RequestParam int adNumber) {
        adoptTempService.deleteAdopt(adNumber);

        return "redirect:/admin/adopt";
    }


    // 입양 승인 관리 페이지
    @GetMapping("/adopt/wait")
    public String adoptWait(@RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(defaultValue = "처리중") String status,
                            Model model) {
        AdminAdoptPageForm pageForm = adoptTempService.getAdminAdoptWaitPage(pageNo, status);
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
    public String adoptComplete(@RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "완료") String status,
                                Model model) {
        AdminAdoptPageForm pageForm = adoptTempService.getAdminAdoptWaitPage(pageNo, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_complete_list";
    }

    // 입양 거절된 리스트
    @GetMapping("/adopt/refuse")
    public String adoptRefuse(@RequestParam(defaultValue = "1") int pageNo,
                              @RequestParam(defaultValue = "거절") String status,
                              Model model) {

        AdminAdoptPageForm pageForm = adoptTempService.getAdminAdoptWaitPage(pageNo, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/adopt/adopt_refuse_list";
    }

    // =============== 임시보호 정보 관리 ===============
    // 임시보호 리스트
    @GetMapping("/temp")
    public String tempList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AdminTempPageForm pageForm = adoptTempService.getAdminTempListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_list";
    }

    // 임시보호 정보 추가(insert)
    @GetMapping("/temp/write")
    public String tempWrite(Model model) {
        List<Member> member = adoptTempService.selectAllMemberTemp();
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllExcludeTempStatus();

        model.addAttribute("member", member);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/temp/temp_write";
    }

    @PostMapping("/temp/write")
    public String tempWriteForm(@ModelAttribute AdminTempForm adminTempForm) {
        log.info("adminTempForm={}", adminTempForm);

        adoptTempService.adminTempWrite(adminTempForm);
        adoptTempService.updateStatusToTemp();

        return "/admin/temp/alert_write";
    }

    // 임시보호 정보 수정(update)
    @GetMapping("/temp/modify")
    public String tempModify(@RequestParam int tNumber, Model model) {
        List<Member> member = adoptTempService.selectAllMemberTemp();
        TempPet tempPet = adoptTempService.getTempByPk(tNumber);
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllAbandonedAnimalTemp();

        model.addAttribute("member", member);
        model.addAttribute("temp", tempPet);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/temp/temp_modify";
    }

    @PostMapping("/temp/modify")
    public String tempModifyForm(@ModelAttribute AdminTempForm adminTempForm) {
        adoptTempService.adminTempUpdate(adminTempForm);
        adoptTempService.updateStatusToTemp();

        log.info("수정 후 adminTempForm={}", adminTempForm);

        return "/admin/temp/alert_modify";
    }

    // 임시보호 정보 삭제(delete)
    @GetMapping("/temp/delete")
    public String tempDelete(@RequestParam int tNumber) {
        adoptTempService.deleteTemp(tNumber);

        return "redirect:/admin/temp";
    }


    // 임시보호 승인 관리 페이지
    @GetMapping("/temp/wait")
    public String tempWait(@RequestParam(defaultValue = "1") int pageNo,
                           @RequestParam(defaultValue = "처리중") String status,
                           Model model) {

        AdminTempPageForm pageForm = adoptTempService.getAdminTempWaitPage(pageNo, status);
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
    public String tempComplete(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "완료") String status,
                               Model model) {

        AdminTempPageForm pageForm = adoptTempService.getAdminTempWaitPage(pageNo, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_complete_list";
    }

    // 임시보호 거절된 리스트
    @GetMapping("/temp/refuse")
    public String tempRefuse(@RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "거절") String status,
                             Model model) {

        AdminTempPageForm pageForm = adoptTempService.getAdminTempWaitPage(pageNo, status);
        model.addAttribute("pageForm", pageForm);

        return "/admin/temp/temp_refuse_list";
    }

    // =============== 후원 정보 관리 ===============
    // 후원 리스트
    @GetMapping("/donation")
    public String donationPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AdminDonationPageForm pageForm = donateService.getListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/donation/donation_list";
    }

    // 후원 추가
    @GetMapping("donation/create")
    public String donationCreateForm(Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        List<Member> members = memberService.selectAll();

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);

        return "/admin/donation/donation_write";
    }

    @PostMapping("donation/create")
    public String donationCreate(@ModelAttribute AdminDonationWriteForm writeForm) {
        log.info("adminDonationWriteForm = {}", writeForm);
        donateService.create(writeForm);

        return "/admin/donation/alert_write";
    }

    // 후원 수정
    @GetMapping("donation/modify")
    public String donationModifyForm(@RequestParam int dNumber, Model model) {
        List<Member> members = memberService.selectAll();
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        AdminDonationModifyForm modifyForm = donateService.getModifyForm(dNumber);

        model.addAttribute("members", members);
        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("modifyForm", modifyForm);

        return "/admin/donation/donation_modify";
    }

    @PostMapping("donation/modify")
    public String donationModify(@ModelAttribute AdminDonationModifyForm modifyForm) {
        log.info("adminDonationModifyForm = {}", modifyForm);
        donateService.modify(modifyForm);

        return "/admin/donation/alert_modify";
    }

    // 후원 삭제
    @GetMapping("/donation/delete")
    public String delete(@RequestParam("dNumber") int dNumber) {
        donateService.delete(dNumber);

        return "redirect:/admin/donation";
    }

    // =============== 보호소 정보 관리 ===============
    // 보호소 리스트
    @GetMapping("/shelter")
    public String shelterPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        ShelterPageForm pageForm = shelterService.getListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/shelter/shelter_list";
    }

    // 보호소 추가
    @GetMapping("/shelter/create")
    public String shelterCreateForm() {
        return "/admin/shelter/shelter_write";
    }

    @PostMapping("/shelter/create")
    public String shelterCreate(@ModelAttribute ShelterWriteForm writeForm) {
        log.info("ShelterWriteForm = {}", writeForm);
        shelterService.create(writeForm);

        return "/admin/shelter/alert_write";
    }

    // 보호소 수정
    @GetMapping("/shelter/modify")
    public String shelterModifyForm(@RequestParam int sNumber, Model model) {
        ShelterModifyForm modifyForm = shelterService.getModifyForm(sNumber);
        log.info("보호소 수정 전 modifyForm = {}", modifyForm);
        model.addAttribute("modifyForm", modifyForm);

        return "/admin/shelter/shelter_modify";
    }

    @PostMapping("/shelter/modify")
    public String shelterModify(@ModelAttribute ShelterModifyForm modifyForm) {
        shelterService.modify(modifyForm);
        log.info("보호소 수정 후 modifyForm = {}", modifyForm);

        return "/admin/shelter/alert_modify";
    }

    // 보호소 삭제
    @GetMapping("/shelter/delete")
    public String shelterDelete(@RequestParam int sNumber) {
        shelterService.delete(sNumber);

        return "redirect:/admin/shelter";
    }

    // 파일 업로드
    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> list(String filename, HttpServletRequest request) {

        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";
        fullPath = fullPath + filename;

        log.info("fullPath = {} ", fullPath);

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new UrlResource("file:" + fullPath));
        } catch (MalformedURLException e) {
            log.info("fullPath = {} ", fullPath);

            throw new RuntimeException(e);
        }
    }
}
