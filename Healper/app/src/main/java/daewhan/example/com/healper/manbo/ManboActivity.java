package daewhan.example.com.healper.manbo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import daewhan.example.com.healper.R;

public class ManboActivity extends AppCompatActivity implements SensorEventListener {

    public static int cnt = 0;

    private TextView tView;
    private Button resetBtn;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manbo);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tView = (TextView) findViewById(R.id.cntView);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        tView.setText("10000/" + cnt);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    tView.setText("10000/" + (++cnt));
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }

    //클릭이벤트 처리(리셋 버튼)
    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.resetBtn :
                cnt = 0;
                tView.setText("10000/" + cnt);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
