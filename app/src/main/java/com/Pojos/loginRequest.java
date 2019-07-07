package com.Pojos;

import java.io.Serializable;

public class loginRequest  {

    final String email;
    final String password;

    public loginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

