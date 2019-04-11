package com.example.keshar.realtimeuploadimageinfirebasestorageapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private Button btnRegister;
    private TextView backText;
    private FirebaseAuth fbAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regerster);
        etEmail=findViewById(R.id.etemail);
        etPassword=findViewById(R.id.editText2);
        btnRegister=findViewById(R.id.btn_register);
        backText=findViewById(R.id.txt_back);
        fbAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegiserUser();
            }
        });

    }

    private void RegiserUser() {
        String email,password;
        email=etEmail.getText().toString().trim();
        password=etPassword.getText().toString().trim();


        if(ValidationCheck.isValidForEmail(email,etEmail) && ValidationCheck.isValidForPassword(password,etPassword)){
            progressDialog.setMessage("Registration....");
            progressDialog.show();
            fbAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Register Successful...", Toast.LENGTH_SHORT).show();
                                etEmail.setText("");
                                etPassword.setText("");
                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Register Failed....", Toast.LENGTH_SHORT).show();


                            }


                        }
                    });


        }
    }

    public void LoginPage(View view) {
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        finish();
    }
}

