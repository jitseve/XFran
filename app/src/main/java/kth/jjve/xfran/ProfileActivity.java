package kth.jjve.xfran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import kth.jjve.xfran.viewmodels.UserProfileVM;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /*_________ VIEW _________*/
    private TextView mName, mEmail, mWeight, mLength;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*----- HOOKS -----*/
        drawerLayout = findViewById(R.id.drawer_layout_profile);
        navigationView = findViewById(R.id.nav_view_profile);
        toolbar = findViewById(R.id.profile_toolbar);

        mName = findViewById(R.id.profile_username);
        mEmail = findViewById(R.id.profile_useremail);
        mWeight = findViewById(R.id.profile_weight);
        mLength = findViewById(R.id.profile_height);
        ImageButton edit = findViewById(R.id.profile_Edit);

        /*-----  VM  -----*/
        UserProfileVM mUserProfileVM = ViewModelProviders.of(this).get(UserProfileVM.class);
        mUserProfileVM.init();
        mUserProfileVM.getUserProfile().observe(this, uP -> {
            if (!uP.checkEmpty()){
                mName.setText(uP.getFullName());
                mEmail.setText(uP.getEmail());
                mWeight.setText(Double.toString(uP.getWeight()));
                mLength.setText(Integer.toString(uP.getLength()));
            }
        });

        /*--- OBSERVER ---*/
        edit.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class)));

        /*----- INIT -----*/
        setSupportActionBar(toolbar);
        initNavMenu();

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_diary);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(ProfileActivity.this, HomeScreenActivity.class));
            finish();
        } else if (id == R.id.nav_login) {
            startActivity(new Intent(ProfileActivity.this, RegisterActivity.class));
        } else if (id == R.id.nav_logout) {
            //Todo: make sure that logout works everywhere
            Toast.makeText(ProfileActivity.this, "Log out only in homescreen", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileActivity.this, HomeScreenActivity.class));
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNavMenu() {
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }
}