package petmily.validation;

import petmily.domain.member.form.JoinForm;
import petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm joinForm = (JoinForm) target;

        String pwPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$";
        Boolean securePw = Pattern.matches(pwPattern, joinForm.getPw());

        // ID 중복체크
        if (memberService.checkDuplicatedId(joinForm.getId())) {
            errors.rejectValue("id", "duplicatedId");
        }

        // 안전하지 않은 비번 & 비번!=비번 확인
        if (!securePw) {
            errors.rejectValue("pw", "securePw");
        } else if (joinForm.getPw() != null && !joinForm.getPw().equals(joinForm.getConfirmPw())) {
            errors.rejectValue("confirmPw", "pwIsNotEqualConfirmPw");
        }

        // 성별 미선택 시
        if (!("M".equals(joinForm.getGender()) || "F".equals(joinForm.getGender()))) {
            errors.rejectValue("gender", "unSelectedGender");
        }

        // 이메일 중복체크
        if (memberService.checkDuplicatedEmail(joinForm.getEmail())) {
            errors.rejectValue("email", "duplicatedEmail");
        }

        // 전화번호 중복체크
        if (memberService.checkDuplicatedPhone(joinForm.getPhone())) {
            errors.rejectValue("phone", "duplicatedPhone");
        }
    }
}
