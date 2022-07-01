package com.example.halepor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.halepor.Detail.DetailPratul;
import com.example.halepor.R;
import com.example.halepor.model.Datapratul;

import java.util.ArrayList;

public class PratulAdapter extends RecyclerView.Adapter<PratulAdapter.PratulViewHolder> {
    private Context context;

    private ArrayList<Datapratul> dataPratul;

    public PratulAdapter(Context context, ArrayList<Datapratul> dataPratul) {
        this.dataPratul = dataPratul;
        this.context = context;
    }

    @NonNull
    @Override
    public PratulAdapter.PratulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_pratul,parent, false);
        return new PratulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PratulAdapter.PratulViewHolder holder, int position) {
        Datapratul model = dataPratul.get(position);
//        Glide.with(holder.itemView.getContext())
//                .load(dataPratul.getClass())
//                .apply(new RequestOptions().override(55,55))
//                .into(holder.img_pratul);
        holder.txt_petugas.setText(model.getPetugas());
        holder.txt_idpel_pratul.setText(model.getIdpel_pratul());
        holder.txt_norbm_pratul.setText(model.getNorbm_pratul());
        holder.txt_namapel_pratul.setText(model.getNamapel_pratul());
        holder.txt_alamat_pratul.setText(model.getAlamat_pratul());
        holder.txt_trfdaya_pratul.setText(model.getTrfdaya_pratul());
        holder.txt_rptag_pratul.setText(model.getRptag_pratul());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, model.getIdpel_pratul(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailPratul.class);
//                intent.putExtra(DetailPratul.ITEM_EXTRA, dataPratul);
                intent.putExtra("petugas", model.getPetugas());
                intent.putExtra("idpel_pratul", model.getIdpel_pratul());
                intent.putExtra("norbm", model.getNorbm_pratul());
                intent.putExtra("namapelpelanggan", model.getNamapel_pratul());
                intent.putExtra("alamatpelanggan", model.getAlamat_pratul());
                intent.putExtra("tarifdaya", model.getTrfdaya_pratul());
                intent.putExtra("rptagihan", model.getRptag_pratul());
//                intent.putExtra("foto_pratul", model.getFoto_pratul());
                context.startActivity(intent);
            }
        });


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, model.getIdpel_pratul(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, DetailPratul.class);
//                intent.putExtra("petugas", model.getPetugas());
//                intent.putExtra("Idpel_pratul", model.getIdpel_pratul());
//                intent.putExtra("rbm", model.getNorbm_pratul());
//                intent.putExtra("namapel_pratul", model.getNamapel_pratul());
//                intent.putExtra("alamat_pratul", model.getAlamat_pratul());
//                intent.putExtra("trfdaya_pratul", model.getTrfdaya_pratul());
//                intent.putExtra("rptag_pratul", model.getRptag_pratul());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return dataPratul.size();
    }

    public class PratulViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_idpel_pratul, txt_namapel_pratul
                , txt_alamat_pratul,txt_norbm_pratul,txt_petugas,txt_trfdaya_pratul,txt_rptag_pratul;
//        public CardView cv_pratul;
        ImageView img_pratul;

        public PratulViewHolder(@NonNull View itemView) {
            super(itemView);
            img_pratul = itemView.findViewById(R.id.img_pratul);
            txt_petugas = itemView.findViewById(R.id.txt_Petugas);
            txt_idpel_pratul = itemView.findViewById(R.id.txt_idpel_pratul);
            txt_norbm_pratul =  itemView.findViewById(R.id.txt_norbm_pratul);
            txt_namapel_pratul = itemView.findViewById(R.id.txt_namapel_pratul);
            txt_alamat_pratul = itemView.findViewById(R.id.txt_alamat_pratul);
            txt_trfdaya_pratul = itemView.findViewById(R.id.txt_trfpratul);
            txt_rptag_pratul = itemView.findViewById(R.id.txt_rptagPratul);
//            cv_pratul = itemView.findViewById(R.id.cv_pratul);


        }
    }
//    private static String [] pratuldetail = {
//
//    };
//    static ArrayList<Datapratul> getListData(){
//        ArrayList<Datapratul> list = new ArrayList<>();
//        for (int position = 0; position <Datapratul.length; position++){
//            Datapratul datapratul = new Datapratul();
//            datapratul.setPetugas(datapratul.getPetugas());
//            datapratul.setDetail(datapratul.getDetail());
//            datapratul.setFoto_pratul(datapratul.getFoto_pratul());

}


