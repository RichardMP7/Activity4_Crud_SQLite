package co.edu.uniminuto.activity4_crud_sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import co.edu.uniminuto.activity4_crud_sqlite.entities.User;
import co.edu.uniminuto.activity4_crud_sqlite.repository.UserRepository;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private EditText etDocumento;
    private EditText etUsuario;
    private EditText etNombres;
    private EditText etApellidos;
    private EditText etContraseña;
    private ListView listUsers;
    SQLiteDatabase sqLiteDatabase;
    private Button btnGuardar;

    private Button btnListUsers;

    private Button btnBorrar;
    private Button btnActualizar;
    private int documento;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String pass;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        begin();
        btnGuardar.setOnClickListener(this::createUser);
        btnListUsers.setOnClickListener(this::listUsers);
        btnActualizar.setOnClickListener(this::updateUser);
        btnBorrar.setOnClickListener(this::deleteUser);

    }

    private void createUser(View view) {
        capDat();
               User user = new User(documento, nombres, apellidos, usuario, pass );
        UserRepository userRepository = new UserRepository(context, view);
        userRepository.insertUser(user);
        //Invocacion del metodo para listar los usuarios
        listUsers(view);
    }

    private void listUsers(View view) {
        UserRepository userRepository = new UserRepository(context, view);
        ArrayList<User> list = userRepository.getUserList();
        ArrayAdapter<User> arrayAdapter = new ArrayAdapter<>(
                context, android.R.layout.simple_list_item_1, list);

        // Establecer  adaptador en  ListView
        this.listUsers.setAdapter(arrayAdapter);

        // Establecer  listener para cuando un usuario haga clic en un ítem
        this.listUsers.setOnItemClickListener((parent, view1, position, id) -> {
            // Obtien usuario seleccionado de la lista
            User selectedUser = list.get(position);

            // Carga datos de usuario seleccionado en los campos de entrada
            etDocumento.setText(String.valueOf(selectedUser.getDocument()));
            etUsuario.setText(selectedUser.getUser());
            etNombres.setText(selectedUser.getName());
            etApellidos.setText(selectedUser.getLastName());
            etContraseña.setText(selectedUser.getPass());

            // Hacer visible el botón de "Actualizar" y "Borrar"
            btnBorrar.setVisibility(View.VISIBLE);
            btnActualizar.setVisibility(View.VISIBLE);
        });
    }





    private void updateUser(View view) {
        capDat();
        User user = new User(documento, nombres, apellidos, usuario, pass);
        UserRepository userRepository = new UserRepository(context, view);
        userRepository.updateUser(user);
        listUsers(view);
    }

    private void deleteUser(View view) {
        capDat();
        UserRepository userRepository = new UserRepository(context, view);
        userRepository.deleteUser(documento);
        listUsers(view);
        btnBorrar.setVisibility(View.GONE);
    }


    private void capDat(){
        //Validaciones
        this.documento = Integer.parseInt(this.etDocumento.getText().toString());
        this.nombres = this.etNombres.getText().toString();
        this.apellidos = this.etApellidos.getText().toString();
        this.usuario = this.etUsuario.getText().toString();
        this.pass = this.etContraseña.getText().toString();
    }

    private void begin(){
        this.etNombres = findViewById(R.id.etNombres);
        this.etApellidos = findViewById(R.id.etApellidos);
        this.etDocumento = findViewById(R.id.etDocumento);
        this.etUsuario = findViewById(R.id.etUsuario);
        this.etContraseña = findViewById(R.id.etContrasena);
        this.listUsers = findViewById(R.id.lvLista);
        this.btnGuardar = findViewById(R.id.btnRegistrar);
        this.btnListUsers = findViewById(R.id.btnListar);
        this.btnActualizar = findViewById(R.id.btnActualizar);
        this.btnBorrar = findViewById(R.id.btnBorrar);
        this.context = this;
    }

}