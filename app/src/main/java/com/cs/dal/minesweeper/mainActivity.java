package com.cs.dal.minesweeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import static com.cs.dal.minesweeper.loadGridPage.mygameplay;
import static java.lang.Thread.sleep;


public class mainActivity extends Activity {

    public static int No_of_Bombs;
    public static int total_no_of_Cells =81;
    private boolean toExit;

    /**
     * Method to check the back button pressed
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you really sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = dialog.create();
        alert.show();
    }
    /**
     * Method to check the back button pressed
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mygameplay.createNewGrid(this);
        setContentView(R.layout.home);
        Button Beginner = (Button) findViewById(R.id.beginner);
        Button InterMediate = (Button) findViewById(R.id.Intermediate);
        Button Advanced = (Button) findViewById(R.id.Advanced);
        final TextView txtZoomIn = (TextView) findViewById(R.id.textView2);
        final Animation animZoomIn;
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.music);
        mp2.start();



        Beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_of_Bombs = (int) Math.round(0.1 * total_no_of_Cells);
                mp2.stop();
                Intent intent = new Intent(mainActivity.this, loadGridPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                txtZoomIn.setVisibility(View.VISIBLE);
                txtZoomIn.startAnimation(animZoomIn);

             }
           });
        InterMediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_of_Bombs = (int) Math.round(0.3 * total_no_of_Cells);
                mp2.stop();
                Intent intent = new Intent(mainActivity.this, loadGridPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                txtZoomIn.setVisibility(View.VISIBLE);
                txtZoomIn.startAnimation(animZoomIn);


            }
        });
        Advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                No_of_Bombs =(int) Math.round(0.5 * total_no_of_Cells);
                mp2.stop();
                Intent intent = new Intent(mainActivity.this, loadGridPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                txtZoomIn.setVisibility(View.VISIBLE);
                txtZoomIn.startAnimation(animZoomIn);


            }
        });

    }


}

