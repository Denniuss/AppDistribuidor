package com.newmantech.appdistribuidor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*import pe.edu.upc.practicat61_javs.database.SentenciaSQL;
import pe.edu.upc.practicat61_javs.database.SentenciaSQLImpl;
import pe.edu.upc.practicat61_javs.model.Usuario;*/

public class LoginActivity extends AppCompatActivity {
    private EditText txtCorreo;
    private EditText txtContrasenha;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo = (EditText) findViewById(R.id.txtCorreoElectronioo);
        txtContrasenha = (EditText) findViewById(R.id.txtContrasenha);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarUsuario();
            }
        });

    }

    private void ValidarUsuario() {
        /*if (txtCorreo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese su correo!", Toast.LENGTH_SHORT).show();
            txtCorreo.requestFocus();
            return;
        }
        if (txtContrasenha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese su contraseña!", Toast.LENGTH_SHORT).show();
            txtContrasenha.requestFocus();
            return;
        }

        //Implementando acceso de usuario
        String usuario = txtCorreo.getText().toString();
        String password = txtContrasenha.getText().toString();

        SentenciaSQL sentenciaSQL = new SentenciaSQLImpl(LoginActivity.this);
        Usuario result = sentenciaSQL.validarUsaurio(usuario, password);

        if (result != null) {
            //login
            //Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            intent.putExtra("item", result);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("credenciales invalidas")
                    .setNegativeButton("Retry", null)
                    .create().show();
        }*/

        System.out.print("LLego a LoginActivity?");

        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        //intent.putExtra("item", result);
        System.out.print("intent  " + intent );
        startActivity(intent);

    }
}
