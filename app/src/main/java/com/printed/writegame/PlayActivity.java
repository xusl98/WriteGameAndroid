package com.printed.writegame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class PlayActivity extends AppCompatActivity {

    private Button btn_check;
    private Button btn_start;
    private TextInputLayout input_word;
    private TextView text_view_word;
    private TextView text_view_points;
    private int points;

    private AdView mAdView;

    private String[] palabras;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btn_check = (Button) findViewById(R.id.btn_check);
        btn_start = (Button) findViewById(R.id.btn_start);

        input_word = (TextInputLayout) findViewById(R.id.input_word);
        text_view_word = (TextView) findViewById(R.id.text_view_word);
        text_view_points = (TextView) findViewById(R.id.text_view_points);

        points = 0;

        palabras = getApplicationContext().getResources().getStringArray(R.array.car_array);



        //****************EVENT LISTENER FOR START BUTTON************************
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points = 0;
                text_view_points.setText(String.valueOf(points));
                text_view_word.setText(palabra());

                Thread one = new Thread() {
                    public void run() {
                        try {
                            long start = System.currentTimeMillis();
                            long end = start + 40 * 1000; // 40 seconds * 1000 ms/sec
                            while (System.currentTimeMillis() < end) {

                            }


                            Thread.sleep(0000);


                            Intent scoreIntent = new Intent(PlayActivity.this, ScoreBoardActivity.class);
                            scoreIntent.putExtra("points", points);
                            startActivity(scoreIntent);

//                            System.out.println("¡¡You got " + points + " points!!");

                        } catch (InterruptedException v) {
                            System.out.println(v);
                        }
                    }
                };
                one.start();
            }
        });

        //****************EVENT LISTENER FOR CHECK BUTTON*************************
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (input_word.getEditText().getText().toString().equals(text_view_word.getText().toString())) {
//                    Context ctx = getApplicationContext();
//                    Toast toast = Toast.makeText(ctx, "Works fine", Toast.LENGTH_SHORT);
//                    toast.show();
                    points++;
                    text_view_points.setText(String.valueOf(points));
                    input_word.getEditText().getText().clear();
                    text_view_word.setText(palabra());

                }


            }
        });


    }
    public String palabra() {

        int numero = (int) (Math.random() * 15);//numero de palabras menos una
        return palabras[numero];
    }


}
