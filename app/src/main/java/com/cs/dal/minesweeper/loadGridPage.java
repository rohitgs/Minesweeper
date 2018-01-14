package com.cs.dal.minesweeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by rohitgs on 10/15/17.
 */

public  class loadGridPage extends Activity {
    public static long millisec = 0L, StartTime = 0L;
    public static long TimeBuff = 0L, UpdateTime = 0L;
    public static int sec, Minutes, MilliSeconds;
    public static Handler handler = new Handler();
    public static gamePlay mygameplay = new gamePlay();
    public static TextView Stopwatch;
    public TextView GreetMessage;
    public static boolean stopStopwatch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        mygameplay.createNewGrid(this);
        Stopwatch = (TextView)findViewById(R.id.stopwatch);
        Button reset = (Button) findViewById(R.id.Reset);
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);


        /**
         * Reset Onclick Option
         */
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Context c = loadGridPage.this;
                mygameplay.createNewGrid(c);

                /**
                 * Resetting the stopwatch
                 */
                millisec = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                sec = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                stopStopwatch = false;
             }
         });
    }


    /**
     * Runnable method used for the clock
     */
    public static  Runnable runnable = new Runnable() {

        public void run() {

            millisec = SystemClock.uptimeMillis() - StartTime;
            Minutes = ((int) ((TimeBuff + millisec) / 1000))/ 60;
            sec = ((int) ((TimeBuff + millisec) / 1000)) % 60;

            Stopwatch.setText("" + Minutes + ":"
                    + String.format("%02d", sec));

            handler.postDelayed(this, 0);
        }

    };
}
