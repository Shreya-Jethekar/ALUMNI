package com.example.alumnis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnis.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Result extends AppCompatActivity {
    private  int positionCountry;
TextView  alumni_name_d,alumni_passing_year_d,alumni_contact_no_d,alumni_email_d,alumni_job_status_d,alumni_company_name_d,
        alumni_designation_d,alumni_company_address_d,alumni_ug_project_d,alumni_social_profile_d;
    Button exit,clear;
    public String type_user;

    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        Userid = fauth.getCurrentUser().getUid();


        alumni_name_d=findViewById(R.id.alumni_name_detail); ;
        alumni_passing_year_d=findViewById(R.id.alumni_passing_year_detail);
        alumni_contact_no_d=findViewById(R.id.alumni_contact_no_detail);
        alumni_email_d=findViewById(R.id.alumni_email_id_detail);
        alumni_job_status_d=findViewById(R.id.alumni_job_status_detail);
        alumni_company_name_d=findViewById(R.id.alumni_company_name_detail);
        alumni_designation_d=findViewById(R.id.alumni_designation_detail);
        alumni_company_address_d=findViewById(R.id.alumni_company_address_detail);
        alumni_ug_project_d=findViewById(R.id.alumni_project_details_detail);
        alumni_social_profile_d=findViewById(R.id.alumni_social_profile_detail);
        exit=findViewById(R.id.button_exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });


        fetch();


    }

    private void fetch() {

        Authorization();
        alumni_name_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_NAME);
        alumni_passing_year_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_PASSING_YEAR);
        alumni_email_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_EMAIL);
        alumni_job_status_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_JOB_STATUS);
        alumni_company_name_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_COMPANY_NAME);
        alumni_designation_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_DESIGNATION);
        alumni_company_address_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_COMPANY_ADDRESS);
        alumni_ug_project_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_UG_PROJECT);
        alumni_social_profile_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_SOCIAL_PROFILE);

    }

    private void Authorization() {
        DocumentReference ref = fstore.collection("USERS").document(Userid);
        ref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                   type_user = value.getString("USER_TYPE");
//                Toast.makeText(Result.this, type_user, Toast.LENGTH_SHORT).show();

                if(type_user.equals("ADMIN")){
                    alumni_contact_no_d.setText(HomeFragment.arrayl.get(positionCountry).ALUMNI_CONTACT_NO);
                }
                else{
                    alumni_contact_no_d.setText("Not Authorized!!!");
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}