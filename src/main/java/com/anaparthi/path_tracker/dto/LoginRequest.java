package com.anaparthi.path_tracker.dto;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {

    private String username;
    private String password;

}
