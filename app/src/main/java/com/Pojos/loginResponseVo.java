package com.Pojos;

import android.media.MediaRouter;

import com.google.gson.annotations.SerializedName;

public class loginResponseVo {


/*
    {
        "status": "true",
            "message": "Authentication Success",
            "user": {
        "id": 25,
                "name": "Phaneendra Hari",
                "email": "hari2@spotflock.com",
                "email_verified_at": null,
                "api_token": "H5X1Hmd9GasItkzZMI5tElzcHds9KEQiIBI8KOXNBhDhPuvWWHBvGabUlxf7",
                "rate_limit": "60",
                "wallet_balance": 0,
                "facebook": 0,
                "google": 0,
                "twitter": 0,
                "user_type": "Student",
                "deleted_at": null,
                "created_at": "2019-07-06 05:38:21",
                "updated_at": "2019-07-06 05:38:21"
    }
    }*/



    @SerializedName("status")
    public String status;


    @SerializedName("message")
    public String message;


    @SerializedName("user")
    public UserInfo user = new UserInfo();

    public class UserInfo {


        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("email")
        public String email;
        @SerializedName("email_verified_at")
        public String email_verified_at;

        @SerializedName("api_token")
        public String api_token;

        @SerializedName("rate_limit")
        public String rate_limit ;

        @SerializedName("wallet_balance")
        public String wallet_balance;

        @SerializedName("facebook")
        public String facebook;

        @SerializedName("google")
        public String google ;

        @SerializedName("twitter")
        public String twitter;



        @SerializedName("user_type")
        public String user_type;
        @SerializedName("deleted_at")
        public String deleted_at;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;


    }
}
