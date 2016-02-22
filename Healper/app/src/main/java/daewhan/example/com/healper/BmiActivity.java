package daewhan.example.com.healper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    NumberPicker np_height;
    NumberPicker np_weight;
    Button bt_result;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        tv_result = (TextView)findViewById(R.id.tv_result);
        np_height = (NumberPicker)findViewById(R.id.np_height);
        np_weight = (NumberPicker)findViewById(R.id.np_weight);

        np_height.setMinValue(140);
        np_height.setMaxValue(200);
        np_weight.setMinValue(40);
        np_weight.setMaxValue(130);

        bt_result = (Button)findViewById(R.id.bt_result);
        bt_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float height = np_height.getValue();
                float weight = np_weight.getValue();
                float bmi_value = weight / (height/100 * height/100);

                Log.v("태그", "몸무게" + weight);
                Log.v("태그", "키" + height);
                Log.v("태그", "bmi " + bmi_value);

                if(bmi_value < 18.5) {
                    tv_result.setText("저체중");
                }else if(bmi_value <= 24.9){
                    tv_result.setText("정상");
                }else if(bmi_value <= 29.9){
                    tv_result.setText("과체중");
                }else if(bmi_value >= 30){
                    tv_result.setText("비만!!");
                }

            }
        });
    }
}
