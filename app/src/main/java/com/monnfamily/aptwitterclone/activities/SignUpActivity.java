package com.monnfamily.aptwitterclone.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.monnfamily.aptwitterclone.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_email, et_name, et_password;
    Button btn_signUp, btn_LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_email = findViewById(R.id.et_EmailSignUp);
        et_name = findViewById(R.id.et_NameSignUp);
        et_password = findViewById(R.id.et_signUpass);

        btn_signUp = findViewById(R.id.btn_SignUp);
        btn_LogIn = findViewById(R.id.btn_Login);

        btn_signUp.setOnClickListener(this);
        btn_LogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser()!=null){
            transitionToSocailActivity();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_SignUp:
                final String email = et_email.getText().toString().trim();
                final String name = et_name.getText().toString().trim();
                final String password = et_password.getText().toString().trim();
                if (email.equals("") || name.equals("") || password.equals("")) {
                    Toast.makeText(this, "Email, Name, Password is required.", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ParseUser parseUser = new ParseUser();
                parseUser.setEmail(email);
                parseUser.setUsername(name);
                parseUser.setPassword(password);

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Singing Up");
                progressDialog.setMessage("Singing Up in process");
                progressDialog.show();

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        progressDialog.dismiss();
                        if (e==null){
                            Toast.makeText(SignUpActivity.this, name+" is signed up", Toast.LENGTH_SHORT).show();
                            transitionToSocailActivity();
                        }else{
                            Toast.makeText(SignUpActivity.this, "There is an error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;

            case R.id.btn_Login:
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void transitionToSocailActivity() {
        Intent intent =  new Intent(SignUpActivity.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
