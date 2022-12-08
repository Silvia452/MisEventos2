package com.example.listadodeeventos;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.listadodeeventos.VariablesConstantes.AFORO;
import static com.example.listadodeeventos.VariablesConstantes.DATABASE_NAME;
import static com.example.listadodeeventos.VariablesConstantes.DESCRIPCION;
import static com.example.listadodeeventos.VariablesConstantes.DIRECCION;
import static com.example.listadodeeventos.VariablesConstantes.FECHA;
import static com.example.listadodeeventos.VariablesConstantes.IMAGEN;
import static com.example.listadodeeventos.VariablesConstantes.NOMBRE;
import static com.example.listadodeeventos.VariablesConstantes.PRECIO;
import static com.example.listadodeeventos.VariablesConstantes.TABLA_EVENTOS;
import static com.example.listadodeeventos.VariablesConstantes.VERSION;

import com.example.listadodeeventos.util.Util;


public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_EVENTOS + "(" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMBRE + " TEXT, " +
                DESCRIPCION + " TEXT, " + DIRECCION + " TEXT, " + FECHA + " TEXT, " +
                AFORO + " INTEGER, " + PRECIO + " REAL, " + IMAGEN + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_EVENTOS);
        onCreate(db);
    }

    public void nuevoEvento(Evento evento) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE, evento.getNombre());
        values.put(DESCRIPCION, evento.getDescripcion());
        values.put(DIRECCION, evento.getDireccion());
        values.put(FECHA, Util.formatearFecha(evento.getFecha()));
        values.put(AFORO, evento.getAforo());
        values.put(PRECIO, evento.getPrecio());
        values.put(IMAGEN, Util.getBytes(evento.getImagen()));

        db.insertOrThrow(TABLA_EVENTOS, null, values);
        db.close();
    }

    public void eliminarEvento(Evento evento) {

        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(evento.getId())};
        db.delete(TABLA_EVENTOS, _ID + " = ?", args);
        db.close();
    }

    public void modificarEvento(Evento evento) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOMBRE, evento.getNombre());
        values.put(DESCRIPCION, evento.getDescripcion());
        values.put(DIRECCION, evento.getDireccion());
        values.put(PRECIO, evento.getPrecio());
        values.put(AFORO, evento.getAforo());
        values.put(FECHA, Util.formatearFecha(evento.getFecha()));
        values.put(IMAGEN, Util.getBytes(evento.getImagen()));

        String[] args = {String.valueOf(evento.getId())};
        db.update(TABLA_EVENTOS, values, _ID + " = ?", args);
        db.close();
    }

    public List<Evento> getEventos() {

        final String[] COLUMNAS = {_ID, NOMBRE, DESCRIPCION, DIRECCION,
                PRECIO, AFORO, FECHA, IMAGEN};
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLA_EVENTOS, COLUMNAS, null, null, null,
                null, null);

        List<Evento> eventos = new ArrayList<>();
        Evento evento = null;
        while (cursor.moveToNext()) {
            evento = new Evento();
            evento.setId(cursor.getInt(0));
            evento.setNombre(cursor.getString(1));
            evento.setDescripcion(cursor.getString(2));
            evento.setDireccion(cursor.getString(3));
            evento.setPrecio(cursor.getFloat(4));
            evento.setAforo(cursor.getInt(5));
            try {
                evento.setFecha(Util.parsearFecha(cursor.getString(6)));
            } catch (ParseException pe) {
                evento.setFecha(new Date());
            }
            evento.setImagen(Util.getBitmap(cursor.getBlob(7)));
            eventos.add(evento);
        }

        return eventos;
    }

    public List<Evento> getEventos(String busqueda) {

        return null;
    }
}
