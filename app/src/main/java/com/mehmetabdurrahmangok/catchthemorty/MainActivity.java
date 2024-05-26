package com.mehmetabdurrahmangok.catchthemorty;

import static android.content.DialogInterface.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView timeText;
    int score;

    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;



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

        textView = findViewById(R.id.scoreView);
        timeText = findViewById(R.id.textView);
        score = 0;

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView1);
        imageView3 = findViewById(R.id.imageView2);
        imageView4 = findViewById(R.id.imageView3);
        imageView5 = findViewById(R.id.imageView4);
        imageView6 = findViewById(R.id.imageView5);
        imageView7 = findViewById(R.id.imageView6);
        imageView8 = findViewById(R.id.imageView7);
        imageView9 = findViewById(R.id.imageView8);



        imageArray = new ImageView[] {imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImage();

        new CountDownTimer(20000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText("time: " + millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                timeText.setText("time off");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
                alertdialog.setTitle("Restart?");
                alertdialog.setMessage("Are you sure to restart game?");

                alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivities(new Intent[]{intent});
                    }
                });
                alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_LONG).show();
                    }
                });

                alertdialog.show();

            }
        }.start();


    }

    public void skorarttir(View view) {
        score++;
        textView.setText("score: " + score);

    }

    public void hideImage() {

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 380);

            }
        };

        handler.post(runnable);



    }
}