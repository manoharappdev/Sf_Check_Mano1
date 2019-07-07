package com.Pojos;

import com.google.gson.annotations.SerializedName;

public class Register_response {

    /* Success Case Response
    {
        "success": true,
            "message": "Registration Success! Before proceeding, please check your email for a verification link"
    }*/
//

/* Failure Case Response
{
        "message": "The given data was invalid.",
            "errors": {
        "email": [
        "The email has already been taken."
        ]
    }
    }*/

    @SerializedName("success")
    public String success;


    @SerializedName("message")
    public String message;

}
