package com.example.alumnis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {

    String user_type;
    EditText r_name, r_contact, r_email, r_password;
    Spinner user_type_spinner;
    public  String NAME,EMAIL,PASSWORD,CONTACT_NO,USER_TYPE;
    private FirebaseFirestore Fstore;
    String USERID;
    private FirebaseAuth mAuth;
    Boolean bool=false;
//    GoogleSignInClient mGoogleSignInClient;
    String[] country = {"Select user type ", "ADMIN", "STUDENT"};

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

            r_name = findViewById(R.id.ed_signup_Names);
            r_contact = findViewById(R.id.ed_signup_contact_nos);
            r_email = findViewById(R.id.ed_signup_emails);
            r_password = findViewById(R.id.ed_signup_passwords);
            user_type_spinner = findViewById(R.id.ed_register_key);
            Fstore = FirebaseFirestore.getInstance();
            mAuth=FirebaseAuth.getInstance();




            SinnerData();

            user_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (l == 0) {
                        user_type = "nill";
//                    Toast.makeText(Register_Activity.this, "plz select user type", Toast.LENGTH_SHORT).show();
                    }
                    if (l == 1) {
                        user_type = "ADMIN";
//                    Toast.makeText(Register_Activity.this, "Student", Toast.LENGTH_SHORT).show();
                    }
                    if (l == 2) {
                        user_type = "STUDENT";
//                    Toast.makeText(Register_Activity.this, "ADMIN", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
//                Toast.makeText(Register_Activity.this, "......", Toast.LENGTH_SHORT).show();

                }
            });

    }

    private void SinnerData() {
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.ed_register_key);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if(position != 0){
            Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void btn_register(View view) {
        Validation();

        if(bool==true){
            SignUp();
        }
//        Toast.makeText(this, "registered", Toast.LENGTH_SHORT).show();
    }

    private void SignUp() {

        mAuth.createUserWithEmailAndPassword(EMAIL,PASSWORD).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            USERID= mAuth.getCurrentUser().getUid();
//                        USERID= Objects.requireNonNull(auth.getCurrentUser()).getUid();
                            DocumentReference dref = Fstore.collection("USERS").document(USERID);

                            Users user=new Users(NAME,CONTACT_NO,EMAIL,PASSWORD,USER_TYPE);

                            dref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignUpActivity.this, "Data Saved..", Toast.LENGTH_SHORT).show();
//                                        r_progressBar.setVisibility(View.INVISIBLE);
//                                          Toast.makeText(Register_Activity.this, "User Created Sucessfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this,NavActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this, "Data Not Saved", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        else{

                            Toast.makeText(SignUpActivity.this, "Error in Auntheticarion", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(SignUpActivity.this,SignUpActivity.class));

                        }


                    }
                });
    }



    private void Validation() {
        int tim=0;
//        r_progressBar.setVisibility(View.VISIBLE);
        NAME=r_name.getText().toString().trim();
        CONTACT_NO=r_contact.getText().toString().trim();
        EMAIL=r_email.getText().toString().trim();
        PASSWORD=r_password.getText().toString().trim();
        USER_TYPE = user_type;

        if(TextUtils.isEmpty(NAME))
        {
            r_name.setError("Name is required");
            tim++;
        }
        if(TextUtils.isEmpty(PASSWORD))
        {
            r_password.setError("password is required");
            tim++;
        }
        if(TextUtils.isEmpty(CONTACT_NO))
        {
            r_contact.setError("Contact No is required");
            tim++;
        }

        if(TextUtils.isEmpty(EMAIL))
        {
            r_email.setError("Name is required");
            tim++;
        }

        if (CONTACT_NO.length() <10)
        {
            r_contact.setError("Contact No must be of 10 digit");
            tim++;
        }
        if (PASSWORD.length() <6)
        {
            r_password.setError("password length must be greater than 6 character");
            tim++;
        }

        if(user_type.equals("nill"))
        {

            Toast.makeText(SignUpActivity.this, "user type selection is manadtory......", Toast.LENGTH_SHORT).show();
            tim++;
        }

        if(tim == 0){
//            Toast.makeText(this, "tim="+tim, Toast.LENGTH_SHORT).show();
            bool=true;
        }

    }
    public void loginpage(View view) {
        Intent i=new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(i);
    }
}