package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
TextInputEditText username,emailadd,password,confirmPassword;
Button registerBtn;
TextView regLoginBtn;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Initialization();
    }

    private void Initialization() {
        username=findViewById(R.id.Reg_name_txt);
       emailadd =findViewById(R.id.Register_Email_txt);
        password=findViewById(R.id.Register_Password_txt);
        confirmPassword=findViewById(R.id.Register_ConfrmPassword_txt);
        registerBtn=findViewById(R.id.Signup_btn);
       regLoginBtn =findViewById(R.id.Reg_loginBtn);

      progressDialog=new ProgressDialog(this);
       progressDialog.setTitle("Creating New Account");
       progressDialog.setMessage("Please Wait");
       progressDialog.setCancelable(false);

       firebaseAuth=FirebaseAuth.getInstance();
       firebaseUser=firebaseAuth.getCurrentUser();

       registerBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String pass=password.getText().toString();
               String Cpassword1=confirmPassword.getText().toString();
               String email=emailadd.getText().toString();
               if (emailadd.length() == 0) {
                   emailadd.setError("Fill Enter Email Address");

                   //  Toast.makeText(this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
               } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                   emailadd.setError("Please Enter Valid Email");

               }
               else if(username.length()==0){
                   username.setError("Fill Username");
               }
               else if (!username.getText().toString().matches("^[\\p{L} .'-]+$"))
               {
                   username.setError("Please enter a valid character");
               } else if (pass.length() < 6) {
                   password.setError("Please Enter password minimum in 6 char");
               } else if (!Cpassword1.equals(pass)) {
                   confirmPassword.setError("Both password are not Matched");
               }
               else {
                   progressDialog.show();
                   firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                          SendUserToMainActivity();
                    }
                    else {

                        Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                       }
                   });

               }
           }
       });


       regLoginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(RegisterActivity.this,Login.class);
               startActivity(intent);
           }
       });


    } //initiazation body

    private void SendUserToMainActivity() {
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(firebaseUser!=null){
//        SendUserToMainActivity();
//        }
  //  }
}