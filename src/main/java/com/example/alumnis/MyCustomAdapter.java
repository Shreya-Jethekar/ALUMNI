package com.example.alumnis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    FirebaseFirestore fstore;
    Alumni_Data da;
    Context context;
    ArrayList<Alumni_Data> arrayList_alumni;

    public MyCustomAdapter(Context context, ArrayList<Alumni_Data> arrayList_alumni) {
        this.context = context;
        this.arrayList_alumni = arrayList_alumni;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_dialog,parent,false);
        MyViewHolder myholder=new MyViewHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Alumni_Data daa = arrayList_alumni.get(position);
        fstore= FirebaseFirestore.getInstance();

        int currentposition=position;

        holder.alumni_name_i.setText(daa.ALUMNI_NAME);
        holder.alumni_passing_year_i.setText(daa.ALUMNI_PASSING_YEAR);
        holder.alumni_contact_no_i.setText(daa.ALUMNI_CONTACT_NO);
        holder.alumni_email_i.setText(daa.ALUMNI_EMAIL);
        holder.alumni_job_status_i.setText(daa.ALUMNI_JOB_STATUS);
        holder.alumni_company_name_i.setText(daa.ALUMNI_COMPANY_NAME);
        holder.alumni_company_address_i.setText(daa.ALUMNI_COMPANY_ADDRESS);
        holder.alumni_designation_i.setText(daa.ALUMNI_DESIGNATION);
        holder.alumni_social_profile_i.setText(daa.ALUMNI_SOCIAL_PROFILE);
        holder.alumni_ug_project_i.setText(daa.ALUMNI_UG_PROJECT);


        holder.linearLayout_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Alumni_Data product = arrayList_alumni.get(currentposition);
                Intent intent = new Intent(context, UPDATE.class);
                intent.putExtra("product", product);

                context.startActivity(intent);

//               Update_AlumniFragment bottomSheetfragment=new Update_AlumniFragment();
//                bottomSheetfragment.show(bottomSheetfragment.getChildFragmentManager(), bottomSheetfragment.getTag());
//



            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList_alumni.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView alumni_add_i,alumni_name_i,alumni_passing_year_i,alumni_contact_no_i,alumni_email_i,alumni_job_status_i,alumni_company_name_i
                ,alumni_designation_i,alumni_company_address_i,alumni_ug_project_i,alumni_social_profile_i;
        Button add_i,clear_i;
        LinearLayout linearLayout_i;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout_i=itemView.findViewById(R.id.linear_layout_add_dialog_i);
            alumni_name_i = itemView.findViewById(R.id.alumni_name_i);
            alumni_add_i = itemView.findViewById(R.id.alumni_add_i);
            alumni_passing_year_i = itemView.findViewById(R.id.alumni_passing_year_i);
            alumni_contact_no_i=itemView.findViewById(R.id.alumni_contact_no_i);
            alumni_email_i = itemView.findViewById(R.id.alumni_email_id_i);
            alumni_job_status_i = itemView.findViewById(R.id.alumni_job_status_i);
            alumni_company_name_i= itemView.findViewById(R.id.alumni_company_name_i);
            alumni_company_address_i = itemView.findViewById(R.id.alumni_company_address_i);
            alumni_designation_i = itemView.findViewById(R.id.alumni_designation_i);
            alumni_ug_project_i =itemView.findViewById(R.id.alumni_project_details_i);
            alumni_social_profile_i = itemView.findViewById(R.id.alumni_social_profile_i);
//            add_i = itemView.findViewById(R.id.button_save_i);
//            clear_i = itemView.findViewById(R.id.button_clear_i);



        }
    }


}
