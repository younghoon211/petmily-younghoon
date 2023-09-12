package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import petmily.domain.abandoned_animal.form.*;
import petmily.domain.adopt.Adopt;
import petmily.domain.temp.TempPet;
import petmily.service.AbandonedAnimalService;
import petmily.service.AdoptTempService;
import petmily.service.DonateService;
import petmily.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/abandonedAnimal")
@RequiredArgsConstructor
@Slf4j
public class AbandonedAnimalController {

    private final AbandonedAnimalService abandonedAnimalService;
    private final AdoptTempService adoptTempService;
    private final DonateService donateService;
    private final MemberService memberService;

    // 유기동물 리스트
    @GetMapping("/list")
    public String list(@Validated @ModelAttribute AbandonedAnimalConditionForm conditionForm, Model model) {
        AbandonedAnimalPageForm pageForm = abandonedAnimalService.getListPage(conditionForm);
        model.addAttribute("pageForm", pageForm);

        return "/abandoned.animal/abandoned_animal_list";
    }

    // 유기동물 상세보기
    @GetMapping("/detail")
    public String detail(@RequestParam int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailPage(abNumber);
        Adopt adopt = abandonedAnimalService.getAdoptByPk(abNumber);

        model.addAttribute("detailForm", detailForm);
        model.addAttribute("adopt", adopt);

        return "/abandoned.animal/abandoned_animal_detail";
    }

    // 입양완료 리스트
    @GetMapping("/adoptedList")
    public String adoptedList(@ModelAttribute AbandonedAnimalConditionForm conditionForm, Model model) {
        log.info("GET AbandonedAnimalConditionForm = {}", conditionForm);
        AbandonedAnimalPageForm pageForm = abandonedAnimalService.getAdoptedListPage(conditionForm);

        model.addAttribute("pageForm", pageForm);

        return "/abandoned.animal/adopted_animal_list";
    }

    // 후원 신청
    @GetMapping("/auth/donate")
    public String donatePage(@RequestParam int abNumber, Model model) {
        model.addAttribute("abAnimal", abandonedAnimalService.getAbAnimal(abNumber));

        return "/abandoned.animal/submit_donate";
    }

    @PostMapping("/auth/donate")
    public String donate(@ModelAttribute DonateSubmitForm donateSubmitForm) {
        log.info("POST donateSubmitForm = {}", donateSubmitForm);

        donateService.donate(donateSubmitForm);

        return "/alert/member/donate_submit";
    }

    // 입양/임보 신청
    @GetMapping("/auth/adoptTemp")
    public String adoptTempPage(@RequestParam int abNumber, Model model) {
        TempPet temp = abandonedAnimalService.getTempByPk(abNumber);

        model.addAttribute("temp", temp);
        model.addAttribute("abAnimal", abandonedAnimalService.getAbAnimal(abNumber));
        model.addAttribute("residences", adoptTempService.getResidenceList());

        return "/abandoned.animal/submit_adopt_temp";
    }

    @PostMapping("/auth/adoptTemp")
    public String adoptTemp(@ModelAttribute AdoptTempSubmitForm submitForm) {
        log.info("POST adoptTempSubmitForm = {}", submitForm);

        if (submitForm.getAdoptOrTemp().equals("adopt")) {
            adoptTempService.adopt(submitForm);
            return "/alert/member/adopt_submit";
        }

        if (submitForm.getAdoptOrTemp().equals("temp")) {
            adoptTempService.temp(submitForm);
            return "/alert/member/temp_submit";
        }

        return null;
    }

    // 봉사 신청
    @GetMapping("/auth/volunteer")
    public String volunteerPage(@RequestParam int abNumber, Model model) {
        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailPage(abNumber);
        model.addAttribute("detailForm", detailForm);

        return "/abandoned.animal/submit_volunteer";
    }

    // 이미지파일 불러오기
    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> getImage(@RequestParam String filename, HttpServletRequest request) {
        try {
            Path imagePath = Paths.get(getFullPath(request) + filename);
            log.info("GET imagePath = {} ", imagePath);

            Resource resource = new UrlResource(imagePath.toUri());
            MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.IMAGE_PNG);

            if (!resource.exists()) {
                throw new FileNotFoundException("존재하지 않는 파일입니다.");
            }

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(resource);
        } catch (MalformedURLException e) {
            log.error("Malformed URL = {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (FileNotFoundException e) {
            log.error("Image not found = {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private String getFullPath(HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        return fullPath;
    }
}
