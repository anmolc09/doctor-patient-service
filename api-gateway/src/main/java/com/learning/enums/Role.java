package com.learning.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ADMIN"),
    USER("USER"),
    GUEST("GUEST");

    private final String role;
}


