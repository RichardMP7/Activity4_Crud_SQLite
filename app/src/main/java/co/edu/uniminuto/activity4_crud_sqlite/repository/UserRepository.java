package co.edu.uniminuto.activity4_crud_sqlite.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLDataException;
import java.util.ArrayList;

import co.edu.uniminuto.activity4_crud_sqlite.dataAccess.ManagerDataBase;
import co.edu.uniminuto.activity4_crud_sqlite.entities.User;

public class UserRepository {
    private ManagerDataBase dataBase;
    private Context context;
    private View view;
    private User user;

    public UserRepository(Context context, View view) {
        this.context = context;
        this.view = view;
        this.dataBase = new ManagerDataBase(context);
    }

    public void insertUser (User user) {
        try {
            SQLiteDatabase dataBaseSql = dataBase.getWritableDatabase();
            if(dataBaseSql != null){
                ContentValues values = new ContentValues();
                values.put("use_document", user.getDocument());
                values.put("use_name", user.getName());
                values.put("use_lastname", user.getLastName());
                values.put("use_user", user.getUser());
                values.put("use_pass", user.getPass());
                values.put("use_status", "1");
                long response = dataBaseSql.insert("users", null, values);
                String message = (response >=1) ? "Se registro correctamente"
                        : "No se registro correctamente";
                Snackbar.make(this.view,message, Snackbar.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Log.i("Error en Base de Datos", "insertUser: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUserList(){
        SQLiteDatabase dataBaseSql = dataBase.getReadableDatabase();
        String query = "SELECT * FROM users WHERE use_status = 1";
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = dataBaseSql.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                User user = new User();
                user.setDocument(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setUser(cursor.getString(3));
                user.setPass(cursor.getString(4));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dataBaseSql.close();
        return users;
    }

    public void updateUser(User user) {
        SQLiteDatabase dataBaseSql = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("use_document", user.getDocument());
        values.put("use_name", user.getName());
        values.put("use_lastname", user.getLastName());
        values.put("use_user", user.getUser());
        values.put("use_pass", user.getPass());


        int rowsUpdated = dataBaseSql.update("users", values,
                "use_document = ?",
                new String[]{String.valueOf(user.getDocument())});

        String message = (rowsUpdated > 0) ? "Usuario actualizado correctamente" : "No se pudo actualizar el usuario";
        Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show();

        dataBaseSql.close();
    }

    public void deleteUser(int documento) {
        SQLiteDatabase dataBaseSql = dataBase.getWritableDatabase();
        // Eliminar usuario de la base de datos por documento
        int rowsDeleted = dataBaseSql.delete("users", "use_document = ?", new String[]{String.valueOf(documento)});

        // Mostrar mensaje según resultado
        String message = (rowsDeleted > 0) ? "Usuario eliminado correctamente" : "No se pudo eliminar el usuario";
        Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show();

        dataBaseSql.close();
    }



}
