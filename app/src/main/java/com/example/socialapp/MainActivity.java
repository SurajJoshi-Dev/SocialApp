package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import fragment.fragment1;
import fragment.fragment2;
import fragment.fragment3;
import fragment.fragment4;

public class MainActivity extends AppCompatActivity {
FirebaseAuth firebaseAuth;


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(" Really Exit")
                .setCancelable(false)
                .setMessage("Do you really want to Exit the App?")
                .setPositiveButton("Yes Exit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })

                .setNegativeButton("No Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();


        BottomNavigationView  bottomNavigationView=findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new fragment1()).commit();


    }
private BottomNavigationView.OnNavigationItemSelectedListener listener=new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selected=null;

        switch(item.getItemId()){

            case R.id.home_bottom_nav:
                Log.i("home","ok");

                selected =new fragment1();
               break;

                case R.id.profile_bottom_nav:
              Log.i("profile","ok");
                selected =new CreateProfile();
                break;

              case R.id.help_bottom_nav:
                  Log.i("help","ok");

                selected =new fragment3();
                break;

             case R.id.friends_bottom_nav:

                selected =new fragment4();
                break;


        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selected).commit();
return true;
    }
};
}