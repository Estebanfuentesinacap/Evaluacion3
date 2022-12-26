package com.example.evaluacion3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout usuario, clave, nombre, apellido, palabra;
    Button registrar, cancelar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario=findViewById(R.id.tilRegUsuario);
        clave=findViewById(R.id.tilRegClave);
        nombre=findViewById(R.id.tilRegNombre);
        apellido=findViewById(R.id.tilRegApellido);
        palabra=findViewById(R.id.tilRegPalabra);

        registrar=findViewById(R.id.btnCrear);
        cancelar=findViewById(R.id.btnSalir);
        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        dao=new daoUsuario( this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCrear:
                Usuario u=new Usuario();
                u.setUsuario(usuario.getEditText().getText().toString());
                u.setClave(clave.getEditText().getText().toString());
                u.setNombre(nombre.getEditText().getText().toString());
                u.setApellido(apellido.getEditText().getText().toString());
                u.setPalabra(palabra.getEditText().getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"Error: campos vacios",Toast.LENGTH_LONG).show();
                }else if (dao.insertUsuario(u)){
                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btnSalir:
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
                break;

        }

    }
}
