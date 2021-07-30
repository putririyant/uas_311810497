package id.ac.pelitabangsa.ericofernandy_uas.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.pelitabangsa.ericofernandy_uas.R;
import id.ac.pelitabangsa.ericofernandy_uas.database.DBLogin;

public class MainActivity extends AppCompatActivity {

    private EditText NIM, Nama, TanggalLahir, Alamat;
    private Spinner Jurusan;
    private RadioButton MALE, FAMALE;

    private String setNIM, setNama, setTanggalLahir, setAlamat, setJurusan, setJenisKelamin;
    private DBMahasiswa dbMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button simpan = findViewById(R.id.save);
        NIM = findViewById(R.id.nim);
        Nama = findViewById(R.id.nama);
        TanggalLahir = findViewById(R.id.date);
        MALE = findViewById(R.id.male);
        FAMALE = findViewById(R.id.famale);
        Jurusan = findViewById(R.id.jurusan);
        Alamat = findViewById(R.id.alamat);

        dbMahasiswa = new DBMahasiswa(getBaseContext());



        Button viewData = findViewById(R.id.readData);
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewData.class));
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });
    }

    private void setData(){
        setNIM = NIM.getText().toString();
        setNama = Nama.getText().toString();
        setJurusan = Jurusan.getSelectedItem().toString();
        if(MALE.isChecked()){
            setJenisKelamin = MALE.getText().toString();
        }else if (FAMALE.isChecked()){
            setJenisKelamin = FAMALE.getText().toString();
        }
        setTanggalLahir = TanggalLahir.getText().toString();
        setAlamat = Alamat.getText().toString();
    }

    private void saveData(){
        SQLiteDatabase create = dbMahasiswa.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBMahasiswa.MyColumns.NIM, setNIM);
        values.put(DBMahasiswa.MyColumns.Nama, setNama);
        values.put(DBMahasiswa.MyColumns.Jurusan, setJurusan);
        values.put(DBMahasiswa.MyColumns.JenisKelamin, setJenisKelamin);
        values.put(DBMahasiswa.MyColumns.TanggalLahir, setTanggalLahir);
        values.put(DBMahasiswa.MyColumns.Alamat, setAlamat);

        create.insert(DBMahasiswa.MyColumns.NamaTabel, null, values);
    }

    private void clearData(){
        NIM.setText("");
        Nama.setText("");
        TanggalLahir.setText("");
        Alamat.setText("");
    }
}