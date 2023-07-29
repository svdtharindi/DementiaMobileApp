package com.example.dementiaapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CreateAccount extends AppCompatActivity {

    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    Button createAccountBtn;
    ProgressBar progressBar;
    TextView loginBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createAccountBtn=findViewById(R.id.create_account_Btn);
        emailEditText=findViewById(R.id.email_editText);
        passwordEditText=findViewById(R.id.password_Edittext);
        confirmPasswordEditText=findViewById(R.id.Confirm_password_Edittext);

        progressBar=findViewById(R.id.progressbar);
        loginBtnTextView=findViewById(R.id.Login_text_view);


        createAccountBtn.setOnClickListener(v->createAccount());
        loginBtnTextView.setOnClickListener(v-> finish());
    }

    void createAccount(){
        String email =emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String confirmPassword=confirmPasswordEditText.getText().toString();

        boolean isValidated =validateData(email,password,confirmPassword);
        if(!isValidated){
            return ;
        }
        createAccountInFirebase(email,password);
    }

    void createAccountInFirebase(String email,String password){
        changeInProgress(true);
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //creating account is success
                    // Toast.makeText(CreateAccountActivity.this,"Successfully created the account,check email to vertify",Toast.LENGTH_SHORT).show();
                    //utility class is made to make it easy to diplay toast messages without always typing the above code part for toast message
                    Utility.showToast(CreateAccount.this,"Successfully created the account,check email to vertify");
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                }else{
                    //failure
                    // Toast.makeText(CreateAccountActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    Utility.showToast(CreateAccount.this,task.getException().getLocalizedMessage());
                }
            }
        });
    }

    //to update progressbar created
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            createAccountBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            createAccountBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password,String confirmPassword){
        //validate the data input by user

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }

        if(password.length()<6){
            passwordEditText.setError("password length is invalid");
            return false;

        }

        if(!password.equals(confirmPassword)){
            confirmPasswordEditText.setError("password not matched");
            return false;
        }

        return true;

    }
}