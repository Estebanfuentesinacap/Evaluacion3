package com.example.evaluacion3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class inicio extends AppCompatActivity implements View.OnClickListener {
    Button mostrar, salir, editar, borrar,guardar;
    TextInputLayout titulo, fecha, importancia, observacion, lugar, tiempo;
    TextView nombre;
    int id=0;
    Usuario u;
    daoUsuario dao;
    daoEvento daoEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        nombre=findViewById(R.id.tvNombre);
        mostrar=findViewById(R.id.btnMostrar);
        salir=findViewById(R.id.btnSalirLog);
        editar=findViewById(R.id.btnEditar);
        borrar=findViewById(R.id.btnBorrar);
        guardar=findViewById(R.id.btnAgregar);


        titulo=findViewById(R.id.tilTitulo);
        fecha=findViewById(R.id.tilFecha);
        importancia=findViewById(R.id.tilImportancia);
        observacion=findViewById(R.id.tilObservacion);
        lugar=findViewById(R.id.tilLugar);
        tiempo=findViewById(R.id.tilTiempo);


        mostrar.setOnClickListener(this);
        salir.setOnClickListener(this);
        editar.setOnClickListener(this);
        borrar.setOnClickListener(this);
        guardar.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        dao= new daoUsuario(this);
        daoEvent = new daoEvento(this);
        id=b.getInt("id");
        u=dao.getUsuarioById(id);
        nombre.setText("Bienvenido: "+u.getNombre());
        limpiarPantalla();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMostrar:
                Intent i=new Intent(this,mostrar.class);
                i.putExtra("id", u.getId());
                startActivity(i);
                break;
            case R.id.btnSalirLog:
                Intent c=new Intent(this,MainActivity.class);
                startActivity(c);
                finish();
                break;
            case R.id.btnEditar:
                Intent a=new Intent(this,editar.class);
                a.putExtra("id",id);
                startActivity(a);

            case R.id.btnAgregar:
                Evento e= new Evento();


                e.setTitulo(titulo.getEditText().getText().toString());
                e.setFecha(fecha.getEditText().getText().toString());
                e.setImportancia(importancia.getEditText().getText().toString());
                e.setObservacion(observacion.getEditText().getText().toString());
                e.setLugar(lugar.getEditText().getText().toString());
                e.setTiempo(tiempo.getEditText().getText().toString());
                e.setId_usuario(id);
                if(!e.isNull()){
                    Toast.makeText(this, "ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(daoEvent.insertEvento(e)){
                    Toast.makeText(this, "Registro creado exitosamente", Toast.LENGTH_LONG).show();
                    limpiarPantalla();


                }

                break;
            case R.id.btnBorrar:
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                b.setMessage("Esta seguro de eliminar la cuenta??");
                b.setCancelable(false);
                b.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dao.deleteUsuario(id)){
                            Toast.makeText(inicio.this,"Se elimino de forma exitosa!!!",Toast.LENGTH_LONG).show();
                            Intent a=new Intent(inicio.this,MainActivity.class);
                            a.putExtra("id", id);
                            startActivity(a);
                            finish();
                        }else{
                            Toast.makeText(inicio.this,"No se elimino Usuario",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
        }

    }

    private void limpiarPantalla(){
        titulo.getEditText().setText("");
        fecha.getEditText().setText("");
        importancia.getEditText().setText("");
        observacion.getEditText().setText("");
        tiempo.getEditText().setText("");
        lugar.getEditText().setText("");
    }

}