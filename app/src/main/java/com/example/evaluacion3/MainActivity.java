package com.example.evaluacion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout usuario,clave;
    Button ingresar, registrar;
    daoUsuario dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario=findViewById(R.id.tilUsuario);
        clave=findViewById(R.id.tilClave);
        ingresar=findViewById(R.id.btnIngresar);
        registrar=findViewById(R.id.btnRegistrar);

        ingresar.setOnClickListener(this);
        registrar.setOnClickListener(this);
        dao= new daoUsuario(this);

        cargarPreferencias();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIngresar:
                guardarPreferencias();

                String u=usuario.getEditText().getText().toString();
                String p=clave.getEditText().getText().toString();
                if (u.equals("")&&p.equals("")){
                    Toast.makeText(this,"Error: Campos vacios",Toast.LENGTH_LONG).show();
                }else if (dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(this,inicio.class);
                    i2.putExtra("id", ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario y/o contraseña incorrectos",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnRegistrar:
                Intent i=new Intent(this,Registro.class);
                startActivity(i);
                break;
        }

    }

    private void cargarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String user=preferences.getString("usuario","No existe la informacion");
        usuario.getEditText().setText(user);
    }

    private void guardarPreferencias() {

        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String user=usuario.getEditText().getText().toString();

        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("usuario",user);

        usuario.getEditText().setText(user);

        editor.commit();
    }
}