package id.co.imastudio.realmapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gilang Ramadhan on 24/12/2016.
 */

public class AdapterSiswa extends RecyclerView.Adapter<AdapterSiswa.ViewHolder>{

    private final OnItemClickListener listener;
    private ArrayList<ModelSiswa> siswas;


    public AdapterSiswa(ArrayList<ModelSiswa> siswas, OnItemClickListener listener){
        this.siswas = siswas;
        this.listener = listener;
    }

    @Override
    public AdapterSiswa.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType    ){
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new
                ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder
            (AdapterSiswa.ViewHolder holder, final int position) {
        holder.click(siswas.get(position), listener);
        holder.id.setText(String.valueOf(siswas.get(position).getId()));
        holder.nama.setText(siswas.get(position).getNama());
        holder.alamat.setText(siswas.get(position).getAlamat());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: "+ position);
//            }
//        });

    }
    public interface  OnItemClickListener{
        void onClick(ModelSiswa item);
    }
    @Override
    public int getItemCount() {
        return siswas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, nama, alamat;
        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.TVid);
            nama = (TextView) itemView.findViewById(R.id.TVnama);
            alamat = (TextView) itemView.findViewById(R.id.TValamat);

        }

        public void click(final ModelSiswa modelSiswa, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(modelSiswa);
                }
            });
        }
    }

}
