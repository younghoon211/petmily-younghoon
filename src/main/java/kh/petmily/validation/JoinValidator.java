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

        // ID 중복 검증
        if (memberService.checkDuplicatedId(joinRequest.getId())) {
            errors.rejectValue("id", "duplicated");
        }

        // 안전하지 않은 비번 & 비번!=비번 확인 검증
        if (!securePw) {
            errors.rejectValue("pw", "securePw");
        } else if (joinRequest.getPw() != null && !joinRequest.getPw().equals(joinRequest.getConfirmPw())) {
            errors.rejectValue("confirmPw", "pwIsNotEqualConfirmPw");
        }
    }
}
