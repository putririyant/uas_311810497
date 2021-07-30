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

public class UpdateActivity extends AppCompatActivity {

    private DBMahasiswa MyDatabase;
    private EditText NewNIM, NewNama, NewTanggalLahir, NewAlamat;
    private Spinner NewJurusan;
    private RadioButton NewMALE, NewFAMALE;
    private String getNewNIM, getNewNama, getNewJurusan, getNewJenisKelamin, getNewAlamat,
            getNewTanggalLahir;
    private Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        MyDatabase = new DBMahasiswa(getBaseContext());
        NewNIM = findViewById(R.id.new_nim);
        NewNama = findViewById(R.id.new_nama);
        NewJurusan = findViewById(R.id.new_jurusan);
        NewTanggalLahir = findViewById(R.id.new_date);
        NewMALE = findViewById(R.id.new_male);
        NewFAMALE = findViewById(R.id.new_famale);
        NewAlamat = findViewById(R.id.new_alamat);
        Update = findViewById(R.id.new_data);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdateData();
                startActivity(new Intent(UpdateActivity.this, ViewData.class));
                finish();
            }
        });
    }

    private void setUpdateData(){
        getNewNIM = NewNIM.getText().toString();
        getNewNama = NewNama.getText().toString();
        getNewJurusan = NewJurusan.getSelectedItem().toString();
        if(NewMALE.isChecked()){
            getNewJenisKelamin = NewMALE.getText().toString();
        }else if (NewFAMALE.isChecked()){
            getNewJenisKelamin = NewFAMALE.getText().toString();
        }
        getNewTanggalLahir = NewTanggalLahir.getText().toString();
        getNewAlamat = NewAlamat.getText().toString();

        //Menerima Data NIM yang telah dipilih Oleh User untuk diposes
        String GetNIM = getIntent().getExtras().getString("SendNIM");

        SQLiteDatabase database = MyDatabase.getReadableDatabase();

        //Memasukan Data baru pada 3 kolom (NIM, Nama dan Jurusan)
        ContentValues values = new ContentValues();
        values.put(DBMahasiswa.MyColumns.Nama, getNewNama);
        values.put(DBMahasiswa.MyColumns.Jurusan, getNewJurusan);
        values.put(DBMahasiswa.MyColumns.NIM, getNewNIM);
        values.put(DBMahasiswa.MyColumns.JenisKelamin, getNewJenisKelamin);
        values.put(DBMahasiswa.MyColumns.Alamat, getNewAlamat);
        values.put(DBMahasiswa.MyColumns.TanggalLahir, getNewTanggalLahir);

        //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan NIM
        String selection = DBMahasiswa.MyColumns.NIM + " LIKE ?";
        String[] selectionArgs = {GetNIM};
        database.update(DBMahasiswa.MyColumns.NamaTabel, values, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();
    }
}