package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalModifyForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalWriteForm;
import kh.petmily.domain.adopt.form.AdoptPageForm;
import kh.petmily.domain.adopt.form.TempPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.donation.form.DonationCreateForm;
import kh.petmily.domain.donation.form.DonationDetailForm;
import kh.petmily.domain.donation.form.DonationModifyForm;
import kh.petmily.domain.donation.form.DonationPageForm;
import kh.petmily.domain.find_board.form.FindBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberModifyForm;
import kh.petmily.domain.member.form.MemberPageForm;
import kh.petmily.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    // 회원 리스트
    @GetMapping("/member")
    public String memberPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        MemberPageForm memberPageForm = memberService.getMemberPage(pageNo);
        model.addAttribute("memberPageForm", memberPageForm);

        return "/admin/member/adminMemberList";
    }

    // 회원 정보 추가(insert)
    @GetMapping("/member/insert")
    public String memberCreateForm() {
        return "admin/member/adminInsertForm";
    }

    @PostMapping("/member/insert")
    public String join(@ModelAttribute("joinRequest") JoinRequest joinRequest) {

        if (!joinRequest.isPwEqualToConfirm()) {
            return "/admin/member/adminInsertForm";
        }
        memberService.join(joinRequest);
        return "/admin/member/insertSuccess";
    }

    // 회원 정보 수정(modify)
    @GetMapping("/member/modify")
    public String memberDetailModify(@RequestParam("mNumber") int mNumber, Model model) {
        MemberModifyForm memberModifyForm = memberService.getMemberModify(mNumber);
        memberModifyForm.setMNumber(mNumber);

        model.addAttribute("member", memberModifyForm);

        return "admin/member/adminMemberModify";
    }

    @PostMapping("/member/modify")
    public String memberModify(@ModelAttribute MemberModifyForm memberModifyForm, Model model, RedirectAttributes redirectAttributes) {
        log.info("memberModifyForm = {}", memberModifyForm);

        memberService.modify(memberModifyForm);

        model.addAttribute("memberModify", memberModifyForm);

        return "redirect:/admin/member";
    }

    // 회원 정보 삭제(delete)
    @GetMapping("/member/delete")
    public String MemberDelete(@RequestParam("mNumber") int mNumber) {
        memberService.delete(mNumber);

        return "/admin/member/deleteMemberSuccess";
    }

    // =============== 유기동물 관리 시작 ===============
    @GetMapping("/abandoned_animal")
    public String abandonedAnimalList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AbandonedAnimalPageForm abandonedAnimals = abandonedAnimalService.getAbandonedAnimalPage(pageNo);
        model.addAttribute("abandonedAnimals", abandonedAnimals);

        return "/admin/abandoned_animal/abandonedAnimalList";
    }

    @GetMapping("/abandoned_animal/write")
    public String adminAbandonedWriteForm() {
        return "/admin/abandoned_animal/abandonedAnimalWriteForm";
    }

    @PostMapping("/abandoned_animal/write")
    public String adminAbandonedWrite(@ModelAttribute AbandonedAnimalWriteForm abandonedAnimalWriteForm, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = "";

        if (!abandonedAnimalWriteForm.getImgPath().isEmpty()) {
            try {
                filename = abandonedAnimalService.storeFile(abandonedAnimalWriteForm.getImgPath(), fullPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            abandonedAnimalWriteForm.setFullPath(filename);
        } else {
            abandonedAnimalWriteForm.setFullPath("");
        }

        log.info("AbandonedAnimalWriteForm = {}", abandonedAnimalWriteForm);

        abandonedAnimalService.write(abandonedAnimalWriteForm);

        return "/admin/abandoned_animal/abandonedAnimalWriteSuccess";
    }

    @GetMapping("/abandoned_animal/modify")
    public String adminAbandonedModifyForm(@RequestParam("abNumber") int abNumber, Model model) {
        AbandonedAnimalModifyForm modReq = abandonedAnimalService.getAbandonedModify(abNumber);

        model.addAttribute("modReq", modReq);

        return "/admin/abandoned_animal/abandonedAnimalModifyForm";
    }

    @PostMapping("/abandoned_animal/modify")
    public String adminAbandonedModify(@RequestParam("abNumber") int abNumber,
                                       @ModelAttribute AbandonedAnimalModifyForm modReq,
                                       HttpServletRequest request,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        modReq.setAbNumber(abNumber);
        String filename = null;

        try {
            filename = abandonedAnimalService.storeFile(modReq.getImgPath(), fullPath);
            modReq.setFullPath(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("AbandonedAnimalModifyForm = {}", modReq);

        abandonedAnimalService.modify(modReq);
        model.addAttribute("modReq", modReq);
        redirectAttributes.addAttribute("abNumber", abNumber);

        log.info("ModifyForm = {}", modReq);

        return "redirect:/admin/abandoned_animal";
    }

    @GetMapping("/abandoned_animal/delete")
    public String adminAbandonedDelete(@RequestParam("abNumber") int abNumber, RedirectAttributes redirectAttributes) {
        abandonedAnimalService.delete(abNumber);
        redirectAttributes.addAttribute("abNumber", abNumber);

        return "redirect:/admin/abandoned_animal";
    }
    // =============== 유기동물 관리 끝 ===============

    // =============== 게시판 관리 시작 ===============
    @GetMapping("/board")
    public String boardPage(@RequestParam("kindOfBoard") String kind, @RequestParam(defaultValue = "1") int pageNo, Model model) {
        if (kind.equals("자유")) {
            model.addAttribute("boardForm", boardService.getAdminBoardPage("자유", pageNo));
        } else if (kind.equals("문의")) {
            model.addAttribute("boardForm", boardService.getAdminBoardPage("문의", pageNo));
        } else if (kind.equals("입양후기")) {
            model.addAttribute("boardForm", adoptReviewService.getAdminAdoptReviewPage("입양후기", pageNo));
        } else if (kind.equals("find")) {
            model.addAttribute("boardForm", findBoardService.getAdminFindPage(pageNo));
        } else if (kind.equals("look")) {
            model.addAttribute("boardForm", lookBoardService.getAdminLookPage(pageNo));
        }

        return "/admin/board/adminBoardList";
    }

    @GetMapping("/board/write")
    public String boardWrite(@RequestParam("kindOfBoard") String kind, Model model) {
        List<Member> list = memberService.selectAll();
        model.addAttribute("Mems", list);

        if (kind.equals("자유") || kind.equals("문의")) {

            return "/board/writeBoardForm";
        } else if (kind.equals("입양후기")) {

            return "/adopt_review/writeAdoptReviewForm";
        } else if (kind.equals("find")) {

            return "/find_board/writeFindBoardForm";
        } else if (kind.equals("look")) {

            return "/look_board/writeLookBoardForm";
        }
        return null;
    }

    @GetMapping("/board/modify")
    public String boardModify(@RequestParam("kindOfBoard") String kind, @RequestParam("pk") int pk, Model model) {
        if (kind.equals("자유") || kind.equals("문의")) {
            BoardModifyForm bmForm = boardService.getBoardModify(pk);

            int mNumber = boardService.getBoard(pk).getMNumber();
            bmForm.setMNumber(mNumber);
            bmForm.setBNumber(pk);

            model.addAttribute("modReq", bmForm);

            return "/board/modifyForm";
        } else if (kind.equals("입양후기")) {
            AdoptReviewModifyForm armForm = adoptReviewService.getAdoptReviewModify(pk);

            int mNumber = adoptReviewService.getAdoptReview(pk).getMNumber();
            armForm.setMNumber(mNumber);
            armForm.setBNumber(pk);

            model.addAttribute("modReq", armForm);

            return "/adopt_review/modifyAdoptReviewForm";
        } else if (kind.equals("find")) {
            FindBoardModifyForm fmForm = findBoardService.getModifyForm(pk);

            int mNumber = findBoardService.getFindBoard(pk).getMNumber();
            fmForm.setMNumber(mNumber);
            fmForm.setFaNumber(pk);

            model.addAttribute("findMod", fmForm);

            return "/find_board/modifyFindBoardForm";
        } else if (kind.equals("look")) {
            LookBoardModifyForm lmForm = lookBoardService.getModifyForm(pk);

            int mNumber = lookBoardService.getLookBoard(pk).getMNumber();
            lmForm.setMNumber(mNumber);
            lmForm.setLaNumber(pk);

            model.addAttribute("lookMod", lmForm);

            return "/look_board/modifyLookBoardForm";
        }

        return null;
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("kindOfBoard") String kind, @RequestParam("pk") int pk, RedirectAttributes redirectAttributes) {
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

        return "redirect:/admin/board?kindOfBoard={kindOfBoard}";
    }
    // =============== 게시판 관리 끝 ===============


    // 입양/임보 관리 메인 페이지
    @GetMapping("/adopt_temp")
    public String adoptTempTotalList() {
        return "/admin/adopt_temp/adoptTempTotalList";
    }

    // 입양/임보 대기 선택 페이지
    @GetMapping("/adopt_temp/wait")
    public String adoptTempWaitList() {
        return "/admin/adopt_temp/adoptTempWaitList";
    }

    // 입양 대기 페이지
    @GetMapping("/adopt_temp/wait/adopt")
    public String waitAdoptDetail(@RequestParam(defaultValue = "1") int pageNo,
                                  @RequestParam(defaultValue = "처리중") String status,
                                  Model model) {

        AdoptPageForm adopt = adoptTempService.getAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt_temp/waitAdoptDetail";
    }

    // 입양 승인 버튼
    @GetMapping("/adopt_temp/wait/adopt/approve")
    public String adoptApprove(@RequestParam int adNumber) {

        adoptTempService.adoptApprove(adNumber);

        return "/admin/adopt_temp/adoptSuccess";
    }

    // 입양 거절 버튼
    @GetMapping("/adopt_temp/wait/adopt/refuse")
    public String adoptRefuse(@RequestParam int adNumber) {

        adoptTempService.adoptRefuse(adNumber);

        return "/admin/adopt_temp/adoptRefuse";
    }

    // 임보 대기 페이지
    @GetMapping("/adopt_temp/wait/temp_pet")
    public String waitTempDetail(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "처리중") String status,
                                 Model model) {

        TempPageForm temp = adoptTempService.getTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/adopt_temp/waitTempPetDetail";
    }

    // 임보 승인 버튼
    @GetMapping("/adopt_temp/wait/temp_pet/approve")
    public String tempApprove(@RequestParam int tNumber) {

        adoptTempService.tempApprove(tNumber);

        return "/admin/adopt_temp/tempSuccess";
    }

    // 임보 거절 버튼
    @GetMapping("/adopt_temp/wait/temp_pet/refuse")
    public String tempRefuse(@RequestParam int tNumber) {

        adoptTempService.tempRefuse(tNumber);

        return "/admin/adopt_temp/tempRefuse";
    }

    // 입양/임보 완료 선택 페이지
    @GetMapping("/adopt_temp/complete")
    public String adoptTempCompleteList() {
        return "/admin/adopt_temp/adoptTempCompleteList";
    }

    // 입양 완료된 리스트
    @GetMapping("/adopt_temp/complete/adopt")
    public String completeAdoptDetail(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "완료") String status,
                                      Model model) {

        AdoptPageForm adopt = adoptTempService.getAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt_temp/completeAdoptDetail";
    }

    // 임보 완료된 리스트
    @GetMapping("/adopt_temp/complete/temp_pet")
    public String completeTempPetDetail(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "완료") String status,
                                        Model model) {

        TempPageForm temp = adoptTempService.getTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/adopt_temp/completeTempPetDetail";
    }

    // 입양/임보 거절 선택 페이지
    @GetMapping("/adopt_temp/refuse")
    public String adoptTempRefuseList() {
        return "/admin/adopt_temp/adoptTempRefuseList";
    }

    // 입양 거절된 리스트
    @GetMapping("/adopt_temp/refuse/adopt")
    public String refuseAdoptDetail(@RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(defaultValue = "거절") String status,
                                    Model model) {

        AdoptPageForm adopt = adoptTempService.getAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt_temp/refuseAdoptDetail";
    }

    // 임보 거절된 리스트
    @GetMapping("/adopt_temp/refuse/temp_pet")
    public String refuseTempPetDetail(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "거절") String status,
                                      Model model) {

        TempPageForm temp = adoptTempService.getTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/adopt_temp/refuseTempPetDetail";
    }

    @GetMapping("/donation")
    public String donationPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        DonationPageForm donationPageForm = donateService.getDonationPage(pageNo);
        model.addAttribute("donationPageForm", donationPageForm);

        return "/admin/donation/donationList";
    }

    @GetMapping("/donation/detail")
    public String donationDetail(@RequestParam("dNumber") int dNumber,
                                 @RequestParam("abNumber") int abNumber,
                                 @RequestParam("mNumber") int mNumber,
                                 HttpServletRequest request,
                                 Model model) {
        String animalName = abandonedAnimalService.findName(abNumber);
        String memberName = memberService.findName(mNumber);

        if (animalName != null) {
            request.setAttribute("animalName", animalName);
        }

        if (memberName != null) {
            request.setAttribute("memberName", memberName);
        }

        DonationDetailForm donationDetailForm = donateService.getDonation(dNumber);

        model.addAttribute("donationDetail", donationDetailForm);

        return "/admin/donation/donationDetail";
    }

    @GetMapping("donation/create")
    public String donationCreateForm(Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        List<Member> members = memberService.selectAll();

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);

        return "/admin/donation/createDonation";
    }

    @PostMapping("donation/create")
    public String donationCreate(@ModelAttribute DonationCreateForm donationCreateForm, Model model) {
        donateService.create(donationCreateForm);

        return "/admin/donation/createDonationSuccess";
    }

    @GetMapping("donation/modify")
    public String donationModifyForm(@RequestParam("dNumber") int dNumber, Model model) {
        List<AbandonedAnimal> abandonedAnimals = abandonedAnimalService.selectAll();
        List<Member> members = memberService.selectAll();

        DonationModifyForm donationModifyForm = donateService.getDonationModify(dNumber);

        donationModifyForm.setDNumber(dNumber);
        log.info("dNumber = {}", dNumber);

        model.addAttribute("abandonedAnimals", abandonedAnimals);
        model.addAttribute("members", members);
        model.addAttribute("donationModify", donationModifyForm);

        return "/admin/donation/modifyDonation";
    }

    @PostMapping("donation/modify")
    public String donationModify(@RequestParam("dNumber") int dNumber,
                                 @RequestParam("abNumber") int abNumber,
                                 @RequestParam("mNumber") int mNumber,
                                 @ModelAttribute DonationModifyForm donationModifyForm, Model model, RedirectAttributes redirectAttributes) {
        donationModifyForm.setDNumber(dNumber);
        log.info("dNumber = {}", dNumber);

        donateService.modify(donationModifyForm);

        model.addAttribute("donationModify", donationModifyForm);

        redirectAttributes.addAttribute("dNumber", dNumber);
        redirectAttributes.addAttribute("abNumber", abNumber);
        redirectAttributes.addAttribute("mNumber", mNumber);

        return "redirect:/admin/donation/detail?dNumber={dNumber}&abNumber={abNumber}&mNumber={mNumber}";
    }

    @GetMapping("/donation/delete")
    public String delete(@RequestParam("dNumber") int dNumber) {
        donateService.delete(dNumber);

        return "/admin/donation/deleteDonationSuccess";
    }

}
