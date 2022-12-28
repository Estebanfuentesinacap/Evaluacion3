package com.example.evaluacion3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class mostrar extends AppCompatActivity implements View.OnClickListener {
    Button reg;
    ListView listaEventos;
    daoUsuario dao;
    daoEvento daoEv;
    int id = 0;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        reg = findViewById(R.id.btnReg);
        reg.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        dao = new daoUsuario(this);
        Usuario u = new Usuario();
        u = dao.getUsuarioById(id);

        listaEventos = (ListView) findViewById(R.id.lvMostrar);

        daoEv = new daoEvento(this);
        ArrayList<Evento> l = daoEv.selectEventos(u.getId());

        ArrayList<String> list = new ArrayList<String>();
        for (Evento e : l) {
            list.add("Titulo: " + e.getTitulo() + " / " + "Fecha: " + e.getFecha());
        }

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        listaEventos.setAdapter(a);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg:
                Intent c = new Intent(this, inicio.class);
                startActivity(c);
                finish();
                break;

        }

    }
}