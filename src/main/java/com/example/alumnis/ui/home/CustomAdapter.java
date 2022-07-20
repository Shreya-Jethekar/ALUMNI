package com.example.alumnis.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alumnis.Alumni_Data;
import com.example.alumnis.R;
import com.example.alumnis.Result;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Alumni_Data> {
    Context context;
    private List<Alumni_Data> countryModelsList;
    private List<Alumni_Data> countryModelsListFiltered;


    public CustomAdapter(Context context, List<Alumni_Data> countryModelsList) {
        super(context, R.layout.item2,countryModelsList);

        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,null,true);
        TextView tvCountryName = view.findViewById(R.id.textView_item2);
        TextView tvNAME = view.findViewById(R.id.textView_name_item);
        TextView tvEMAIL = view.findViewById(R.id.textView_email_item);
//       LinearLayout ll = view.findViewById(R.id.ll);
//        ImageView imageView = view.findViewById(R.id.imageFlag);

//        tvCountryName.setText(countryModelsList.get(position));
        tvCountryName.setText(countryModelsListFiltered.get(position).ALUMNI_DESIGNATION);
        tvNAME.setText(countryModelsListFiltered.get(position).ALUMNI_NAME);
        tvEMAIL.setText(countryModelsListFiltered.get(position).ALUMNI_EMAIL);
//        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(imageView);



//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(getContext(), Result.class).putExtra("position",position));
////                  Intent i=new Intent(context,Result.class).putExtra("position"+position);
//            }
//        });
        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
//        return countryModelsList.size();
    }

    @Nullable
    @Override
    public Alumni_Data getItem(int position) {
        return countryModelsListFiltered.get(position);
//        return countryModelsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                }else{
                    List<Alumni_Data> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Alumni_Data itemsModel:countryModelsList){
                        if(itemsModel.ALUMNI_DESIGNATION.contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                countryModelsListFiltered = (List<Alumni_Data>) results.values;
                HomeFragment.arrayl = (List<Alumni_Data>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }



}
