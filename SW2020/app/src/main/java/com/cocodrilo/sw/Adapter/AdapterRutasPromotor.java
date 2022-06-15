package com.cocodrilo.sw.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cocodrilo.sw.Constructs.Rutas;
import com.cocodrilo.sw.HomeSW;
import com.cocodrilo.sw.R;


import java.util.List;



public class AdapterRutasPromotor extends RecyclerView.Adapter<AdapterRutasPromotor.ViewHolder> {
    private List<Rutas> listhomeRutas;
    private Context mContext;
    private HomeSW mListener;

    public interface OnItemClickListener{
        void onItemClick (int position);
    }
    public void setOnItemClickListener(HomeSW listener){
        mListener=listener;
    }


    public AdapterRutasPromotor(Context context, List<Rutas> listhomeRutas) {
        this.listhomeRutas = listhomeRutas;
        this.mContext = context;
    }


    @NonNull
    @Override
    public AdapterRutasPromotor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_mall, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull AdapterRutasPromotor.ViewHolder holder, int position) {
        final Rutas lista_homeRutas=listhomeRutas.get(position);
        holder.tienda.setText(lista_homeRutas.getMall());
        holder.formato.setText(lista_homeRutas.getFormato());
        holder.orden.setText(lista_homeRutas.getOrden());


        Glide.with(mContext)
                .load(lista_homeRutas.getImage())
                .into(holder.imageView);


       /* holder.cardhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mContext, Modules.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(i);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listhomeRutas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tienda;
        TextView formato;
        TextView orden;
        ImageView imageView;
        CardView cardhome;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tienda = itemView.findViewById(R.id.textViewTienda);
            formato = itemView.findViewById(R.id.textViewFormato);
            orden = itemView.findViewById(R.id.textViewOrden1);
            imageView = itemView.findViewById(R.id.imageViewR);
            cardhome=itemView.findViewById(R.id.list_rutas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }

                    }
                }
            });

        }
    }
}