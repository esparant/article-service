package com.tak.article.domain.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupForm {

    public SignupForm(String password, String username, String nickname) {
        this.password = password;
        this.username = username;
        this.nickname = nickname;
    }

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String checkPassword;

    @NotBlank
    private String nickname;
}
