package com.example.alumnis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.firestore.FirebaseFirestore;

public class UPDATE extends AppCompatActivity {
    TextView addtitle;
    int chk=0;
    private FirebaseFirestore f;
   private Alumni_Data ad;
    EditText alumni_name_u, alumni_passing_year_u, alumni_contact_no_u, alumni_email_u,
            alumni_job_status_u, alumni_company_name_u, alumni_designation_u, alumni_company_address_u
            ,alumni_ug_project_u, alumni_social_profile_u;
    Button update, clear_u;
    public String ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS,ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE;

    public  void deleteProduct() {
        f.collection("ALUMNIS").document(ad.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UPDATE.this, "Product deleted", Toast.LENGTH_LONG).show();
//                            context.finish();
                           startActivity(new Intent(UPDATE.this, Add_AlumniActivity.class));
                           finish();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

         ad= (Alumni_Data) getIntent().getSerializableExtra("product");
         f=FirebaseFirestore.getInstance();

        alumni_name_u = findViewById(R.id.alumni_name_update);
        alumni_passing_year_u = findViewById(R.id.alumni_passing_year_update);
        alumni_contact_no_u = findViewById(R.id.alumni_contact_no_update);
        alumni_email_u = findViewById(R.id.alumni_email_id_update);
        alumni_job_status_u = findViewById(R.id.alumni_job_status_upate);
        alumni_company_name_u = findViewById(R.id.alumni_company_name_update);
        alumni_company_address_u = findViewById(R.id.alumni_company_address_update);
        alumni_designation_u = findViewById(R.id.alumni_designation_update);
        alumni_ug_project_u = findViewById(R.id.alumni_project_details_update);
        alumni_social_profile_u = findViewById(R.id.alumni_social_profile_update);

        update = findViewById(R.id.button_save_update);
        clear_u = findViewById(R.id.button_clear_update);

        //Setting value....

        alumni_name_u.setText(ad.ALUMNI_NAME);
        alumni_passing_year_u.setText(ad.ALUMNI_PASSING_YEAR);
        alumni_contact_no_u.setText(ad.ALUMNI_CONTACT_NO);
        alumni_email_u.setText(ad.ALUMNI_EMAIL);
        alumni_job_status_u.setText(ad.ALUMNI_JOB_STATUS);
        alumni_company_name_u.setText(ad.ALUMNI_COMPANY_NAME);
        alumni_designation_u.setText(ad.ALUMNI_DESIGNATION);
        alumni_company_address_u.setText(ad.ALUMNI_COMPANY_ADDRESS);
        alumni_ug_project_u.setText(ad.ALUMNI_UG_PROJECT);
        alumni_social_profile_u.setText(ad.ALUMNI_SOCIAL_PROFILE);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                         ALUMNI_NAME=alumni_name_u.getText().toString().trim();
                         ALUMNI_PASSING_YEAR=alumni_passing_year_u.getText().toString().trim();
                         ALUMNI_CONTACT_NO=alumni_contact_no_u.getText().toString().trim();
                         ALUMNI_EMAIL=alumni_email_u.getText().toString().trim();
                         ALUMNI_JOB_STATUS=alumni_job_status_u.getText().toString().trim();
                         ALUMNI_COMPANY_NAME=alumni_company_name_u.getText().toString().trim();
                         ALUMNI_DESIGNATION=alumni_designation_u.getText().toString().trim();
                         ALUMNI_COMPANY_ADDRESS=alumni_company_address_u.getText().toString().trim();
                         ALUMNI_UG_PROJECT= alumni_ug_project_u.getText().toString().trim();
                         ALUMNI_SOCIAL_PROFILE =alumni_social_profile_u.getText().toString().trim();


                if(TextUtils.isEmpty(ALUMNI_NAME))
                {
                    alumni_name_u.setError("Name is required");
                    chk++;
                }
                if(TextUtils.isEmpty(ALUMNI_PASSING_YEAR))
                {
                    alumni_passing_year_u.setError("passing year is required");
                    chk++;
                }
                if(TextUtils.isEmpty(ALUMNI_CONTACT_NO))
                {
                    alumni_contact_no_u.setError("contact no is required");
                    chk++;
                }
                if(TextUtils.isEmpty(ALUMNI_EMAIL))
                {
                    alumni_email_u.setError("Email is required");
                    chk++;
                }
                if(TextUtils.isEmpty(ALUMNI_JOB_STATUS))
                {
                    alumni_job_status_u.setError("Job Status is required");
                    chk++;
                }
                if(TextUtils.isEmpty(ALUMNI_COMPANY_NAME))
                {
                    chk++;
                    alumni_company_name_u.setError("Company name is required");
                }
                if(TextUtils.isEmpty(ALUMNI_COMPANY_ADDRESS))
                {
                    chk++;
                    alumni_company_address_u.setError("Name is required");
                }



                if (alumni_contact_no_u.length() == 10)
                {
                    chk++;
                    alumni_contact_no_u.setError("10 number are required!!");
                }

                if(chk==0){
                    UpdateData();
                }

            }
        });

        clear_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alumni_name_u.setText("");
                alumni_passing_year_u.setText("");
                alumni_contact_no_u.setText("");
                alumni_email_u.setText("");
                alumni_job_status_u.setText("");
                alumni_company_name_u.setText("");
                alumni_designation_u.setText("");
                alumni_company_address_u.setText("");
                alumni_ug_project_u.setText("");
                alumni_social_profile_u.setText("");
            }
        });
    }

    private void UpdateData() {


            Alumni_Data da=new Alumni_Data(ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL
                    ,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS
                    ,ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE);

            f.collection("ALUMNIS").document(ad.getId())
                    .set(da).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(UPDATE.this, "Updated...", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(UPDATE.this,Add_AlumniActivity.class);
                    startActivity(i);
                    finish();
                }
            });

    }

    public void btn_delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure about this?");
        builder.setMessage("Deletion is permanent...");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProduct();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();

    }
}