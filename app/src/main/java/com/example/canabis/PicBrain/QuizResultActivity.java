package com.example.canabis.PicBrain;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizResultActivity extends AppCompatActivity {

    ArrayList<String> myAnsList=new ArrayList<String>();
    private Button btnNextLevel;
    private int currentLevel;
    private boolean passedLevel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_result);



        //get rating bar object
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);

        //get text view
        TextView tvAnsweredInfo =(TextView)findViewById(R.id.tvAnsweredInfo);
        TextView t=(TextView)findViewById(R.id.textResult);

        //get score with the bundle
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        int totalQs= b.getInt("totalQs");
        myAnsList=b.getStringArrayList("myAnsList");

        //display score
        bar.setRating(score);

        tvAnsweredInfo.setText("Respondiste correctamente a " + score + " de " + totalQs + " preguntas.");


        float percentage=(score*100)/totalQs;

        if (percentage >= 80 && percentage <= 100) {
            t.setText("¡Tu puntaje es excelente!");
        } else if (percentage >= 70 && percentage <= 79) {
            t.setText("¡Tu puntaje es bueno!");
        } else if (percentage >= 60 && percentage <= 69) {
            t.setText("¡Tu puntaje es satisfactorio!");
        } else if (percentage >= 50 && percentage <= 59) {
            t.setText("¡Tu puntaje es promedio!");
        } else if (percentage >= 33 && percentage <= 49) {
            t.setText("¡Tu puntaje está por debajo del promedio!");
        } else {
            t.setText("¡Tu puntaje es bajo, necesitas estudiar más!");
        }


        final Intent intent = new Intent(this,MainActivity.class);
        Button btnDone=(Button)findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });

        Button btnViewAnswer=(Button)findViewById(R.id.btnViewAnswer);
        btnViewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent=new Intent(QuizResultActivity.this,QuizViewAnswerActivity.class);
                vIntent.putStringArrayListExtra("myAnsList",myAnsList);
                startActivity(vIntent);
            }

        });



        // Obtener el puntaje y nivel del Intent

        int level = b.getInt("level");
        currentLevel = level; // Guardar el nivel actual

        // Determinar si se pasa al siguiente nivel
        passedLevel = (level == 1 && score >= 5) || (level == 2 && score >= 7);

        // Configuración del botón para avanzar o repetir el nivel
        btnNextLevel = findViewById(R.id.btnNextLevel);
        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log para verificar el nivel antes de pasar a la siguiente actividad
                Log.d("QuizResultActivity", "Nivel actual: " + currentLevel + ", Pasado: " + passedLevel);

                // Regresar a QuizConceptActivity con el nivel actualizado si pasa, o repetir el nivel si no pasa
                Intent intent = new Intent(QuizResultActivity.this, QuizConceptActivity.class);
                intent.putExtra("level", passedLevel ? currentLevel + 1 : currentLevel); // Siguiente nivel o mismo nivel
                startActivity(intent);
                finish(); // Cierra QuizResultActivity
            }
        });













    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }






    private void savePointsToServer(String username, int points) {
        ApiService apiService = ApiClient.getApiService();
        Call<ResponseBody> call = apiService.savePoints(username, points);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("QuizResultActivity", "Puntos guardados con éxito en el servidor");
                } else {
                    Log.e("QuizResultActivity", "Error al guardar puntos en el servidor");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("QuizResultActivity", "Error de conexión al servidor: " + t.getMessage());
            }
        });
    }







}