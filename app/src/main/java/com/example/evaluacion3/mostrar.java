package com.example.evaluacion3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class mostrar extends AppCompatActivity {
    ListView lista;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lista=findViewById(R.id.lvMostrar);
        dao= new daoUsuario(this);
        ArrayList<Usuario> i=dao.selectUsuarios();
        ArrayList<String> list= new ArrayList<String>();
        for (Usuario u:i) {
            list.add(u.getNombre()+" "+u.getApellido());
        }
        ArrayAdapter<String> a= new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);

    }
}