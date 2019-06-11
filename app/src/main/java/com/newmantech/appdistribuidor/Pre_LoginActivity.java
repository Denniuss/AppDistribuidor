package com.newmantech.appdistribuidor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pre_LoginActivity extends AppCompatActivity {

    private Button btnIniciarSesion;
    private Button btnRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre__login);

        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSession);
        btnRegistrate= (Button) findViewById(R.id.btnRegistrarte);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pre_LoginActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Pre_LoginActivity.this,NewActivity.class);
                //startActivity(intent);
            }
        });

    }

}
