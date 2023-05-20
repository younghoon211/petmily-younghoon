package kh.petmily.validation;

import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

//        int idLength = joinRequest.getId().trim().length();

        if (memberService.checkDuplicatedId(joinRequest.getId())) {
            errors.rejectValue("id", "duplicated");
            System.out.println("sout: ID 중복 validation 발생!!!!!!!!!!!!");
        }

//        if (joinRequest.getPw()) {
//            errors.rejectValue("pw", "security");
//            System.out.println("sout: PW security validation 발생!!!!!!!!!!!!");
//        }

//        else if (CustomMethod.isNullOrEmpty(joinRequest.getId()) || idLength == 1 || idLength == 2) {
//            errors.rejectValue("id", "required");
//            System.out.println("sout: id 공백 또는 1~2자 validation 발생!!!!!!!!!!!!");
//        }


    }
}
