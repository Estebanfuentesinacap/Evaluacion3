package com.example.evaluacion3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "create table if not exists Usuario(id integer primary key autoincrement, usuario text, clave text, nombre text, apellido text, palabra text)";

    public daoUsuario(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }




    public boolean insertUsuario(Usuario u) {
        if (buscar(u.getUsuario()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("clave", u.getClave());
            cv.put("nombre", u.getNombre());
            cv.put("apellido", u.getApellido());
            cv.put("palabra", u.getPalabra());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }
    }



    public int buscar(String u) {
        int x = 0;
        lista=selectUsuarios();
        for (Usuario us:lista){
            if (us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario",null);
        if (cr!=null&&cr.moveToFirst()){
            do {
                Usuario u=new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setClave(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellido(cr.getString(4));
                u.setPalabra(cr.getString(5));
                lista.add(u);
            }while (cr.moveToNext());
        }
        return lista;
    }


    public  int login(String u, String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuario",null);
        if (cr!=null&&cr.moveToFirst()){
            do {
                if (cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while (cr.moveToNext());
        }
        return a;
    }


    public Usuario getUsuario(String u, String p){
        lista=selectUsuarios();
        for (Usuario us:lista) {
            if (us.getUsuario().equals(u)&&us.getClave().equals(p)){
                return us;
            }
        }return null;
    }

    public Usuario getUsuarioById(int id){
        lista=selectUsuarios();
        for (Usuario us:lista) {
            if (us.getId()==id){
                return us;
            }
        }return null;
    }



    public Boolean updateUsuario(Usuario u){
        ContentValues cv = new ContentValues();
        cv.put("ID",u.getId());
        cv.put("clave", u.getClave());
        cv.put("nombre", u.getNombre());
        cv.put("apellido", u.getApellido());
        cv.put("palabra", u.getPalabra());
        return (sql.update("Usuario",cv,"id"+u.getId()+"clave"+u.getClave(), null) > 0);
    }

    public boolean deleteUsuario(int id){
        for(int i = 0; i < lista.size(); i = i + 1){
            if(lista.get(i).getId() == id){
                lista.removeAll(Collections.singleton(i));
                return true;
            }
        }

        return false;
    }
}
