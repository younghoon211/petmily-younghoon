package kh.petmily.validation;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.service.MemberService;
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
        return JoinRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinRequest joinRequest = (JoinRequest) target;

        String pwPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$";
        Boolean securePw = Pattern.matches(pwPattern, joinRequest.getPw());

        // ID 중복체크
        if (memberService.checkDuplicatedId(joinRequest.getId())) {
            errors.rejectValue("id", "duplicatedId");
        }

        // 안전하지 않은 비번 & 비번!=비번 확인
        if (!securePw) {
            errors.rejectValue("pw", "securePw");
        } else if (joinRequest.getPw() != null && !joinRequest.getPw().equals(joinRequest.getConfirmPw())) {
            errors.rejectValue("confirmPw", "pwIsNotEqualConfirmPw");
        }

        // 성별 미선택 시
        if (!(joinRequest.getGender().equals("M") || joinRequest.getGender().equals("F"))) {
            errors.rejectValue("gender", "unSelectedGender");
        }

        // 이메일 중복체크
        if (memberService.checkDuplicatedEmail(joinRequest.getEmail())) {
            errors.rejectValue("email", "duplicatedEmail");
        }

        // 전화번호 중복체크
        if (memberService.checkDuplicatedPhone(joinRequest.getPhone())) {
            errors.rejectValue("phone", "duplicatedPhone");
        }
    }
}
