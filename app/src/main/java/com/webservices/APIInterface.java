package com.webservices;


import com.Pojos.Register_request;
import com.Pojos.Register_response;
import com.Pojos.loginRequest;
import com.Pojos.loginResponseVo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//login,register,kstream
//{
//	"name":"Phaneendra Hari",
//	"email":"hari2@spotflock.com",
//	"password":"akjshdlaks",
//	"password_confirmation":"akjshdlaks",
//	"mobile":"9959277190",
//	"gender":"Male"
//}
/*{
        "email":"hari2@spotflock.com",
        "password":"akjshdlaks"
        }*/

public interface APIInterface {



    @POST("login")
    Call<loginResponseVo>  login(@Body loginRequest body);


    @POST("register")
    Call<Register_response>  register(@Body Register_request body);



}
