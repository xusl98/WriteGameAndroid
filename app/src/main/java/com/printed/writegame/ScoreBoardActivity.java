package com.printed.writegame;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;

public class ScoreBoardActivity extends AppCompatActivity {

    private int points;
    private int high_score;

    private TextView text_view_score;
    private TextView text_view_record;
    private TextView text_view_high_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);



        points = getIntent().getIntExtra("points", 0);

        text_view_high_score = (TextView) findViewById(R.id.text_view_high_score);
        text_view_score = (TextView) findViewById(R.id.text_view_score);
        text_view_record = (TextView) findViewById(R.id.text_view_record);

        text_view_score.setText(String.valueOf(points));

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        high_score = prefs.getInt("high_score", 0);

        text_view_high_score.setText(String.valueOf(high_score));

        if (points > Integer.parseInt(text_view_high_score.getText().toString())) {
            high_score = points;
            text_view_record.setText(getString(R.string.record));
            text_view_high_score.setText(String.valueOf(high_score));
//            SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("high_score", high_score);
            editor.commit();

        } else {
            text_view_record.setText("");
        }


    }
}
