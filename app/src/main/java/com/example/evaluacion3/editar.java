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
    TextInputLayout  Cla,User;
    int id=0;
    Usuario u;
    daoUsuario dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Cla=findViewById(R.id.tilCla);
        User=findViewById(R.id.tilUser);

        Act=findViewById(R.id.btnActualizar);
        Canc=findViewById(R.id.btnCancelar);
        Bundle b = getIntent().getExtras();
        dao= new daoUsuario(this);
        id=b.getInt("id");
        u=dao.getUsuarioById(id);

        Act.setOnClickListener(this);
        Canc.setOnClickListener(this);

        User.getEditText().setText(u.getUsuario());
        Cla.getEditText().setText(u.getClave());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActualizar:
                u.setUsuario(User.getEditText().getText().toString());
                u.setClave(Cla.getEditText().getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"Error: campos vacios",Toast.LENGTH_LONG).show();
                }else if (dao.updateUsuario(u)){
                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                    Intent i3=new Intent(this,inicio.class);
                    i3.putExtra("id",u.getId());
                    startActivity(i3);
                    finish();
                }else {
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }
            case R.id.btnCancelar:
                Intent i4= new Intent(this,inicio.class);
                i4.putExtra("id",u.getId());
                startActivity(i4);
                finish();
            }
        }

    }
