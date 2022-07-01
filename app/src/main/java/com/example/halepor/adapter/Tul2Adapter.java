package com.example.halepor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.halepor.R;
import com.example.halepor.model.Datatul2;

import java.util.ArrayList;

public class Tul2Adapter extends RecyclerView.Adapter<Tul2Adapter.Tul2ViewHolder> {
    private Context context;
    private ArrayList<Datatul2> dataTul2;

    public Tul2Adapter(Context context, ArrayList<Datatul2> dataTul2){
        this.dataTul2 = dataTul2;
        this.context =context;
    }

    @NonNull
    @Override
    public Tul2Adapter.Tul2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_cardview_tul2 ,parent,false);
        return new Tul2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tul2Adapter.Tul2ViewHolder holder,int position){
        Datatul2 model = dataTul2.get(position);
        holder.txt_idpel_tul2.setText(model.getIdpel_tul2());
        holder.txt_namapel_tul2.setText(model.getNamapel_tul2());
        holder.txt_alamat_tul2.setText(model.getAlamat_tul2());
    }

    @Override
    public int getItemCount(){ return dataTul2.size();}

    public class Tul2ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txt_idpel_tul2,txt_namapel_tul2,txt_alamat_tul2;


        ItemClickListener itemClickListener;
        public Tul2ViewHolder(@NonNull View itemView){
            super(itemView);

            txt_idpel_tul2 = itemView.findViewById(R.id.txt_idpel_tul2);
            txt_namapel_tul2 = itemView.findViewById(R.id.txt_namapel_tul2);
            txt_alamat_tul2 = itemView.findViewById(R.id.txt_alamat_tul2);

            txt_idpel_tul2.setOnClickListener(this);
        }
        public void setItemClickListener(Tul2Adapter.ItemClickListener ic){
            this.itemClickListener=ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }
}
