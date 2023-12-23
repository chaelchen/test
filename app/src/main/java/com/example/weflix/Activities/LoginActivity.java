package com.example.weflix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weflix.DBHelper;
import com.example.weflix.R;

public class LoginActivity extends AppCompatActivity {
    DBHelper dbhelper;
    private EditText etusername,etpassword;
    private Button loginBtn, registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        dbhelper = new DBHelper(this);
        etusername=findViewById(R.id.editTextUsername);
        etpassword=findViewById(R.id.editTextPassword);
        loginBtn=findViewById(R.id.loginBtn);
        registerBtn=findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean logid = dbhelper.checkuser(etusername.getText().toString(),etpassword.getText().toString());
                if(logid){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(v -> {

            Log.d("LoginActivity", "Register button clicked");

            startActivity(new Intent(getBaseContext(),RegisterActivity.class));
        });

//        registerBtn.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View v){
//                Intent gotoactivity2 = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(gotoactivity2, 0);
//            }
//        });

    }
}