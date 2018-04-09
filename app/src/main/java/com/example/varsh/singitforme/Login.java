package com.example.varsh.singitforme;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button logged;
    TextView et;
    TextView pt;
    EditText lemail;
    EditText lpass;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Button showuers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lemail=(EditText)findViewById(R.id.emaileditlogin);
        lpass=(EditText)findViewById(R.id.passeditlogin);
        et=(TextView)findViewById(R.id.emailtxt);
        pt=(TextView)findViewById(R.id.passtxt);
        firebaseAuth= FirebaseAuth.getInstance();
        lemail.setSelected(false);
        lpass.setSelected(false);
        lemail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    lemail.setHint("");
                    et.setText("Emailid");

                }

            }
        });

        lpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    lpass.setHint("");
                    pt.setText("Password");

                }

            }
        });
        logged=(Button)findViewById(R.id.btnlogin);

        logged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please wait...", "on process..", true);

                (firebaseAuth.signInWithEmailAndPassword(lemail.getText().toString(), lpass.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged on", Toast.LENGTH_LONG).show();
                            Intent i= new Intent(Login.this,Home.class);
                            startActivity(i);
                        }
                        else
                        {
                            Log.e("error..",task.getException().toString());
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }
                });
                showuers=(Button)findViewById(R.id.showbtn);
                showuers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Login.this, Main2Activity.class);
                        startActivity(i);
                    }
                });
            }
        });

    }
}
