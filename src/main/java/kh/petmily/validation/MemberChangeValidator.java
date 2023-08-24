package kh.petmily.validation;

import kh.petmily.domain.member.form.MemberChangeForm;
import kh.petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class MemberChangeValidator implements Validator {

    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberChangeForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberChangeForm memberChangeForm = (MemberChangeForm) target;

        String pwPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$";
        Boolean securePw = Pattern.matches(pwPattern, memberChangeForm.getPw());


        // 안전하지 않은 비번
        if (!securePw) {
            errors.rejectValue("pw", "securePw");
        }

        // 이메일 중복체크
        if (!memberService.checkDuplicatedEmailChangeInfo(memberChangeForm.getEmail(), memberChangeForm.getId())) {
            errors.rejectValue("email", "duplicatedEmail");
        }

        // 전화번호 중복체크
        if (memberService.checkDuplicatedPhoneChangeInfo(memberChangeForm)) {
            errors.rejectValue("phone", "duplicatedPhone");
        }
    }
}
