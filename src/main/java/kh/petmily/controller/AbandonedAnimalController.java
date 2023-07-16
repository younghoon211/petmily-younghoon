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
        log.info("abandonedAnimalConditionForm = {}", conditionForm);

        AbandonedAnimalPageForm abandonedAnimalPageForm = abandonedAnimalService.getAbandonedAnimalPage(conditionForm);
        model.addAttribute("abandonedAnimals", abandonedAnimalPageForm);

        return "/abandoned_animal/listAbandonedAnimal";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
        log.info("detailForm = {}", detailForm);

        model.addAttribute("detailForm", detailForm);

        return "/abandoned_animal/detailAbandonedAnimal";
    }

    //=======후원=======
    @GetMapping("/auth/donate")
    public String donateForm(@RequestParam int abNumber, HttpServletRequest request, Model model) {
        int mNumber = getAuthMember(request).getMNumber();

        model.addAttribute("animalName", abandonedAnimalService.findName(abNumber));
        model.addAttribute("memberName", memberService.findName(mNumber));

        return "/abandoned_animal/donateSubmitForm";
    }

    @PostMapping("/auth/donate")
    public String donate(@ModelAttribute DonateSubmitForm donateSubmitForm, HttpServletRequest request) {
        int mNumber = getAuthMember(request).getMNumber();

        donateSubmitForm.setMNumber(mNumber);
        log.info("donateSubmitForm = {}", donateSubmitForm);

        donateService.donate(donateSubmitForm);

        return "/abandoned_animal/submitSuccess";
    }

    //=======입양/임보=======
    @GetMapping("/auth/adopt_temp")
    public String adoptTempForm(@RequestParam int abNumber, HttpServletRequest request, Model model) {
        int mNumber = getAuthMember(request).getMNumber();

        model.addAttribute("animal", abandonedAnimalService.getAnimal(abNumber));
        model.addAttribute("memberName", memberService.findName(mNumber));

        return "/abandoned_animal/adoptTempSubmitForm";
    }

    @PostMapping("/auth/adopt_temp")
    public String adoptTemp(@ModelAttribute AdoptTempSubmitForm form,
                            @RequestParam String adoptOrTemp,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {

        log.info("adoptTempSubmitForm = {}", form);

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        form.setMNumber(mNumber);

        if (adoptOrTemp.equals("adopt")) {
            adoptTempService.adopt(form);
        }

        if (adoptOrTemp.equals("temp")) {
            adoptTempService.temp(form);
        }

        redirectAttributes.addAttribute("abNumber", form.getAbNumber());

        return "/abandoned_animal/submitSuccess";
    }

    //=======봉사=======
    @GetMapping("/auth/volunteer")
    public String volunteerForm(@RequestParam int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
        model.addAttribute("detailForm", detailForm);

        return "/abandoned_animal/volunteerForm";
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");
        return member;
    }
}
