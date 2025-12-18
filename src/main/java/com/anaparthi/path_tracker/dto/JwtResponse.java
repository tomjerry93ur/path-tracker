package com.anaparthi.path_tracker.dto;

public class JwtResponse {

    private String access_token;
    private String token_type;
    private long expires_in;

    public JwtResponse(String accessToken,String token_type, long expiresIn) {
        this.access_token = accessToken;
        this.token_type = "bearer";
        this.expires_in = expiresIn;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }
}
