package com.example.alumnis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnis.databinding.ActivityNavBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class NavActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore fstores;
    String UserId;
    FirebaseUser currentUser ;
    String Usertype;
    MenuItem m;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        fstores= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        UserId=currentUser.getUid();
        call();
//        setSupportActionBar(binding.appBarNav.toolbar);
        setSupportActionBar(binding.appBarNav.toolbar);
//        binding.appBarNav.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        m=menu.findItem(R.id.action_add_alumni);
        return true;
    }
    public void Add_Alumni(MenuItem item) {
//        Toast.makeText(NavActivity.this, "Add Alumni", Toast.LENGTH_SHORT).show();
        Add_clicked();

    }

    private void Add_clicked() {
//        AddFragment bottomSheetFragment=new AddFragment();
//        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
        Intent i=new Intent(NavActivity.this, Add_AlumniActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logclick(MenuItem item) {
        signOut();
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(NavActivity.this, LoginActivity.class));
//        onDestroy();
       finish();
    }


    private void call() {

        DocumentReference doc=fstores.collection("USERS").document(UserId);

        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    NavigationView mNavigationView = findViewById(R.id.nav_view);
                    View headerView = mNavigationView.getHeaderView(0);

                    TextView userName = headerView.findViewById(R.id.User_Name_nav_header);
                    TextView userEmail = headerView.findViewById(R.id.User_Email_nav_header);
//                    TextView usertype = headerView.findViewById(R.id.User_TYPE_Fieldr);
                    Usertype=documentSnapshot.getString("USER_TYPE");

                    userName.setText(documentSnapshot.getString("NAME"));
                    userEmail.setText(documentSnapshot.getString("EMAIL"));
//                    usertype.setText(Usertype);


                    if(Usertype.equalsIgnoreCase("Admin")){
                        m.setVisible(true);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NavActivity.this, "Fail to Load", Toast.LENGTH_SHORT).show();
            }
        });
    }
}