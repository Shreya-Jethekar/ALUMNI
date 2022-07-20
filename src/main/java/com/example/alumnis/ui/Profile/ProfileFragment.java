package com.example.alumnis.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.alumnis.R;
import com.example.alumnis.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseFirestore fstores;
    String UserId;
    FirebaseUser currentUser ;
    Button btn_save;
    TextView profilechange;
    TextView h_name, h_email, h_contactNo, h_password, h_type;
    //        private FragmentProfileBinding binding;
    ImageView h_image;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fstores=FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
//        Toast.makeText(getContext(), "UserID:- "+currentUser.getUid(), Toast.LENGTH_SHORT).show();
        UserId=currentUser.getUid();
        h_name = root.findViewById(R.id.nameEditText);
        h_contactNo = root.findViewById(R.id.contactEditText);
        h_email = root.findViewById(R.id.emailEditText);
        h_type = root.findViewById(R.id.type_Spinner);
        h_image = root.findViewById(R.id.profile_image);

// ...

//
//        fauth = FirebaseAuth.getInstance();
//        fstore = FirebaseFirestore.getInstance();
//        Userid = fauth.getCurrentUser().getUid();
        btn_save = root.findViewById(R.id.change_image_btn);
        profilechange = root.findViewById(R.id.textHint);

//        call();



        call();

        btn_save = root.findViewById(R.id.change_image_btn);
        profilechange = root.findViewById(R.id.textHint);


        final TextView textView = binding.textGallery;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    private void call() {

        DocumentReference doc=fstores.collection("USERS").document(UserId);

        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
//                    Toast.makeText(getContext(), "Name="+documentSnapshot.getString("NAME"), Toast.LENGTH_SHORT).show();
                    h_email.setText(documentSnapshot.getString("EMAIL"));
//                    h_password.setText(documentSnapshot.getString("PASSWORD"));
                    h_contactNo.setText(documentSnapshot.getString("CONTACT_NO"));
                    h_type.setText(documentSnapshot.getString("USER_TYPE"));

                    h_name.setText(documentSnapshot.getString("NAME"));

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Fail to Load", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}