package com.example.alumnis.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.alumnis.Alumni_Data;
import com.example.alumnis.R;
import com.example.alumnis.Result;
import com.example.alumnis.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseFirestore fstore;
    CustomAdapter myCustomAdapter;
    public static List<Alumni_Data> arrayl = new ArrayList<Alumni_Data>();
    public static List<String> arr = new ArrayList<String>();


    EditText ed_search;
    ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        fstore= FirebaseFirestore.getInstance();

        listView=root.findViewById(R.id.Listviewid);

        DISS();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Result.class).putExtra("position",position));

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(getActivity(), Result.class).putExtra("position",position));

                return false;
            }
        });

        ed_search=root.findViewById(R.id.ed_search);
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                myCustomAdapter.getFilter().filter(charSequence);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



//        final TextView textView = binding.textHome;


        return root;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            getActivity().finish();
        return super.onOptionsItemSelected(item);
    }

    private void DISS() {

        fstore.collection("ALUMNIS").orderBy("ALUMNI_NAME", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            Toast.makeText(getContext(), "error!!"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        for(DocumentChange da:value.getDocumentChanges()){
                            if(da.getType()==DocumentChange.Type.ADDED){

                                Alumni_Data l=da.getDocument().toObject(Alumni_Data.class);
//                                    String s = l.ALUMNI_DESIGNATION;
//                                    Toast.makeText(getContext(), l.ALUMNI_DESIGNATION, Toast.LENGTH_SHORT).show();
//                                    arr.add(l.ALUMNI_DESIGNATION);
//                                    if(arr.contains(s)){
                                arrayl.add(l);
//                                    }
//                                    if(!arrayl.contains(l))
//                                    {
//                                        arrayl.add(l);
//                                    }

                            }

                        }

                        myCustomAdapter = new CustomAdapter(getContext(),arrayl);
                        listView.setAdapter(myCustomAdapter);
                    }
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        arrayl.clear();
    }
}