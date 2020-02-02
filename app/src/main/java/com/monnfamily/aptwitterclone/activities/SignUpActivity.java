package com.monnfamily.aptwitterclone.activities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.monnfamily.aptwitterclone.R;

public class SignUpActivity extends AppCompatActivity {

    EditText et_email,et_name,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_email = findViewById(R.id.et_EmailSignUp);
        et_name= findViewById(R.id.et_NameSignUp);
        et_password = findViewById(R.id.et_signUpass);


    }

}
