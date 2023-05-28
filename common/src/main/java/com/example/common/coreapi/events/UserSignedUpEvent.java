package com.example.common.coreapi.events;

import lombok.Data;

@Data
public class UserSignedUpEvent {

    private final String userId;
    private final String username;
    private final String password;
    private final String email;
}

