package com.example.socialapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CreateProfile extends AppCompatActivity {
TextInputEditText name, email, phonenumber,city;
Button saveDetails;
ImageView userProfile;
Uri imageuri;
private static final int PICK_IMAGE=1;
    UploadTask uploadTask;
StorageReference storageReference;
FirebaseFirestore firebaseFirestore;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
DocumentReference documentReference;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        UserModel userModel=new UserModel();
        name=findViewById(R.id.User_name);
        email=findViewById(R.id.User_email);
        phonenumber=findViewById(R.id.User_phoneNumber);
        city=findViewById(R.id.User_city);
        userProfile=findViewById(R.id.userProfile_image);
        saveDetails=findViewById(R.id.User_Save_detail_Btn);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String currentUser=firebaseUser.getUid();

       documentReference=firebaseFirestore
               .collection("user").document(currentUser+name);

       storageReference=FirebaseStorage.getInstance().getReference("ProfileImages");
       databaseReference=firebaseDatabase.getReference("user");

       saveDetails.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             uploadData();
           }
       });

       userProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(intent,PICK_IMAGE);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
  try {
      if(requestCode==PICK_IMAGE && requestCode==RESULT_OK && data!=null && data.getData()==null){
          imageuri=data.getData();
          Picasso.get().load(imageuri).into(userProfile);
      }

  }
  catch (Exception e){
      Toast.makeText(this, "Error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
  }


    }

    private void uploadData() {
    }
}