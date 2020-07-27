package com.example.eatactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    TextView textSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        textSlogan=(TextView)findViewById(R.id.textSlogan);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn=new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp=new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });
    }
}
