package com.tak.article.domain.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupCheckResponse {

    private boolean success;
    private String message;
}
