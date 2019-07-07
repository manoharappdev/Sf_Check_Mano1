package com.spotflock.manohar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Pojos.Register_request;
import com.Pojos.Register_response;
import com.google.gson.Gson;
import com.webservices.APIClient;
import com.webservices.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends Activity {

    Button btn_signup;
    EditText input_name,input_email,input_password,input_password_confirm,input_Mobile;
    RadioGroup radio_Gender;
    RadioButton radio_Select;
    TextView link_login;

    String selected_gender,name,email,password,cnf_password,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_signup=findViewById(R.id.btn_signup);
        input_name=findViewById(R.id.input_name);
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        input_password_confirm=findViewById(R.id.input_password_confirm);
        input_Mobile=findViewById(R.id.input_Mobile);
        radio_Gender=findViewById(R.id.radio_Gender);
        link_login=findViewById(R.id.link_login);

        final int selectedId = radio_Gender.getCheckedRadioButtonId();
        radio_Select = findViewById(selectedId);





        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selected_gender=radio_Select.getText().toString();
                name=input_name.getText().toString();
                password=input_password.getText().toString();
                cnf_password=input_password_confirm.getText().toString();
                mobile=input_Mobile.getText().toString();
                email=input_email.getText().toString();

                if (name != null && !name.equalsIgnoreCase("")&&
                        email != null && !email.equalsIgnoreCase("") &&
                        password != null && !password.equalsIgnoreCase("") &&
                        cnf_password != null && !cnf_password.equalsIgnoreCase("") &&
                        mobile != null && !mobile.equalsIgnoreCase("") &&
                        selected_gender != null && !selected_gender.equalsIgnoreCase(""))
                {
                    RegisterWebService();
                }
                else
                {
                    Toast.makeText(SignupActivity.this,"Please Fill The Fields",Toast.LENGTH_LONG).show();
                }


            }
        });

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignupActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    public void RegisterWebService(){

        try {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<Register_response> call4 = apiInterface.register(new Register_request(name,
                    email,password, cnf_password
                    ,mobile, selected_gender));

            final ProgressDialog progressDoalog;
            progressDoalog = new ProgressDialog(SignupActivity.this);
            progressDoalog.setTitle("Registeration Processing");
            progressDoalog.setMessage("Please wait....");
            progressDoalog.setCancelable(false);
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            progressDoalog.show();


            call4.enqueue(new Callback<Register_response>() {
                @Override
                public void onResponse(Call<Register_response> call, Response<Register_response> response) {


                    Register_response responseVo = response.body();
                    String status = responseVo.success;
                    String message = responseVo.message;
                    // loginResponseVo.UserInfo userInfo = responseVo.user;
                    Log.v("onSuccess", response.body().toString());
                    Log.v("TAG", "response 33: "+new Gson().toJson(response.body()) );
                    progressDoalog.dismiss();

                    try {
                        JSONObject jsonRootObject = new JSONObject(new Gson().toJson(response.body())+"");
                        String status1 = jsonRootObject.optString("status");
                        System.out.println("Status>>>"+status1+"\nDATA"+response.body()+"");
                        if (message.equalsIgnoreCase("Registration Success! Before proceeding, please check your email for a verification link")) {
                            Toast.makeText(SignupActivity.this,"Registration success",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(i);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(SignupActivity.this,"Registration Fail",Toast.LENGTH_LONG).show();
                        Log.v("Fail", response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<Register_response> call, Throwable t) {
                    call.cancel();
                    Toast.makeText(SignupActivity.this,"Registration Fail",Toast.LENGTH_LONG).show();

                    progressDoalog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(SignupActivity.this,"Registration Fail",Toast.LENGTH_LONG).show();
        }
    }

}
