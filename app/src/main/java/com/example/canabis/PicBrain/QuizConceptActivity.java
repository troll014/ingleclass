package com.example.canabis.PicBrain;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.ArrayList;
import java.util.List;
import android.media.MediaPlayer;


public class QuizConceptActivity extends AppCompatActivity {

    private List<Question> questionsList;
    private Question currentQuestion;

    private TextView txtQuestion,tvNoOfQs, levels;
    private RadioButton rbtnA, rbtnB, rbtnC,rbtnD;
    private Button btnNext;
    private ImageView questionImage;

    private int obtainedScore;
    private int questionId;

    private int answeredQsNo=0;


    private TextView tvLevel;
    private int level = 1;
    private MediaPlayer mediaPlayer;


    ArrayList<String> myAnsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_concept);


        //Initialize the view
        init();

        // Establecemos el texto inicial para mostrar el nivel 1

        updateLevelText();


        //Initialize the database
        final DBAdapter dbAdapter=new DBAdapter(this);
        questionsList= dbAdapter.getRandomQuestions();
        currentQuestion=questionsList.get(questionId);

        //Set question
        setQuestionsView();

        //Check and Next
      checknext();
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
//                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
//
//                Log.e("Answer ID", "Selected Positioned  value - "+grp.getCheckedRadioButtonId());
//
//                if(answer!=null){
//                    Log.e("Answer", currentQuestion.getANSWER() + " -- " + answer.getText());
//                    //Add answer to the list
//                    myAnsList.add(""+answer.getText());
//
//                    if(currentQuestion.getANSWER().equals(answer.getText())){
//                        obtainedScore++;
//                        Log.e("comments", "Respuesta Correcta");
//                        Log.d("score", "Tu Puntaje es " + obtainedScore);
//                    }else{
//                        Log.e("comments", "Respuesta Incorrecta");
//                    }
//
//                    if(questionId<10){
//                        currentQuestion=questionsList.get(questionId);
//                        setQuestionsView();
//                    }else{
//                        Intent intent = new Intent(QuizConceptActivity.this, QuizResultActivity.class);
//
//                        Bundle b = new Bundle();
//                        b.putInt("score", obtainedScore);
//                        b.putInt("totalQs", questionsList.size());
//                        b.putStringArrayList("myAnsList", myAnsList);
//                        intent.putExtras(b);
//                        startActivity(intent);
//                        finish();
//
//                    }
//
//                }else{
//                    Log.e("comments", "No Answer");
//                }
//
//                //Need to clear the checked item id
//                grp.clearCheck();
//
//
//            }//end onClick Method
//        });


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("level")) {
            level = intent.getIntExtra("level", 1); // Si se pasó el nivel, actualízalo
            Log.d("QuizConceptActivity", "Nivel recibido: " + level); // Verificar nivel recibido
        }


    }

    public void init(){
        //inicialiar el nivel

        tvNoOfQs=(TextView)findViewById(R.id.tvNumberOfQuestions);
        txtQuestion=(TextView)findViewById(R.id.tvQuestion);
        questionImage = (ImageView) findViewById(R.id.questionImage);


        rbtnA=(RadioButton)findViewById(R.id.radio0);
        rbtnB=(RadioButton)findViewById(R.id.radio1);
        rbtnC=(RadioButton)findViewById(R.id.radio2);
        rbtnD=(RadioButton)findViewById(R.id.radio3);

        btnNext=(Button)findViewById(R.id.btnNext);
        tvLevel = findViewById(R.id.tvLevel);
        myAnsList = new ArrayList<String>();
    }


    private void setQuestionsView()
    {
        rbtnA.setChecked(false);
        rbtnB.setChecked(false);
        rbtnC.setChecked(false);
        rbtnD.setChecked(false);

        answeredQsNo=questionId+1;
         tvNoOfQs.setText(answeredQsNo+  "   Nùmero de preguntas: "+questionsList.size());

        questionImage.setImageResource(currentQuestion.getQUESTIONImageId());
        txtQuestion.setText(currentQuestion.getQUESTION());
        rbtnA.setText(currentQuestion.getOptionA());
        rbtnB.setText(currentQuestion.getOptionB());
        rbtnC.setText(currentQuestion.getOptionC());
        rbtnD.setText(currentQuestion.getOptionD());

        questionId++;
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



    private void updateLevelText() {
        if (tvLevel != null) {
            tvLevel.setText("Nivel " + level);
            Log.d("QuizConceptActivity", "Nivel actualizado en la interfaz: " + level);
        } else {
            Log.e("QuizConceptActivity", "Error: tvLevel es nulo");
        }

    }



    //funcion chech
//    public void checknext() {
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RadioGroup grp = findViewById(R.id.radioGroup1);
//                RadioButton answer = findViewById(grp.getCheckedRadioButtonId());
//
//                Log.e("Answer ID", "Selected Positioned value - " + grp.getCheckedRadioButtonId());
//
//                if (answer != null) {
//                    Log.e("Answer", currentQuestion.getANSWER() + " -- " + answer.getText());
//                    myAnsList.add("" + answer.getText());
//
//                    // Verifica si la respuesta es correcta y actualiza el puntaje
//                    if (currentQuestion.getANSWER().equals(answer.getText())) {
//                        obtainedScore++;
//                        Log.e("comments", "Respuesta Correcta");
//                        Log.d("score", "Tu Puntaje es " + obtainedScore);
//                    } else {
//                        Log.e("comments", "Respuesta Incorrecta");
//                    }
//
//                    // Verificar si quedan preguntas en el nivel actual
//                    if (questionId < 10) {
//                        currentQuestion = questionsList.get(questionId);
//                        setQuestionsView();
//                    } else {
//                        // Llamar a showResultActivity() para mostrar resultados o avanzar de nivel
//                        showResultActivity();
//                    }
//                } else {
//                    Log.e("comments", "No Answer");
//                }
//
//                grp.clearCheck();  // Limpiar la selección actual
//            }
//        });
//    }
//
//
//
//
//    private void showResultActivity() {
//        // Verificar si se cumplen los requisitos de puntaje para avanzar de nivel
//        if ((level == 1 && obtainedScore >= 3) ||
//                (level == 2 && obtainedScore >= 7) ||
//                (level == 3 && obtainedScore >= 9)) {
//
//            // Incrementa el nivel
//            level++;
//            Log.d("QuizConceptActivity", "Nivel actualizado a: " + level);
//
//            // Actualizar el TextView si es necesario (si quieres que se muestre en la interfaz actual)
//            updateLevelText();
//        }
//
//        // Iniciar el QuizResultActivity con los datos necesarios
//        Intent intent = new Intent(QuizConceptActivity.this, QuizResultActivity.class);
//        Bundle b = new Bundle();
//        b.putInt("score", obtainedScore);
//        b.putInt("totalQs", questionsList.size());
//        b.putInt("level", level); // Enviar el nivel actual (actualizado si corresponde)
//        b.putStringArrayList("myAnsList", myAnsList);
//        intent.putExtras(b);
//        startActivity(intent);
//    }

    public void checknext() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = findViewById(R.id.radioGroup1);
                RadioButton answer = findViewById(grp.getCheckedRadioButtonId());

                Log.e("Answer ID", "Selected Positioned value - " + grp.getCheckedRadioButtonId());

                if (answer != null) {
                    Log.e("Answer", currentQuestion.getANSWER() + " -- " + answer.getText());
                    myAnsList.add("" + answer.getText());

                    // Verifica si la respuesta es correcta y actualiza el puntaje
                    if (currentQuestion.getANSWER().equals(answer.getText())) {
                        obtainedScore++;
                        Log.e("comments", "Respuesta Correcta");
                        Log.d("score", "Tu Puntaje es " + obtainedScore);
                    } else {
                        Log.e("comments", "Respuesta Incorrecta");
                    }

                    // Verificar si quedan preguntas en el nivel actual
                    if (questionId < 10) {
                        currentQuestion = questionsList.get(questionId);
                        setQuestionsView();
                    } else {
                        // Llamar a showResultActivity() para mostrar resultados o verificar si se avanza de nivel
                        showResultActivity();
                    }
                } else {
                    Log.e("comments", "No Answer");
                }

                grp.clearCheck();  // Limpiar la selección actual
            }
        });
    }

    private void showResultActivity() {
        // Verificar si el puntaje acumulado permite avanzar al siguiente nivel
        boolean levelUp = false;
        if ((level == 1 && obtainedScore >= 2) ||
                (level == 2 && obtainedScore >= 7) ||
                (level == 3 && obtainedScore >= 9)) {
            if (level == 1 && obtainedScore >= 2) {
                playSuccessSound();  // Llamada para reproducir el sonido
            }
            // Incrementa el nivel solo si se alcanza el puntaje necesario
            level++;
            levelUp = true;
            Log.d("QuizConceptActivity", "Nivel actualizado a: " + level);

            // Actualiza el TextView de nivel en la interfaz
            updateLevelText();
        }

        // Iniciar el QuizResultActivity con los datos necesarios
        Intent intent = new Intent(QuizConceptActivity.this, QuizResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("score", obtainedScore);  // Puntaje acumulado
        b.putInt("totalQs", questionsList.size());
        b.putInt("level", level); // Enviar el nivel actual
        b.putStringArrayList("myAnsList", myAnsList);
        b.putBoolean("levelUp", levelUp); // Indicar si avanzó de nivel
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }



    private void playSuccessSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.success_sound);  // Reemplaza con el nombre de tu archivo
        }
        mediaPlayer.start();

        // Liberar el MediaPlayer después de la reproducción
        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();
            mediaPlayer = null;
        });
    }





    @Override
    protected void onResume() {
        super.onResume();
        updateLevelText(); // Asegura que el nivel se muestre correctamente al regresar a esta actividad
    }




}