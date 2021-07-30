package id.ac.pelitabangsa.ericofernandy_uas.Activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.pelitabangsa.ericofernandy_uas.R;

public class ViewData extends AppCompatActivity {
    private DBMahasiswa MyDatabase;

    private RecyclerViewAdapter adapter;
    private ArrayList<DataFilter> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        dataList = new ArrayList<>();
        MyDatabase = new DBMahasiswa(getBaseContext());
        RecyclerView recyclerView = findViewById(R.id.recycler);
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(dataList);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
    }


    @SuppressLint("Recycle")
    protected void getData(){

        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBMahasiswa.MyColumns.NamaTabel,null);




        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);


            dataList.add(new DataFilter(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),
                    cursor.getString(5)));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {

                nextText = nextText.toLowerCase();
                ArrayList<DataFilter> dataFilter = new ArrayList<>();
                for(DataFilter data : dataList){
                    String nama = data.getNama().toLowerCase();
                    if(nama.contains(nextText)){
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                return true;
            }
        });
        return true;
    }
}