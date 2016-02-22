package daewhan.example.com.healper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import daewhan.example.com.healper.manbo.ManboActivity;
import daewhan.example.com.healper.pushup.PushupActivity;

public class MainActivity extends AppCompatActivity {

    private Button bt_pushup;
    private Button bt_manbo;
    private Button bt_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt_pushup = (Button)findViewById(R.id.bt_pushup);
        bt_pushup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PushupActivity.class);
                startActivity(intent);
            }
        });

        bt_manbo = (Button)findViewById(R.id.bt_manbo);
        bt_manbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManboActivity.class);
                startActivity(intent);
            }
        });


        bt_bmi =(Button)findViewById(R.id.bt_bmi);
        bt_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BmiActivity.class);
                startActivity(intent);
            }
        });


    }
}
