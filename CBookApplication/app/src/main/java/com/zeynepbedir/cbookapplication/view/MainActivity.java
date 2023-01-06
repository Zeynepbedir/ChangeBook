package com.zeynepbedir.cbookapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zeynepbedir.cbookapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth=FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null){
            Intent intent = new Intent(MainActivity.this, AnaSayfaActivity.class);
            startActivity(intent);
            finish();
        }

    }
    public void girisClick(View view){
        String email=binding.edtEmail.getText().toString();
        String sifre=binding.edtSifre.getText().toString();

        if (email.equals("") || sifre.equals("") ){
            Toast.makeText(MainActivity.this,"Bütün alanları doldurunuz...",Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(email, sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent =new Intent(MainActivity.this,AnaSayfaActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    public void kaydolClick(View view){

        String email=binding.edtEmail.getText().toString();
        String sifre=binding.edtSifre.getText().toString();


        if (email.equals("") || sifre.equals("") ){
            Toast.makeText(MainActivity.this,"Bütün alanları doldurunuz...",Toast.LENGTH_LONG).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email, sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MainActivity.this, "Kullanıcı oluşturuldu.", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this,AnaSayfaActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

      }
    }
