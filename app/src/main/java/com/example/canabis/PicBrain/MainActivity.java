package com.example.canabis.PicBrain;

import android.os.AsyncTask;
import android.content.Intent;
import android.media.MediaPlayer;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.canabis.PicBrain.R;

import org.json.JSONObject;

import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView daysandmonths;
    ImageView numbers;
    ImageView animals;
    ImageView fruits;
    ImageView goods;
    ImageView colors;
    Button quizyap;
    MediaPlayer number;
    MediaPlayer animal;
    MediaPlayer fruit;
    MediaPlayer day;
    MediaPlayer object;
    MediaPlayer color;

    private AppDatabase db;
//    private String userName;
    private int currentPoints;

    private String userName = "Loo";
    private TextView pointsTextView;
    private TextView playerNameTextView;
    int points = 0;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daysandmonths = (ImageView) findViewById(R.id.daysandmonths);
        numbers = (ImageView) findViewById(R.id.numbers);
        animals = (ImageView) findViewById(R.id.animals);
        fruits = (ImageView) findViewById(R.id.fruits);
        goods = (ImageView) findViewById(R.id.goods);
        colors = (ImageView) findViewById(R.id.colors);
        quizyap = (Button) findViewById(R.id.quizyap);

        number = MediaPlayer.create(this,R.raw.numbers);
        day = MediaPlayer.create(this,R.raw.days);
        animal = MediaPlayer.create(this,R.raw.animals);
        fruit = MediaPlayer.create(this,R.raw.fruits);
        object = MediaPlayer.create(this,R.raw.objects);
        color = MediaPlayer.create(this,R.raw.colors);





        final Intent intentdays = new Intent(this, TabbedActivityDays.class);
        final Intent intentnumbers = new Intent(this, TabbedActivityNumbers.class);
        final Intent intentanimals = new Intent(this,TabbedActivityAnimals.class);
        final Intent intentfruits = new Intent(this,TabbedActivityFruits.class);
        final Intent intentobjects = new Intent(this,TabbedActivityGoods.class);
        final Intent intentcolors = new Intent(this,TabbedActivityColors.class);
        final Intent quizsayfasi = new Intent(this,QuizMainActivity.class);


        daysandmonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day.start();
                startActivity(intentdays);

            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number.start();
                startActivity(intentnumbers);

            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animal.start();
                startActivity(intentanimals);
            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruit.start();
                startActivity(intentfruits);
            }
        });

        goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.start();
                startActivity(intentobjects);
            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color.start();
                startActivity(intentcolors);
            }
        });

        quizyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(quizsayfasi);
            }
        });



//        db = AppDatabase.getInstance(this);
//        // Obtén el nombre del usuario desde el Intent
//
//
//        pointsTextView = findViewById(R.id.pointsTextView);
//        playerNameTextView = findViewById(R.id.playerNameTextView);
//        userName = getIntent().getStringExtra("USER_NAME");
//        if (userName != null && !userName.isEmpty()) {
//            playerNameTextView.setText("Jugador: " + userName);
//        }
//        // getCurrentPoints();
//        checkAndCreateUser();
        userName = getIntent().getStringExtra("USER_NAME");
        playerNameTextView = findViewById(R.id.playerNameTextView);

        if (userName != null && !userName.isEmpty()) {
            playerNameTextView.setText("Jugador: " + userName);
            loadUserPoints(userName); // Carga los puntos del usuario desde la base de datos
        } else {
            Toast.makeText(this, "No se encontró el nombre del usuario", Toast.LENGTH_SHORT).show();
        }
        getPoints(userName);
//        savePoints(userName, 100);

//        playerNameTextView = findViewById(R.id.playerNameTextView);
  pointsTextView = findViewById(R.id.pointsTextView);
//        playerNameTextView.setText("Jugador: " + userName);
//        userName = getIntent().getStringExtra("USER_NAME");

        saveUser(userName, points);
        Log.d(TAG, "Guardando usuario: " + userName + " con puntos iniciales: " + points);














    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        day.release();
        number.release();
        animal.release();
        fruit.release();
        object.release();
        color.release();

    }




    //aqui agrego los metodos

//    private void getCurrentPoints() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = db.userDao().getUserByName(userName);
//                if (user != null) {
//                    currentPoints = user.getPoints();
//                    runOnUiThread(() -> {
//                        // Muestra los puntos en la interfaz
//                        TextView pointsTextView = findViewById(R.id.pointsTextView);
//                        pointsTextView.setText("Puntos: " + currentPoints);
//                    });
//                }
//            }
//        }).start();
//    }
//
//    // Función para actualizar los puntos del usuario en la base de datos
//    private void updatePointsInDatabase(int newPoints) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = db.userDao().getUserByName(userName);
//                if (user != null) {
//                    user.setPoints(newPoints);
//                    db.userDao().update(user);
//                }
//            }
//        }).start();
//    }
//
//    // Función para agregar puntos al usuario y actualizar en la base de datos
//    public void addPoints(int points) {
//        currentPoints += points;
//        updatePointsInDatabase(currentPoints); // Actualiza en la base de datos
//        updatePointsDisplay(currentPoints); // Actualiza en la interfaz
//    }
//
//
//    // Función para actualizar la visualización de puntos en la interfaz
//    private void updatePointsDisplay(int points) {
//        runOnUiThread(() -> pointsTextView.setText("Puntos Totales: " + points));
//    }
//
//
//    private void checkAndCreateUser() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                User user = db.userDao().getUserByName(userName);
//                if (user == null) {
//                    // Si el usuario no existe, crear uno nuevo con puntos en 0
//                    user = new User(userName, 0);
//                    db.userDao().insert(user);
//                    currentPoints = 0;
//                } else {
//                    // Si el usuario ya existe, obtenemos sus puntos
//                    currentPoints = user.getPoints();
//                }
//
//                // Actualiza el TextView con los puntos actuales
//                runOnUiThread(() -> pointsTextView.setText("Puntos Totales: " + currentPoints));
//            }
//        }).start();
//    }
//ESTE CODIGO DE ABAJO FUNCIONA TOTALMENTE PARA OBTENER UN PUNTOS DESDE PHP
    private void getPoints(String username) {
        Call<ResponseBody> call = ApiClient.getApiService().getPointsRaw(username);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseBody = response.body().string();
                        // Parseo manual del JSON
                        JSONObject jsonObject = new JSONObject(responseBody);
                        int points = jsonObject.getInt("points");

                        // Actualiza el TextView con los puntos
                        pointsTextView.setText("Puntos Totales: " + points);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error al procesar los puntos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No se pudieron cargar los puntos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión al cargar puntos", Toast.LENGTH_SHORT).show();
            }
        });
    }



//    private void savePoints(String username, int points) {
//        ApiService apiService = ApiClient.getApiService();
//        Call<ResponseBody> call = apiService.savePoints(username, points);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "Puntos guardados con éxito", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Error al guardar puntos", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//    private void savePointsToServer(String username, int points) {
//        ApiService apiService = ApiClient.getApiService();
//        Call<ResponseBody> call = apiService.savePoints(username, points);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), "Puntos guardados con éxito", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Puntos guardados con éxito", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Puntos guardados con éxito", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }








    private void loadUserPoints(String username) {
        ApiService apiService = ApiClient.getApiService();
        Call<ResponseBody> call = apiService.getPointsRaw(username);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseBody = response.body().string();
                        Log.d("MainActivity", "Respuesta del servidor: " + responseBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error al procesar los puntos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("MainActivity", "Respuesta fallida: " + response.message());
                    Log.e("MainActivity", "URL: " + call.request().url());
                    Toast.makeText(MainActivity.this, "No se pudieron cargar los puntos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("MainActivity", "Error de conexión: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión al cargar puntos", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void saveUser(String name, int points) {
        User user = new User(name, points);

        // Usar AsyncTask para realizar operaciones en la base de datos
        AsyncTask.execute(() -> {
            try {
                Log.d(TAG, "Intentando guardar el usuario en la base de datos...");
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .insert(user); // Llama al método de inserción de UserDao
                Log.d(TAG, "Usuario guardado correctamente: " + name);
            } catch (Exception e) {
                Log.e(TAG, "Error al guardar el usuario", e);
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error al guardar el usuario", Toast.LENGTH_SHORT).show());
            }
        });
    }


}

