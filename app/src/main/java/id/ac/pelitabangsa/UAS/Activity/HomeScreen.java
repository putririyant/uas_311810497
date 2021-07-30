package id.ac.pelitabangsa.ericofernandy_uas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.pelitabangsa.ericofernandy_uas.R;
import id.ac.pelitabangsa.ericofernandy_uas.database.DBLogin;


public class HomeScreen extends AppCompatActivity {
    Button btnStart, btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        Button btnStart = (Button) findViewById(R.id.btn_start);
        Button btnSign = (Button) findViewById(R.id.btn_signin);

        btnSign .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeScreen.this,SignActivity.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,RegisterActvity.class);
                startActivity(intent);
            }
        });

    }
}