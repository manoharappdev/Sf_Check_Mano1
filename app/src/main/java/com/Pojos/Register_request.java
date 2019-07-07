package com.Pojos;

public class Register_request {

/*    {
        "name":"Manohar",
            "email":"manohar.appdev@gmail.com",
            "password":"12345",
            "password_confirmation":"12345",
            "mobile":"9177343616",
            "gender":"Male"
    }*/

    final String name;
    final String email;
    final String password;
    final String password_confirmation;
    final String mobile;
    final String gender;


    public Register_request(String name, String email, String password, String password_confirmation
            , String mobile, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.mobile = mobile;
        this.gender = gender;
    }

}
