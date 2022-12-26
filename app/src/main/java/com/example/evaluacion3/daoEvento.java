package com.example.evaluacion3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class daoEvento {
    Context c;
    Evento e;
    ArrayList<Evento> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tablaEventos="create table if not exists evento(id integer primary key autoincrement, titulo text, fecha string, importancia text, observacion text, lugar text, tiempo int, id_usuario int, CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id))";

    public daoEvento(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd, Context.MODE_PRIVATE,null);
        sql.execSQL(tablaEventos);
        e=new Evento();
    }

    public boolean insertEvento(Evento e){
        ContentValues cv = new ContentValues();
        cv.put("titulo", e.getTitulo());
        cv.put("fecha", e.getFecha());
        cv.put("importancia", e.getImportancia());
        cv.put("observacion", e.getObservacion());
        cv.put("lugar", e.getLugar());
        cv.put("tiempo", e.getTiempo());
        cv.put("id_usuario", e.getId_usuario());
        return (sql.insert("evento", null, cv) > 0);

    }

    public ArrayList<Evento> selectEventos(int id){
        String[] param = new String[]{String.valueOf(id)};
        ArrayList<Evento> lista= new ArrayList<Evento>();
        lista.clear();
        Cursor cr = sql.rawQuery("Select * from evento where id_usuario= ?", param);
        if(cr!=null&&cr.moveToFirst()){
            do{
                Evento e=new Evento();
                e.setId(cr.getInt(0));
                e.setTitulo(cr.getString(1));
                e.setFecha(cr.getString(2));
                e.setImportancia(cr.getString(3));
                e.setObservacion(cr.getString(4));
                e.setLugar(cr.getString(5));
                e.setTiempo(cr.getString(6));
                e.setId_usuario(cr.getInt(7));
                lista.add(e);
            }while(cr.moveToNext());
        }
        return lista;
    }


}
