package com.example.evaluacion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class recuperarClave extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout user, clave;
    Button rec, canc;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_clave);

        user = findViewById(R.id.tilRecUser);
        clave = findViewById(R.id.tilRecClave);

        rec = findViewById(R.id.btnRecClave);
        canc = findViewById(R.id.btnRecCancelar);

        dao = new daoUsuario(this);

        rec.setOnClickListener(this);
        canc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRecClave:
                String us = user.getEditText().getText().toString();
                String cla = clave.getEditText().getText().toString();

                if(us.equals("")&&cla.equals("")){
                    Toast.makeText(this, "ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.getClave(us,cla)==0){
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }else if(dao.getClave(us,cla)==1){
                    Usuario ux=dao.getUsuarioClave(us);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(this, editar.class);
                    i.putExtra("Id", ux.getId());
                    startActivity(i);
                }
                finish();
        }
    }
}