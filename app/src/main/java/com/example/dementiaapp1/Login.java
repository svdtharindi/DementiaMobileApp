package com.example.dementiaapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    Button loginBtn;
    ProgressBar progressBar;
    TextView CreateAccountBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn=findViewById(R.id.Login_Btn);
        emailEditText=findViewById(R.id.email_editText);
        passwordEditText=findViewById(R.id.password_Edittext);

        progressBar=findViewById(R.id.progressbar);
        CreateAccountBtnTextView=findViewById(R.id.CreateAccount_text_view);

        loginBtn.setOnClickListener((v)->loginUser());
        CreateAccountBtnTextView.setOnClickListener((v)->startActivity(new Intent(Login.this,CreateAccount.class)));
    }


    void loginUser(){
        String email =emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();


        boolean isValidated =validateData(email,password);
        if(!isValidated){
            return ;
        }
        loginAccountInFirebase(email,password);
    }

    void loginAccountInFirebase(String email,String password){
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful()){
                    //login is success
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        //go to main activity
                        startActivity(new Intent(Login.this,Dashboard.class));
                        finish();
                    }else{
                        Utility.showToast(Login.this,"Email not vertified <please vertify your email.");

                    }
                }else{
                    //login failed
                    Utility.showToast(Login.this,task.getException().getLocalizedMessage());
                }
            }
        });

    }

    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password){
        //validate the data input by user

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }

        if(password.length()<6){
            passwordEditText.setError("password length is invalid");
            return false;

        }



        return true;

    }
}