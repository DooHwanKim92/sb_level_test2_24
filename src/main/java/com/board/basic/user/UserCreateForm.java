package com.board.basic.user;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "ID는 필수항목 입니다.")
    private String username;
    @NotEmpty(message = "비밀번호는 필수항목 입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호 확인은 필수항목 입니다.")
    private String password2;
}
