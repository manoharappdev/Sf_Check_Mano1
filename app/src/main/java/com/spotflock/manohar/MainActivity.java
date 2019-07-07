package com.spotflock.manohar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Pojos.loginRequest;
import com.Pojos.loginResponseVo;
import com.google.gson.Gson;
import com.webservices.APIClient;
import com.webservices.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity {


    EditText _nameText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _nameText=findViewById(R.id.input_email);
        _passwordText=findViewById(R.id.input_password);
        _loginButton=findViewById(R.id.btn_login);
        _signupButton=findViewById(R.id.link_signup);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (_nameText.getText().toString() != null && !_nameText.getText().toString().equalsIgnoreCase("")&&
                        _passwordText.getText().toString() != null &&  !_passwordText.getText().toString().equalsIgnoreCase("")) {
                    loginWebService();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please Fill The Fields",Toast.LENGTH_LONG).show();
                }

            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }


    public void loginWebService(){

        try {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<loginResponseVo> call4 = apiInterface.login(new loginRequest(_nameText.getText().toString(),_passwordText.getText().toString()));

            final ProgressDialog progressDoalog;
            progressDoalog = new ProgressDialog(MainActivity.this);
            progressDoalog.setTitle("Authenticating");
            progressDoalog.setMessage("Please wait....");
            progressDoalog.setCancelable(false);
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            progressDoalog.show();


            call4.enqueue(new Callback<loginResponseVo>() {
                @Override
                public void onResponse(Call<loginResponseVo> call, Response<loginResponseVo> response) {


                    loginResponseVo responseVo = response.body();
                    String status = responseVo.status;
                    String message = responseVo.message;
                    loginResponseVo.UserInfo userInfo = responseVo.user;
                    Log.v("onSuccess", response.body().toString());
                    //  Toast.makeText(getApplicationContext(), (response.message())+"\n"+new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();
                    Log.v("TAG", "response 33: "+new Gson().toJson(response.body()) );
                    progressDoalog.dismiss();

                    try {
                        JSONObject jsonRootObject = new JSONObject(new Gson().toJson(response.body())+"");
                        String status1 = jsonRootObject.optString("status");
                        System.out.println("Status>>>"+status1+"\nDATA"+response.body()+"");
                        if (status.equalsIgnoreCase("true")) {
                            Toast.makeText(MainActivity.this,"Authentication success",Toast.LENGTH_LONG).show();

                            Intent i=new Intent(MainActivity.this,Newsfeed.class);
                            startActivity(i);
                            ClearFields();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"Authentication Fail",Toast.LENGTH_LONG).show();
                        ClearFields();

                    }
                }

                @Override
                public void onFailure(Call<loginResponseVo> call, Throwable t) {
                    call.cancel();
                    Toast.makeText(MainActivity.this,"Authentication Fail",Toast.LENGTH_LONG).show();
                    ClearFields();
                    progressDoalog.dismiss();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Authentication Fail",Toast.LENGTH_LONG).show();
            ClearFields();
        }
    }

    public void ClearFields(){
        _nameText.setText("");
        _passwordText.setText("");


    }

}

