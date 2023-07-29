package kh.petmily.controller;

import kh.petmily.domain.abandoned_animal.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.service.AbandonedAnimalService;
import kh.petmily.service.AdoptTempService;
import kh.petmily.service.DonateService;
import kh.petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/abandoned_animal")
@RequiredArgsConstructor
@Slf4j
public class AbandonedAnimalController {

    private final AbandonedAnimalService abandonedAnimalService;
    private final AdoptTempService adoptTempService;
    private final DonateService donateService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute AbandonedAnimalConditionForm conditionForm, Model model) {
        AbandonedAnimalPageForm pageForm = abandonedAnimalService.getListPage(conditionForm);
        model.addAttribute("pageForm", pageForm);

        return "/abandoned.animal/abandoned_animal_list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailPage(abNumber);
        model.addAttribute("detailForm", detailForm);

        return "/abandoned.animal/abandoned_animal_detail";
    }

    //=======후원=======
    @GetMapping("/auth/donate")
    public String donateForm(@RequestParam int abNumber, HttpServletRequest request, Model model) {
        int mNumber = getAuthMember(request).getMNumber();

        model.addAttribute("animalName", abandonedAnimalService.getAnimalName(abNumber));
        model.addAttribute("memberName", memberService.getMemberName(mNumber));

        return "/abandoned.animal/donate_submit";
    }

    @PostMapping("/auth/donate")
    public String donate(@ModelAttribute DonateSubmitForm donateSubmitForm, HttpServletRequest request) {
        int mNumber = getAuthMember(request).getMNumber();

        donateSubmitForm.setMNumber(mNumber);
        log.info("donateSubmitForm = {}", donateSubmitForm);

        donateService.donate(donateSubmitForm);

        return "/abandoned.animal/alert_submit";
    }

    //=======입양/임보=======
    @GetMapping("/auth/adopt_temp")
    public String adoptTempForm(@RequestParam int abNumber, HttpServletRequest request, Model model) {
        int mNumber = getAuthMember(request).getMNumber();

        model.addAttribute("animal", abandonedAnimalService.getAnimal(abNumber));
        model.addAttribute("memberName", memberService.getMemberName(mNumber));

        return "/abandoned.animal/adopt_temp_submit";
    }

    @PostMapping("/auth/adopt_temp")
    public String adoptTemp(@ModelAttribute AdoptTempSubmitForm submitForm,
                            @RequestParam String adoptOrTemp,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {

        log.info("adoptTempSubmitForm = {}", submitForm);

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        submitForm.setMNumber(mNumber);

        if (adoptOrTemp.equals("adopt")) {
            adoptTempService.adopt(submitForm);
        }

        if (adoptOrTemp.equals("temp")) {
            adoptTempService.temp(submitForm);
        }

        redirectAttributes.addAttribute("abNumber", submitForm.getAbNumber());

        return "/abandoned.animal/alert_submit";
    }

    //=======봉사=======
    @GetMapping("/auth/volunteer")
    public String volunteerForm(@RequestParam int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailPage(abNumber);
        model.addAttribute("detailForm", detailForm);

        return "/abandoned.animal/volunteer_submit";
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}
