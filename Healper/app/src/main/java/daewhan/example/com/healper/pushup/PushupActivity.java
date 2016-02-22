package daewhan.example.com.healper.pushup;

import android.content.Intent;
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

public class PushupActivity extends AppCompatActivity implements SensorEventListener{

    // 센서 관련 객체
    SensorManager m_sensor_manager;
    Sensor m_sensor;

    int m_check_count = 0;
    TextView m_check_view;
    Button bt_rest;
    Button bt_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushup);

        // 출력용 텍스트뷰를 얻는다.
        m_check_view = (TextView) findViewById(R.id.check_tv);
        bt_rest = (Button) findViewById(R.id.bt_rest);
        bt_finish = (Button) findViewById(R.id.bt_finish_m);

        // 시스템서비스로부터 SensorManager 객체를 얻는다.
        m_sensor_manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // SensorManager 를 이용해서 근접 센서 객체를 얻는다.
        m_sensor = m_sensor_manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        bt_rest.setOnClickListener(rClickListener);
        //bt_finish.setOnClickListener(fClickListener);
    }


    // 해당 액티비티가 시작되면 근접 데이터를 얻을 수 있도록 리스너를 등록한다.
    protected void onStart() {
        super.onStart();

        m_check_count = 0;
        // 센서 값을 이 컨텍스트에서 받아볼 수 있도록 리스너를 등록한다.
        m_sensor_manager.registerListener(this, m_sensor, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onStop() {
        super.onStop();
        // 센서 값이 필요하지 않는 시점에 리스너를 해제해준다.
        m_sensor_manager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // 정확도가 낮은 측정값인 경우
        if(event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            // 몇몇 기기의 경우 accuracy 가 SENSOR_STATUS_UNRELIABLE 값을
            // 가져서 측정값을 사용하지 못하는 경우가 있기 때문에 임의로 return ;을 막는다.
            // return ;
        }
        // 센서값을 측정한 센서의 종류가 근접센서인 경우
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if(event.values[0] == 0)
                m_check_count++;

            String str;

            //해당 센서가 반환해주는 최대값과 메소드 호출 횟수를 출력한다.
            str = " " + m_check_count;
            m_check_view.setText(str);
        }
    }

    // RestActivity로 전환
    Button.OnClickListener rClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent rIntent = new Intent(PushupActivity.this, RestActivity.class);
            startActivity(rIntent);
        }
    };
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
