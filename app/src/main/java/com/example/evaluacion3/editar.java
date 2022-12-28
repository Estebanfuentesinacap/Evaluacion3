package com.example.evaluacion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class editar extends AppCompatActivity implements View.OnClickListener {
    Button Act, Canc;
    TextInputLayout  Cla,Cla2;
    int id=0;
    Usuario u;
    Intent x;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Cla=findViewById(R.id.tilCla);
        Cla2=findViewById(R.id.tilCla2);

        Act=findViewById(R.id.btnActualizar);
        Canc=findViewById(R.id.btnCancelar);

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuario(this);
        u=dao.getUsuarioById(id);

        Act.setOnClickListener(this);
        Canc.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActualizar:
                u.setClave(Cla2.getEditText().getText().toString());
                String clave1=Cla.getEditText().getText().toString();
                String clave2=Cla2.getEditText().getText().toString();
                if(clave1.equals("")&&clave2.equals("")){
                    Toast.makeText(this, "ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(!clave1.equals(clave2)){
                    Toast.makeText(this, "Campos no son iguales", Toast.LENGTH_LONG).show();
                }else if(dao.updatePassword(u)){
                    Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(this, MainActivity.class);
                    startActivity(i);
                }
                finish();
                break;
            case R.id.btnCancelar:
                Intent i4= new Intent(this,inicio.class);
                startActivity(i4);
                finish();
            }
        }

    }
