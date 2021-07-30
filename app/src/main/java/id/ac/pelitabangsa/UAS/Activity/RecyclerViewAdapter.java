package id.ac.pelitabangsa.ericofernandy_uas.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.pelitabangsa.ericofernandy_uas.R;
import id.ac.pelitabangsa.ericofernandy_uas.database.DBLogin;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<id.ac.pelitabangsa.ericofernandy_uas.Activity.DataFilter> dataList;
    private Context context;

    RecyclerViewAdapter(ArrayList<id.ac.pelitabangsa.ericofernandy_uas.Activity.DataFilter> dataList){
        this.dataList = dataList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Nama, Jurusan, Nim, JenisKelamin, Alamat, TanggalLahir;
        private ImageButton Overflow;

        ViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            Nama = itemView.findViewById(R.id.name);
            Jurusan = itemView.findViewById(R.id.jurusan);
            Overflow = itemView.findViewById(R.id.overflow);
            Nim = itemView.findViewById(R.id.NIM);
            JenisKelamin = itemView.findViewById(R.id.gender);
            Alamat = itemView.findViewById(R.id.alamat);
            TanggalLahir = itemView.findViewById(R.id.lahir);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final String Nama = dataList.get(position).getNama();
        final String Jurusan = dataList.get(position).getJurusan();
        final String NIM = dataList.get(position).getNIM();
        final String JenisKelamin = dataList.get(position).getJenisKelamin();
        final String Alamat = dataList.get(position).getAlamat();
        final String TanggalLahir = dataList.get(position).getTanggalLahir();
        holder.Nama.setText(Nama);
        holder.Jurusan.setText(Jurusan);
        holder.Nim.setText(NIM);
        holder.JenisKelamin.setText(JenisKelamin);
        holder.Alamat.setText(Alamat);
        holder.TanggalLahir.setText(TanggalLahir);


        holder.Overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:

                                DBMahasiswa getDatabase = new DBMahasiswa(view.getContext());
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                String selection = DBMahasiswa.MyColumns.NIM + " LIKE ?";

                                String[] selectionArgs = {NIM};
                                DeleteData.delete(DBMahasiswa.MyColumns.NamaTabel, selection, selectionArgs);


                                String position2 = String.valueOf(NIM.indexOf(NIM));
                                dataList.remove(position);
                                notifyItemRemoved(position);
                                if (position2 == null) {
                                    notifyItemRangeChanged(Integer.parseInt(position2), dataList.size());
                                }
                                break;

                            case R.id.update:
                                Intent dataForm = new Intent(view.getContext(), UpdateActivity.class);
                                dataForm.putExtra("SendNIM", NIM);
                                context.startActivity(dataForm);
                                ((Activity)context).finish();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    void setFilter(ArrayList<id.ac.pelitabangsa.ericofernandy_uas.Activity.DataFilter> filterList){
        dataList = new ArrayList<>();
        dataList.addAll(filterList);
        notifyDataSetChanged();
    }

}