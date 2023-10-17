package petmily.domain.admin_form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class MemberInsertForm {

    @NotBlank
    @Size(min = 1)
    private String id;

    @NotBlank
    @Size(min = 1)
    private String pw;

    @NotBlank
    @Size(min = 1)
    private String name;

    @NotBlank
    @Size(min = 10, max = 10)
    private String birth;

    @NotBlank
    @Size(min = 1)
    private String gender;

    @NotBlank
    @Size(min = 1)
    private String email;

    @NotBlank
    @Size(min = 1)
    private String phone;
}
