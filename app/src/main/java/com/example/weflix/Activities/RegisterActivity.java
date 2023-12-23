package com.example.weflix.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.weflix.DBHelper;
import com.example.weflix.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatEditText editTextUsername, editTextPassword, editTextConfirm;
    private Button buttonReg,buttonLog;
    DBHelper dbHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUsername = findViewById(R.id.editTextUsername1);
        editTextPassword = findViewById(R.id.editTextPassword1);
        editTextConfirm = findViewById(R.id.editTextPassword2);
        buttonReg = findViewById(R.id.registerBtn1);
        buttonLog = findViewById(R.id.loginBtn1);
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        dbHelper = new  DBHelper(this);

        buttonReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username, password, confirm;
                username = String.valueOf(editTextUsername.getText());
                password = String.valueOf(editTextPassword.getText());
                confirm = String.valueOf(editTextConfirm.getText());

                if (username.equals("") || password.equals("") || confirm.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Semua kolom Text Wajib Diisi", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm)){
                        if(dbHelper.checkusername(username)){
                            Toast.makeText(RegisterActivity.this, "User sudah ada",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean registersukses = dbHelper.insertData(username,password);
                        if(registersukses){
                            Toast.makeText(RegisterActivity.this, "Berhasil Menambahkan User",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registrasi Gagal",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this,"Password tidak sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

