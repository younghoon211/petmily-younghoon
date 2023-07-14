package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalModifyForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalWriteForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdminAdoptForm;
import kh.petmily.domain.adopt.form.AdoptPageForm;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.AdminTempForm;
import kh.petmily.domain.temp.form.TempPageForm;
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

    // 관리자 메인 페이지
    @GetMapping
    public String adminPage() {
        return "/admin/adminPage";
    }

    // =============== 회원정보 관리 ===============
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

    // 회원 정보 수정(update)
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

    // =============== 유기동물 관리 ===============
    // 유기동물 리스트
    @GetMapping("/abandoned_animal")
    public String abandonedAnimalList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AbandonedAnimalPageForm abandonedAnimals = abandonedAnimalService.getAbandonedAnimalPage(pageNo);
        model.addAttribute("abandonedAnimals", abandonedAnimals);

        return "/admin/abandoned_animal/abandonedAnimalList";
    }

    // 유기동물 정보 추가(insert)
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

    // 유기동물 정보 수정(update)
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

    // 유기동물 정보 삭제(delete)
    @GetMapping("/abandoned_animal/delete")
    public String adminAbandonedDelete(@RequestParam("abNumber") int abNumber, RedirectAttributes redirectAttributes) {
        abandonedAnimalService.delete(abNumber);
        redirectAttributes.addAttribute("abNumber", abNumber);

        return "redirect:/admin/abandoned_animal";
    }

    // =============== 게시판 관리 ===============
    // 게시판 리스트
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

    // 게시판 글 추가(insert)
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

    // 게시판 글 수정(update)
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

    // 게시판 글 삭제(delete)
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

    // =============== 입양 정보 관리 ===============
    // 입양 리스트
    @GetMapping("/adopt")
    public String adoptList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        AdoptPageForm adopt = adoptTempService.getAdminAdoptListPage(pageNo);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt/adminAdoptList";
    }

    // 입양 정보 추가(insert)
    @GetMapping("/adopt/write")
    public String adoptWrite(Model model) {
        List<Member> member = adoptTempService.selectAllMemberAdopt();
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllExcludeAdoptStatus();

        model.addAttribute("member", member);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/adopt/adminAdoptWriteForm";
    }

    @PostMapping("/adopt/write")
    public String adoptWriteForm(@ModelAttribute AdminAdoptForm adminAdoptForm) {
        adoptTempService.adminAdoptWrite(adminAdoptForm);
        adoptTempService.updateStatusToAdopt();

        return "/admin/adopt/successAdminAdoptWrite";
    }

    // 입양 정보 수정(update)
    @GetMapping("/adopt/modify")
    public String adoptModify(@RequestParam("adNumber") int adNumber, Model model) {
        List<Member> member = adoptTempService.selectAllMemberAdopt();
        Adopt adopt = adoptTempService.findByAdoptPk(adNumber);
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllAbandonedAnimalAdopt();

        model.addAttribute("member", member);
        model.addAttribute("adopt", adopt);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/adopt/adminAdoptModifyForm";
    }

    @PostMapping("/adopt/modify")
    public String adoptModifyForm(@ModelAttribute AdminAdoptForm adminAdoptForm) {
        adoptTempService.adminAdoptUpdate(adminAdoptForm);
        adoptTempService.updateStatusToAdopt();

        return "/admin/adopt/successAdminAdoptModify";
    }

    // 입양 정보 삭제(delete)
    @GetMapping("/adopt/delete")
    public String adoptDelete(@RequestParam("adNumber") int adNumber) {
        adoptTempService.deleteAdopt(adNumber);

        return "/admin/adopt/successAdminAdoptDelete";
    }


    // 입양 승인 관리 페이지
    @GetMapping("/adopt/wait")
    public String adoptWait(@RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(defaultValue = "처리중") String status,
                            Model model) {
        AdoptPageForm adopt = adoptTempService.getAdminAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt/adminAdoptWaitList";
    }

    // 입양 승인 버튼
    @GetMapping("/adopt/wait/approve")
    public String adoptApprove(@RequestParam("adNumber") int adNumber) {
        adoptTempService.adoptApproveButton(adNumber);

        return "/admin/adopt/successAdoptApprove";
    }

    // 입양 거절 버튼
    @GetMapping("/adopt/wait/refuse")
    public String adoptRefuse(@RequestParam("adNumber") int adNumber) {
        adoptTempService.adoptRefuseButton(adNumber);

        return "/admin/adopt/successAdoptRefuse";
    }

    // 입양 완료된 리스트
    @GetMapping("/adopt/complete")
    public String adoptComplete(@RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "완료") String status,
                                Model model) {
        AdoptPageForm adopt = adoptTempService.getAdminAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt/adoptCompleteList";
    }

    // 입양 거절된 리스트
    @GetMapping("/adopt/refuse")
    public String adoptRefuse(@RequestParam(defaultValue = "1") int pageNo,
                              @RequestParam(defaultValue = "거절") String status,
                              Model model) {

        AdoptPageForm adopt = adoptTempService.getAdminAdoptWaitPage(pageNo, status);
        model.addAttribute("adopt", adopt);

        return "/admin/adopt/adoptRefuseList";
    }

    // =============== 임시보호 정보 관리 ===============
    // 임시보호 리스트
    @GetMapping("/temp")
    public String tempList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
        TempPageForm temp = adoptTempService.getAdminTempListPage(pageNo);
        model.addAttribute("temp", temp);

        return "/admin/temp/adminTempList";
    }

    // 임시보호 정보 추가(insert)
    @GetMapping("/temp/write")
    public String tempWrite(Model model) {
        List<Member> member = adoptTempService.selectAllMemberTemp();
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllExcludeTempStatus();

        model.addAttribute("member", member);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/temp/adminTempWriteForm";
    }

    @PostMapping("/temp/write")
    public String tempWriteForm(@ModelAttribute AdminTempForm adminTempForm) {
        adoptTempService.adminTempWrite(adminTempForm);
        adoptTempService.updateStatusToTemp();
        log.info("adminTempForm={}", adminTempForm);

        return "/admin/temp/successAdminTempWrite";
    }

    // 임시보호 정보 수정(update)
    @GetMapping("/temp/modify")
    public String tempModify(@RequestParam("tNumber") int tNumber, Model model) {
        List<Member> member = adoptTempService.selectAllMemberTemp();
        TempPet tempPet = adoptTempService.findByTempPk(tNumber);
        List<AbandonedAnimal> abandonedAnimal = adoptTempService.selectAllAbandonedAnimalTemp();

        model.addAttribute("member", member);
        model.addAttribute("temp", tempPet);
        model.addAttribute("abandonedAnimal", abandonedAnimal);

        return "/admin/temp/adminTempModifyForm";
    }

    @PostMapping("/temp/modify")
    public String tempModifyForm(@ModelAttribute AdminTempForm adminTempForm) {
        adoptTempService.adminTempUpdate(adminTempForm);
        adoptTempService.updateStatusToTemp();

        return "/admin/temp/successAdminTempModify";
    }

    // 임시보호 정보 삭제(delete)
    @GetMapping("/temp/delete")
    public String tempDelete(@RequestParam("tNumber") int tNumber) {
        adoptTempService.deleteTemp(tNumber);

        return "/admin/temp/successAdminTempDelete";
    }


    // 임시보호 승인 관리 페이지
    @GetMapping("/temp/wait")
    public String tempWait(@RequestParam(defaultValue = "1") int pageNo,
                           @RequestParam(defaultValue = "처리중") String status,
                           Model model) {

        TempPageForm temp = adoptTempService.getAdminTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/temp/adminTempWaitList";
    }

    // 임시보호 승인 버튼
    @GetMapping("/temp/wait/approve")
    public String tempApprove(@RequestParam("tNumber") int tNumber) {
        adoptTempService.tempApproveButton(tNumber);

        return "/admin/temp/successTempApprove";
    }

    // 임시보호 거절 버튼
    @GetMapping("/temp/wait/refuse")
    public String tempRefuse(@RequestParam("tNumber") int tNumber) {
        adoptTempService.tempRefuseButton(tNumber);

        return "/admin/temp/successTempRefuse";
    }

    // 임시보호 완료된 리스트
    @GetMapping("/temp/complete")
    public String tempComplete(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "완료") String status,
                               Model model) {

        TempPageForm temp = adoptTempService.getAdminTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/temp/tempCompleteList";
    }

    // 임시보호 거절된 리스트
    @GetMapping("/temp/refuse")
    public String tempRefuse(@RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "거절") String status,
                             Model model) {

        TempPageForm temp = adoptTempService.getAdminTempWaitPage(pageNo, status);
        model.addAttribute("temp", temp);

        return "/admin/temp/tempRefuseList";
    }

    // =============== 후원 정보 관리 ===============
    // 후원
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
