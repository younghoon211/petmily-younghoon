package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AdminAbandonedAnimalModifyForm;
import kh.petmily.domain.abandoned_animal.form.AdminAbandonedAnimalWriteForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdminAdoptForm;
import kh.petmily.domain.adopt.form.AdminAdoptPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.donation.form.AdminDonationModifyForm;
import kh.petmily.domain.donation.form.AdminDonationPageForm;
import kh.petmily.domain.donation.form.AdminDonationWriteForm;
import kh.petmily.domain.find_board.form.FindBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.AdminMemberCreateForm;
import kh.petmily.domain.member.form.AdminMemberModifyForm;
import kh.petmily.domain.member.form.MemberPageForm;
import kh.petmily.domain.shelter.Shelter;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final AdoptReviewService adoptReviewService;
    private final FindBoardService findBoardService;
    private final LookBoardService lookBoardService;
    private final DonateService donateService;
    private final AbandonedAnimalService abandonedAnimalService;
    private final AdoptTempService adoptTempService;

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
//        modifyForm.setMNumber(mNumber);
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

        return "/admin/member/alert_delete";
    }

    // =============== 유기동물 관리 ===============
    // 유기동물 리스트
    @GetMapping("/abandoned_animal")
    public String abandonedAnimalList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AbandonedAnimalPageForm pageForm = abandonedAnimalService.getAdminListPage(pageNo);
        model.addAttribute("pageForm", pageForm);

        return "/admin/abandoned.animal/abandoned_animal_list";
    }

    // 유기동물 정보 추가(insert)
    @GetMapping("/abandoned_animal/write")
    public String adminAbandonedWriteForm() {
        return "/admin/abandoned.animal/abandoned_animal_write";
    }

    @PostMapping("/abandoned_animal/write")
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
    @GetMapping("/abandoned_animal/modify")
    public String adminAbandonedModifyForm(@RequestParam int abNumber, Model model) {
        AdminAbandonedAnimalModifyForm modifyForm = abandonedAnimalService.getModifyForm(abNumber);
        log.info("수정 전 adminAbandonedAnimalModifyForm = {}", modifyForm);

        List<Shelter> shelterList = abandonedAnimalService.selectAllShelter();

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("shelter", shelterList);

        return "/admin/abandoned.animal/abandoned_animal_modify";
    }

    @PostMapping("/abandoned_animal/modify")
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
    @GetMapping("/abandoned_animal/delete")
    public String adminAbandonedDelete(@RequestParam int abNumber) {
        abandonedAnimalService.delete(abNumber);

        return "/admin/abandoned.animal/alert_delete";
    }

    // =============== 게시판 관리 ===============
    // 게시판 리스트
    @GetMapping("/board")
    public String boardPage(@RequestParam("kindOfBoard") String kind,
                            @RequestParam(defaultValue = "1") int pageNo,
                            Model model) {
        if (kind.equals("자유")) {
            model.addAttribute("boardForm", boardService.getAdminListPage("자유", pageNo));
        } else if (kind.equals("문의")) {
            model.addAttribute("boardForm", boardService.getAdminListPage("문의", pageNo));
        } else if (kind.equals("입양후기")) {
            model.addAttribute("boardForm", adoptReviewService.getAdminListPage("입양후기", pageNo));
        } else if (kind.equals("find")) {
            model.addAttribute("boardForm", findBoardService.getAdminListPage(pageNo));
        } else if (kind.equals("look")) {
            model.addAttribute("boardForm", lookBoardService.getAdminListPage(pageNo));
        }

        return "/admin/board/board_list";
    }

    // 게시판 글 추가(insert)
    @GetMapping("/board/write")
    public String boardWrite(@RequestParam("kindOfBoard") String kind, Model model) {
        List<Member> memberList = memberService.selectAll();

        model.addAttribute("memberList", memberList);

        if (kind.equals("자유") || kind.equals("문의")) {
            return "/board/board_write";
        } else if (kind.equals("입양후기")) {
            return "/adopt.review/adopt_review_write";
        } else if (kind.equals("find")) {
            return "/find.board/find_write";
        } else if (kind.equals("look")) {
            return "/look.board/look_write";
        }

        return null;
    }

    // 게시판 글 수정(update)
    @GetMapping("/board/modify")
    public String boardModify(@RequestParam("kindOfBoard") String kind, @RequestParam int pk, Model model) {
        if (kind.equals("자유") || kind.equals("문의")) {
            BoardModifyForm modifyForm = boardService.getModifyForm(pk);
            log.info("수정 전 boardModifyForm = {}", modifyForm);

            model.addAttribute("modifyForm", modifyForm);

            return "/board/board_modify";
        } else if (kind.equals("입양후기")) {
            AdoptReviewModifyForm modifyForm = adoptReviewService.getModifyForm(pk);
            log.info("수정 전 adoptReviewModifyForm = {}", modifyForm);

            model.addAttribute("modifyForm", modifyForm);

            return "/adopt.review/adopt_review_modify";
        } else if (kind.equals("find")) {
            FindBoardModifyForm modifyForm = findBoardService.getModifyForm(pk);
            log.info("수정 전 findBoardModifyForm = {}", modifyForm);

            model.addAttribute("modifyForm", modifyForm);

            return "/find.board/find_modify";
        } else if (kind.equals("look")) {
            LookBoardModifyForm modifyForm = lookBoardService.getModifyForm(pk);
            log.info("수정 전 lookBoardModifyForm = {}", modifyForm);

            model.addAttribute("modifyForm", modifyForm);

            return "/look.board/look_modify";
        }

        return null;
    }

    // 게시판 글 삭제(delete)
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("kindOfBoard") String kind,
                              @RequestParam int pk,
                              RedirectAttributes redirectAttributes) {
        if (kind.equals("자유") || kind.equals("문의")) {
            boardService.delete(pk);
        } else if (kind.equals("입양후기")) {
            adoptReviewService.delete(pk);
        } else if (kind.equals("find")) {
            findBoardService.delete(pk);
        } else if (kind.equals("look")) {
            lookBoardService.delete(pk);
        }

        redirectAttributes.addAttribute("kindOfBoard", kind);

        return "/admin/board/alert_delete";
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

        return "/admin/adopt/alert_delete";
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

        return "/admin/adopt/alert_approve";
    }

    // 입양 거절 버튼
    @GetMapping("/adopt/wait/refuse")
    public String adoptRefuse(@RequestParam int adNumber) {
        adoptTempService.adoptRefuseButton(adNumber);

        return "/admin/adopt/alert_refuse";
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

        return "/admin/temp/alert_delete";
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

        return "/admin/temp/alert_approve";
    }

    // 임시보호 거절 버튼
    @GetMapping("/temp/wait/refuse")
    public String tempRefuse(@RequestParam int tNumber) {
        adoptTempService.tempRefuseButton(tNumber);

        return "/admin/temp/alert_refuse";
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

    @GetMapping("donation/modify")
    public String donationModifyForm(@RequestParam("dNumber") int dNumber, Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        List<Member> members = memberService.selectAll();
        AdminDonationModifyForm modifyForm = donateService.getModifyForm(dNumber);

        modifyForm.setDNumber(dNumber);
        log.info("dNumber = {}", dNumber);

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);
        model.addAttribute("modifyForm", modifyForm);

        return "/admin/donation/donation_modify";
    }

    @PostMapping("donation/modify")
    public String donationModify(@RequestParam("dNumber") int dNumber,
                                 @RequestParam("abNumber") int abNumber,
                                 @RequestParam("mNumber") int mNumber,
                                 @ModelAttribute AdminDonationModifyForm modifyForm,
                                 RedirectAttributes redirectAttributes) {
        log.info("adminDonationModifyForm = {}", modifyForm);
        modifyForm.setDNumber(dNumber);
        log.info("setDNumber = {}", dNumber);

        donateService.modify(modifyForm);

        redirectAttributes.addAttribute("dNumber", dNumber);
        redirectAttributes.addAttribute("abNumber", abNumber);
        redirectAttributes.addAttribute("mNumber", mNumber);

        return "/admin/donation/alert_modify";
    }

    @GetMapping("/donation/delete")
    public String delete(@RequestParam("dNumber") int dNumber) {
        donateService.delete(dNumber);

        return "/admin/donation/alert_delete";
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
