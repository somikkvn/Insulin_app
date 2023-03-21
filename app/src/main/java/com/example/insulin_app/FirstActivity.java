package com.example.insulin_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.example.insulin_app.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button button7 = findViewById(R.id.button7);
        db = FirebaseDatabase.getInstance();
        users = db.getReference();
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegister();
            }
        });
    }

    public void startLoginActivity(View v){
//        Button button6 = findViewById(R.id.button6);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    private void showRegister(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зареєструватися");
        dialog.setMessage("Введіть дані для реєстрації");
        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.activity_register, null);
        dialog.setView(register_window);
        dialog.show();
    }
//    private void showRegister(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
//        builder.setMessage("Реєстрація");
//        builder.setCancelable(true);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

}