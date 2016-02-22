package daewhan.example.com.healper.pushup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import daewhan.example.com.healper.R;

public class RestActivity extends AppCompatActivity {


    Button bt_start;
    Button bt_pause;
    Button bt_workout;
    Button bt_finish;

    TextView tv_time;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        tv_time = (TextView) findViewById(R.id.tv_time);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_pause = (Button) findViewById(R.id.bt_pause);

        bt_workout = (Button) findViewById(R.id.bt_workout);
        bt_finish = (Button) findViewById(R.id.bt_finish_r);

        // StopWatch 시작
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            }
        });

        // StopWatch 멈춤
        bt_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

        // 이전화면으로 전환
        bt_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // FinishActivity로 전환
        // bt_finish.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent fIntent = new Intent(RestActivity.this, FinishActivity.class);
        //        startActivity(fIntent);
        //    }
        //});
    }

    // Thread로 StopWatch 구현
    private Runnable updateTimerThread = new Runnable() {
        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            tv_time.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("0%3d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };

}
