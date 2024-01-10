package com.example.ipscollege;

import static com.example.ipscollege.R.id.frame_layout;
import static com.example.ipscollege.R.id.navigation_LogOut;
import static com.example.ipscollege.R.id.navigation_developer;
import static com.example.ipscollege.R.id.navigation_ebook;
import static com.example.ipscollege.R.id.navigation_rateus;
import static com.example.ipscollege.R.id.navigation_share;
import static com.example.ipscollege.R.id.navigation_theme;
import static com.example.ipscollege.R.id.navigation_video;
import static com.example.ipscollege.R.id.navigation_view;
import static com.example.ipscollege.R.id.navigation_website;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.ipscollege.authentication.LoginActivity;
import com.example.ipscollege.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;
    private FirebaseAuth auth;


    // implement this line in any clickL
//    startActivity(new Intent(MainActivity.this, MainNotification.class));

    private final String CHECKEDITEM = "checked_item";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().subscribeToTopic("Notification");

        auth = FirebaseAuth.getInstance();
        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        switch (getCheckedItem()) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, frame_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


//      this is for header back and slide drawer
try {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
    } else {
        // Handle the case where getSupportActionBar() returns null.
        // You may want to log a message or take appropriate action
        Toast.makeText(this, "this would occurs error!", Toast.LENGTH_SHORT).show();
    }

}catch (Exception e){
    Toast.makeText(this, "error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
}

        navigationView.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    private void openWebsite() {
        String url = "https://www.ipsedu.in/";

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Handle the exception or provide fallback behavior
            Log.e("Error", "No web browser found", e);
            // You can show an error message to the user
        }
    }


    private void shareApp() {
        String apkDownloadUrl = "https://drive.google.com/file/d/12PFJ-TXE4xUskiVH18humC28YeuBp6sA/view?usp=sharing";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome app: " + apkDownloadUrl);

        try {
            startActivity(Intent.createChooser(shareIntent, "Share App"));
        } catch (ActivityNotFoundException e) {
            // Handle the exception or provide fallback behavior
            Log.e("Error", "No app to handle share intent", e);
            // You can show an error message to the user
        }
    }


    public void openGmail(View view) {
        Log.d("debug", "openGmail method called");
        String recipientEmail = "Shlok0531@gmail.com";
        composeEmail(recipientEmail);
    }

    private void composeEmail(String recipient) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + recipient));

        try {
            startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (ActivityNotFoundException e) {
            // Handle the exception or provide fallback behavior
            Log.e("Error", "No email app found", e);
            // You can show an error message to the user
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        if (item.getItemId() == R.id.logout){
            auth.signOut();
            openLogin();
        }
        return true;
   }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() == null) {
            openLogin();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == navigation_developer) {
            Toast.makeText(this, "I'm open to connect with you.", Toast.LENGTH_LONG).show();
            // Get the View associated with the MenuItem
            View menuItemView = findViewById(item.getItemId());
            openGmail(menuItemView);
            return true;

        } else if (itemId == navigation_video) {
            Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show();

        } else if (itemId == navigation_rateus) {
            Toast.makeText(this, "Thanks for rating us 5-star⭐⭐⭐⭐⭐", Toast.LENGTH_SHORT).show();

        } else if (itemId == navigation_ebook) {
                startActivity(new Intent(this, EbookActivity.class));

        } else if (itemId == navigation_website) {
            openWebsite();

        } else if (itemId == navigation_share) {
            Toast.makeText(this, "Sharing...", Toast.LENGTH_SHORT).show();
            shareApp();

        } else if (itemId == navigation_theme) {
            showDialog();

        } else if (itemId == navigation_LogOut) {
            auth.signOut();
            openLogin();
            Toast.makeText(this, "Logout...", Toast.LENGTH_SHORT).show();

        }else {
            throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                selected = themes[i];
                checkedItem = i;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(selected == null){
                    selected = themes[i];
                    checkedItem = i;
                }
                switch (selected) {
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM,0);
    }

    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}