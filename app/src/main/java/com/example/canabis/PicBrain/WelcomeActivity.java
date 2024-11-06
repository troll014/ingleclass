// WelcomeActivity.java
//package com.example.canbay.englishforkids;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class WelcomeActivity extends AppCompatActivity {
//
//    private EditText editTextName;
//    private Button buttonContinue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//
//        editTextName = findViewById(R.id.editTextName);
//        buttonContinue = findViewById(R.id.buttonContinue);
//
//        buttonContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editTextName.getText().toString().trim();
//
//                if (!name.isEmpty()) {
//                    // Navega a MainActivity y pasa el nombre
//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    intent.putExtra("USER_NAME", name);
//                    startActivity(intent);
//                    finish(); // Cierra WelcomeActivity para evitar regresar a ella
//                } else {
//                    Toast.makeText(WelcomeActivity.this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
package com.example.canabis.PicBrain;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



//public class WelcomeActivity extends AppCompatActivity {
//
//    private EditText editTextName;
//    private Button buttonContinue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//
//        editTextName = findViewById(R.id.editTextName);
//        buttonContinue = findViewById(R.id.buttonContinue);
//
//        buttonContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editTextName.getText().toString().trim();
//
//                if (!name.isEmpty()) {
//                    // Guarda el nombre en SharedPreferences
//                    saveUserName(name);
//
//                    // Navega a MainActivity y pasa el nombre
//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    intent.putExtra("USER_NAME", name);
//                    startActivity(intent);
//                    finish(); // Cierra WelcomeActivity para evitar regresar a ella
//                } else {
//                    Toast.makeText(WelcomeActivity.this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void saveUserName(String name) {
//        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("USER_NAME", name);
//        editor.apply();
//    }
//}
public class WelcomeActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonContinue;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        editTextName = findViewById(R.id.editTextName);
        buttonContinue = findViewById(R.id.buttonContinue);
        db = AppDatabase.getInstance(this); // Inicializa la base de datos

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();

                if (!name.isEmpty()) {
                    // Verificar si el usuario ya existe en la base de datos
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = db.userDao().getUserByName(name);
                            if (user == null) {
                                // Si no existe, crea un nuevo usuario con puntos iniciales en 0
                                user = new User(name, 0);
                                db.userDao().insert(user);
                            }
                            // Navega a MainActivity y pasa el nombre
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            intent.putExtra("USER_NAME", name);
                            startActivity(intent);
                            finish();
                        }
                    }).start();
                } else {
                    Toast.makeText(WelcomeActivity.this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
