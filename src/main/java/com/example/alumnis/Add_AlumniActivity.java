package com.example.alumnis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Add_AlumniActivity extends AppCompatActivity {
    public FirebaseFirestore fstore;
    RecyclerView recyclerView;
    public static MyCustomAdapter adapter;
    public ArrayList<Alumni_Data> arraylist_alumni = new ArrayList<>();

    TextView addtitle;
    private FirebaseFirestore f;
    private Alumni_Data ad;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumni);

        recyclerView = findViewById(R.id.recycle_add_alumni);
        adapter=new MyCustomAdapter(this,arraylist_alumni);
        fstore= FirebaseFirestore.getInstance();


        DisplayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(Add_AlumniActivity.this));
        recyclerView.setAdapter(adapter);

    }

//    void UPDATE(){
//        ad= (Alumni_Data) getIntent().getSerializableExtra("product");
//        f=FirebaseFirestore.getInstance();
//
//        Dialog dialog = new Dialog(Add_AlumniActivity.this);
//        dialog.setContentView(R.layout.item_dialog);
//        EditText alumni_name_u, alumni_passing_year_u, alumni_contact_no_u, alumni_email_u, alumni_job_status_u, alumni_company_name_u, alumni_designation_u, alumni_company_address_u,
//                alumni_ug_project_u, alumni_social_profile_u;
//        Button update, clear_u;
//
//
//        dialog.show();
//
//
//        alumni_name_u = dialog.findViewById(R.id.alumni_name);
//        alumni_passing_year_u = findViewById(R.id.alumni_passing_year);
//        alumni_contact_no_u = findViewById(R.id.alumni_contact_no);
//        alumni_email_u = findViewById(R.id.alumni_email_id);
//        alumni_job_status_u = findViewById(R.id.alumni_job_status);
//        alumni_company_name_u = findViewById(R.id.alumni_company_name);
//        alumni_company_address_u = findViewById(R.id.alumni_company_address);
//        alumni_designation_u = findViewById(R.id.alumni_designation);
//        alumni_ug_project_u = findViewById(R.id.alumni_project_details);
//        alumni_social_profile_u = findViewById(R.id.alumni_social_profile);
//
//        update = findViewById(R.id.button_save);
//        clear_u = findViewById(R.id.button_clear);
//
//        //Setting value....
//
//        alumni_name_u.setText(ad.ALUMNI_NAME);
//        alumni_passing_year_u.setText(ad.ALUMNI_PASSING_YEAR);
//        alumni_contact_no_u.setText(ad.ALUMNI_CONTACT_NO);
//        alumni_email_u.setText(ad.ALUMNI_EMAIL);
//        alumni_job_status_u.setText(ad.ALUMNI_JOB_STATUS);
//        alumni_company_name_u.setText(ad.ALUMNI_COMPANY_NAME);
//        alumni_designation_u.setText(ad.ALUMNI_DESIGNATION);
//        alumni_company_address_u.setText(ad.ALUMNI_COMPANY_ADDRESS);
//        alumni_ug_project_u.setText(ad.ALUMNI_UG_PROJECT);
//        alumni_social_profile_u.setText(ad.ALUMNI_SOCIAL_PROFILE);
//
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String  ALUMNI_NAME=alumni_name_u.getText().toString().trim();
//                String ALUMNI_PASSING_YEAR=alumni_passing_year_u.getText().toString().trim();
//                String ALUMNI_CONTACT_NO=alumni_contact_no_u.getText().toString().trim();
//                String ALUMNI_EMAIL=alumni_email_u.getText().toString().trim();
//                String ALUMNI_JOB_STATUS=alumni_job_status_u.getText().toString().trim();
//                String ALUMNI_COMPANY_NAME=alumni_company_name_u.getText().toString().trim();
//                String ALUMNI_DESIGNATION=alumni_designation_u.getText().toString().trim();
//                String ALUMNI_COMPANY_ADDRESS=alumni_company_address_u.getText().toString().trim();
//                String ALUMNI_ALUMNI_UG_PROJECT= alumni_ug_project_u.getText().toString().trim();
//                String ALUMNI_SOCIAL_PROFILE =alumni_social_profile_u.getText().toString().trim();
//
//                if(TextUtils.isEmpty(ALUMNI_NAME) || TextUtils.isEmpty(ALUMNI_PASSING_YEAR) || TextUtils.isEmpty(ALUMNI_CONTACT_NO)|| TextUtils.isEmpty(ALUMNI_EMAIL)||
//                        TextUtils.isEmpty(ALUMNI_JOB_STATUS)|| TextUtils.isEmpty(ALUMNI_COMPANY_NAME)|| TextUtils.isEmpty(ALUMNI_DESIGNATION)
//                        || TextUtils.isEmpty(ALUMNI_COMPANY_ADDRESS)|| TextUtils.isEmpty(ALUMNI_ALUMNI_UG_PROJECT)|| TextUtils.isEmpty(ALUMNI_SOCIAL_PROFILE))
//                {
//                    Toast.makeText(UPDATE.this, "All Fields are required", Toast.LENGTH_SHORT).show();
//                }
//
//                if (alumni_contact_no_u.length() ==9)
//                {
//                    alumni_contact_no_u.setError("10 number are required!!");
//                }
//
//                else{
//                    Alumni_Data da=new Alumni_Data(ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS,ALUMNI_ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE);
//
//                    f.collection("ALUMNIS").document(ad.getId())
//                            .set(da).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(UPDATE.this, "Updated...", Toast.LENGTH_SHORT).show();
//                            Intent i=new Intent(UPDATE.this,ADD.class);
//                            startActivity(i);
//                            finish();
//                        }
//                    });
//                }
//            }
//        });
//
//        clear_u.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alumni_name_u.setText("");
//                alumni_passing_year_u.setText("");
//                alumni_contact_no_u.setText("");
//                alumni_email_u.setText("");
//                alumni_job_status_u.setText("");
//                alumni_company_name_u.setText("");
//                alumni_designation_u.setText("");
//                alumni_company_address_u.setText("");
//                alumni_ug_project_u.setText("");
//                alumni_social_profile_u.setText("");
//            }
//        });
//    }

//    public void btn_delete(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Are you sure about this?");
//        builder.setMessage("Deletion is permanent...");
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                deleteProduct();
//            }
//        });
//
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        AlertDialog ad = builder.create();
//        ad.show();
//
//    }


    public void DisplayData() {
        //Retrive hora...


        fstore.collection("ALUMNIS").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

//                        progressBar.setVisibility(View.GONE);

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {

                                Alumni_Data p = d.toObject(Alumni_Data.class);
                                p.setId(d.getId());
                                arraylist_alumni.add(p);

                            }

                            adapter.notifyDataSetChanged();

                        }


                    }
                });
    }




    public void fab_btn(View view) {
        AddFragment bottomSheetFragment=new AddFragment();
        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());

    }
}