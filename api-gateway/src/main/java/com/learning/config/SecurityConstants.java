package com.learning.config;

public interface SecurityConstants {
    String SIGN_UP_URL= "/api/users/register";
    String TOKEN_SECRET = "skillEnhancer";
    Integer EXPIRATION_TIME = 36000000; //10 hours
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
