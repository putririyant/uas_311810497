package id.ac.pelitabangsa.ericofernandy_uas.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBMahasiswa extends SQLiteOpenHelper{


    static abstract class MyColumns implements BaseColumns{
        static final String NamaTabel = "Mahasiswa";
        static final String NIM = "NIM";
        static final String Nama = "Nama_Mahasiswa";
        static final String Jurusan = "Jurusan";
        static final String JenisKelamin = "Jenis_Kelamin";
        static final String TanggalLahir = "Tanggal_Lahir";
        static final String Alamat = "Alamat";
    }

    private static final String NamaDatabse = "Satria.db";
    private static final int VersiDatabase = 14;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ MyColumns.NamaTabel+
            "("+ MyColumns.NIM+" TEXT PRIMARY KEY, "+ MyColumns.Nama+" TEXT NOT NULL, "+ MyColumns.Jurusan+
            " TEXT NOT NULL, "+ MyColumns.JenisKelamin+" TEXT NOT NULL, "+ MyColumns.TanggalLahir+
            " TEXT NOT NULL, "+ MyColumns.Alamat+" TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ MyColumns.NamaTabel;

    public DBMahasiswa(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}