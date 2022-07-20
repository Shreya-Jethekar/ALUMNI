package com.example.alumnis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText l_email,l_password;
    private String EMAIL,PASSWORD,NAME;
    int cond=0;
    Boolean bool=false;
    String email;
    FirebaseFirestore fstore;
    String Userid;
    FirebaseAuth fath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        l_email= (EditText) findViewById(R.id.ed_login_emails);
        l_password= (EditText) findViewById(R.id.ed_login_passwords);


        fath= FirebaseAuth.getInstance();

        this.fstore = FirebaseFirestore.getInstance();


////        Keep signup code...
        if(fath.getCurrentUser()!=null)
        {
            startActivity(new Intent(LoginActivity.this,NavActivity.class));
            finish();
        }

    }

    public void btn_login(View view) {
        login();
//        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
    }

    private void login() {
//        l_progressBar.setVisibility(View.VISIBLE);
        EMAIL=l_email.getText().toString().trim();
        PASSWORD=l_password.getText().toString().trim();

        if(TextUtils.isEmpty(EMAIL))
        {
            l_email.setError("Name is required");
        }
        if(TextUtils.isEmpty(PASSWORD))
        {
            l_password.setError("password is required");
        }
        if (PASSWORD.length() <6)
        {
            l_password.setError("password length must be greater than 6 character");
        }
        fath.signInWithEmailAndPassword(EMAIL,PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
//                    l_progressBar.setVisibility(View.INVISIBLE);
                    Intent ii=new Intent(LoginActivity.this, NavActivity.class);
                    startActivity(ii);
                    finish();

                }
                else{
                    Toast.makeText(LoginActivity.this,"Login is not Succesfull",Toast.LENGTH_LONG).show();
//                    l_progressBar.setVisibility(View.INVISIBLE);

                }
            }
        });

    }

    public void forgot_password(View view) {
        Intent i=new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
//        Toast.makeText(this, "forgot", Toast.LENGTH_SHORT).show();

    }

//    public void google_btn_login(View view) {
////        Toast.makeText(this, "google btn", Toast.LENGTH_SHORT).show();
//
//    }

}