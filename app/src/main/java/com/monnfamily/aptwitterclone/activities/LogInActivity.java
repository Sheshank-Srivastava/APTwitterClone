package com.monnfamily.aptwitterclone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.monnfamily.aptwitterclone.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword;
    Button btn_SignUp, btn_LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_EmailSignUp);
        etPassword = findViewById(R.id.et_signUpass);

        btn_LogIn = findViewById(R.id.btn_Login);
        btn_SignUp = findViewById(R.id.btn_SignUp);

        btn_LogIn.setOnClickListener(this);
        btn_SignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_Login:
                String email =  etEmail.getText().toString().trim();
                String pass =  etPassword.getText().toString().trim();

                ParseUser.logInInBackground(email, pass, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                            if (user== null || e!=null){
                                Toast.makeText(LogInActivity.this, "Email or Password  is required", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            transitionSocialActivity();
                    }
                });
                break;
            case R.id.btn_SignUp:
                Intent intent =  new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    private void transitionSocialActivity() {
        Intent intent =  new Intent(LogInActivity.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
