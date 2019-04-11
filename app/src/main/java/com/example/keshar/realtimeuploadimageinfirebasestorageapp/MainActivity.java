package com.example.keshar.realtimeuploadimageinfirebasestorageapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    private Button btnLogin,btnRegister;
    private FirebaseAuth fbAuth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.etemail);
        password=findViewById(R.id.editText2);
        btnLogin=findViewById(R.id.btnlogin);
        btnRegister=findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
            }
        });
        fbAuth=FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sigin();
            }
        });
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
                    finish();
                }else
                    Toast.makeText(MainActivity.this, "Username or password mismatch", Toast.LENGTH_SHORT).show();

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(authStateListener);
    }

    private void Sigin() {
        String strEmail,strPassword;
        strEmail=email.getText().toString().trim();
        strPassword=password.getText().toString().trim();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Signining....");
        progressDialog.show();
        fbAuth.signInWithEmailAndPassword(strEmail,strPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}
