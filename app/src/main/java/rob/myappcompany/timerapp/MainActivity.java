package rob.myappcompany.timerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    float x1, x2, y1, y2;
    String tag = MainActivity.class.getSimpleName();

    private Button stopstartButton;
    private Timer timer;
    private TimerTask timerTask;
    Double time = 0.0;
    private boolean timerStarted = false;
    boolean returnBool = false;

    TextView hoursTextView;
    TextView minutesTextView;
    TextView secondsTextView;
    View view;
    Intent toTimerList;
    List<timeTeil>  getList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopstartButton = findViewById(R.id.startStopBtn);
        hoursTextView = findViewById(R.id.hoursId);
        minutesTextView = findViewById(R.id.minuteId);
        secondsTextView = findViewById(R.id.secondId);

        timer = new Timer();

        view = this.getWindow().getDecorView();
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.appleBlack));


    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                if (x1 < x2){
                    Intent intent = new Intent(this, TimerListActivity.class);
                    startActivity(intent);
                }
        }
        return super.onTouchEvent(event);
    }

    public void startStopTapped(View view) {
        if (timerStarted == false){
            timerStarted = true;

            setButtonUI("STOP", R.color.red);

            startTimer();
        }
        else {
            timerStarted = false;
            setButtonUI("START", R.color.green);

            //Stop timerTask
            timerTask.cancel();
        }
    }

    private void setButtonUI(String stop, int color) {
        stopstartButton.setText(stop);
        stopstartButton.setTextColor(ContextCompat.getColor(this, color));
    }



    public void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //increment time every second
                        time++;

                        hoursTextView.setText(getTimerText().get(0).getHours());
                        minutesTextView.setText(getTimerText().get(0).getMinutes());
                        secondsTextView.setText(getTimerText().get(0).getSeconds());

                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private List<timeTeil> getTimerText() {

        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) / 3600);
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) % 3600) % 60;


        return getTime(seconds, minutes, hours);
    }



    public List<timeTeil> getTime(int seconds, int minutes, int hours){
        getList = new ArrayList<>();
        getList.add(new timeTeil(String.format("%02d", seconds),String.format("%02d", minutes),String.format("%02d", hours)));
        return getList;
    }

    public void resetDialogTapped(View view) {
        AlertDialog.Builder saveTimeDialog = new AlertDialog.Builder(this);
        saveTimeDialog.setTitle("Save Time");
        saveTimeDialog.setMessage("Do you want to reset and save Time ?");
        saveTimeDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                toTimerList = new Intent(MainActivity.this, TimerListActivity.class);
                String secStr = getList.get(0).getSeconds();
                String minStr = getList.get(0).getMinutes();
                String hourStr = getList.get(0).getHours();
                String formatTimer = String.format("%s : %s : %s", hourStr,minStr,secStr);

                if (!formatTimer.equals(null) ){
                    toTimerList.putExtra("timeValue", formatTimer);
                    resetTimer();
                    startActivity(toTimerList);
                }


            }
        });
        saveTimeDialog.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Nothing
            }
        });
        saveTimeDialog.show();
    }

    public void resetTimer(){
        setButtonUI("START", R.color.green);
        //time become of 0.0
        time = 0.0;
        timerStarted = false;
        //timerText.setText(formatTime(0,0,0));
        secondsTextView.setText(getTime(0,0,0).get(0).getSeconds());
        minutesTextView.setText(getTime(0,0,0).get(0).getMinutes());
        hoursTextView.setText(getTime(0,0,0).get(0).getHours());


    }


}