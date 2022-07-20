package com.example.alumnis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumnis.databinding.FragmentAddBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends BottomSheetDialogFragment {
    public FirebaseFirestore fstore;
    RecyclerView recyclerView;
    MyCustomAdapter adapter;
    int chk=0;
    public String ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME
            ,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS,ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE;
    public ArrayList<Alumni_Data> arraylist_alumni = new ArrayList<>();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private FragmentAddBinding binding;
    EditText alumni_name,alumni_passing_year,alumni_contact_no,alumni_email,alumni_job_status,alumni_company_name,alumni_designation
            ,alumni_company_address,alumni_ug_project,alumni_social_profile;
    Button add,clear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add, container, false);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycle_add_alumni);
//        adapter=new MyCustomAdapter(this,arraylist_alumni);
        fstore= FirebaseFirestore.getInstance();


//        DisplayData();
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);



        alumni_name = root.findViewById(R.id.alumni_name);
        alumni_passing_year = root.findViewById(R.id.alumni_passing_year);
        alumni_contact_no= root.findViewById(R.id.alumni_contact_no);
        alumni_email = root.findViewById(R.id.alumni_email_id);
        alumni_job_status = root.findViewById(R.id.alumni_job_status);
        alumni_company_name= root.findViewById(R.id.alumni_company_name);
        alumni_company_address = root.findViewById(R.id.alumni_company_address);
        alumni_designation = root.findViewById(R.id.alumni_designation);
        alumni_ug_project = root.findViewById(R.id.alumni_project_details);
        alumni_social_profile = root.findViewById(R.id.alumni_social_profile);
        add = root.findViewById(R.id.button_save);
        clear = root.findViewById(R.id.button_clear);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    String  ALUMNI_ID=alumni_id.getText().toString().trim();
                  ALUMNI_NAME=alumni_name.getText().toString().trim();
                 ALUMNI_PASSING_YEAR=alumni_passing_year.getText().toString().trim();
                 ALUMNI_CONTACT_NO=alumni_contact_no.getText().toString().trim();
                 ALUMNI_EMAIL=alumni_email.getText().toString().trim();
                 ALUMNI_JOB_STATUS=alumni_job_status.getText().toString().trim();
                 ALUMNI_COMPANY_NAME=alumni_company_name.getText().toString().trim();
                 ALUMNI_DESIGNATION=alumni_designation.getText().toString().trim();
                 ALUMNI_COMPANY_ADDRESS=alumni_company_address.getText().toString().trim();
                 ALUMNI_UG_PROJECT= alumni_ug_project.getText().toString().trim();
                 ALUMNI_SOCIAL_PROFILE =alumni_social_profile.getText().toString().trim();

                validation();
//                else{

                if(chk == 0){
                    //Data Save...
                    DocumentReference dref = fstore.collection("ALUMNIS").document();

                    Alumni_Data da=new Alumni_Data(ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS,ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE);
                    dref.set(da).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), "Data Entered", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
                            arraylist_alumni.add(new Alumni_Data(ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_CONTACT_NO,ALUMNI_EMAIL
                                    ,ALUMNI_JOB_STATUS,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION,ALUMNI_COMPANY_ADDRESS,
                                    ALUMNI_UG_PROJECT,ALUMNI_SOCIAL_PROFILE));

//                                ADDMethod(ALUMNI_NAME,ALUMNI_PASSING_YEAR,ALUMNI_COMPANY_NAME,ALUMNI_DESIGNATION);
                            Add_AlumniActivity.adapter.notifyItemInserted(arraylist_alumni.size());

                            getActivity().finish();
                            Intent i=new Intent(getActivity(),Add_AlumniActivity.class);
                            startActivity(i);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Data not Enterd", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
//                }

            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alumni_name.setText("");
                alumni_passing_year.setText("");
                alumni_contact_no.setText("");
                alumni_email.setText("");
                alumni_job_status.setText("");
                alumni_company_name.setText("");
                alumni_designation.setText("");
                alumni_company_address.setText("");
                alumni_ug_project.setText("");
                alumni_social_profile.setText("");
            }
        });

        return root;

    }

    private void validation() {

        if(TextUtils.isEmpty(ALUMNI_NAME))
        {
            alumni_name.setError("Name is required");
            chk++;
        }
        if(TextUtils.isEmpty(ALUMNI_PASSING_YEAR))
        {
            alumni_passing_year.setError("passing year is required");
            chk++;
        }
        if(TextUtils.isEmpty(ALUMNI_CONTACT_NO))
        {
            alumni_contact_no.setError("contact no is required");
            chk++;
        }
        if(TextUtils.isEmpty(ALUMNI_EMAIL))
        {
            alumni_email.setError("Email is required");
            chk++;
        }
        if(TextUtils.isEmpty(ALUMNI_JOB_STATUS))
        {
            alumni_job_status.setError("Job Status is required");
            chk++;
        }
        if(TextUtils.isEmpty(ALUMNI_COMPANY_NAME))
        {
            chk++;
            alumni_company_name.setError("Company name is required");
        }
        if(TextUtils.isEmpty(ALUMNI_COMPANY_ADDRESS))
        {
            chk++;
            alumni_company_address.setError("Name is required");
        }


        if (alumni_contact_no.length() ==9)
        {
            chk++;
            alumni_contact_no.setError("10 number are required!!");
        }
    }


}
