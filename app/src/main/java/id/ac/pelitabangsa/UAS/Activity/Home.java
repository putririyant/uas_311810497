package id.ac.pelitabangsa.ericofernandy_uas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import id.ac.pelitabangsa.ericofernandy_uas.R;
import id.ac.pelitabangsa.ericofernandy_uas.database.DBLogin;


public class Home extends AppCompatActivity {
    private ImageButton Overflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    Overflow = findViewById(R.id.menu);
    Overflow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    });
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popup_menu_home);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tambahManual:;
                        startActivity(new Intent(Home.this, MainActivity.class ));
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}