package com.josevalenzuela.prospectosconcreditoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class ProspectoAdapter extends RecyclerView.Adapter<ProspectoAdapter.ProspectosViewHolder> {
    private List<Prospecto> prospectosList;
    private Context context;
    private OnItemClickListener clickListener;

    public ProspectoAdapter(List<Prospecto> prospectosList, Context context, OnItemClickListener clickListener) {
        this.prospectosList = prospectosList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProspectosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prospecto_list_item, parent, false);
        ProspectosViewHolder prospectosViewHolder = new ProspectosViewHolder(view);
        return prospectosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProspectosViewHolder holder, int position) {
        Prospecto prospecto = prospectosList.get(position);
        String nombre = prospecto.getNombre() + " " + prospecto.getPrimerApellido() + " " + prospecto.getSegundoApellido();
        holder.nombreTxtview.setText(nombre);

        //Agregando diferenciador de color para cada estado del prospecto.
        holder.estatusTxtView.setText(prospecto.getEstatus());
        if (prospecto.getEstatus().equals("RECHAZADO")) {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#0DD32F2F"));
        } else if (prospecto.getEstatus().equals("AUTORIZADO")) {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#0D00701A"));
        } else {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#1A0097A7"));
        }

        //Seleccionando el prospecto para visualizar su informacion completa.
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return prospectosList.size();
    }


    public static class ProspectosViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreTxtview, estatusTxtView;
        private CardView cardView;
        private LinearLayout linearLayout;
        private OnItemClickListener clickListener;
        public ProspectosViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nombreTxtview = itemView.findViewById(R.id.idNombreTxtView);
            this.estatusTxtView = itemView.findViewById(R.id.estatusIdTxtView);
            this.cardView = itemView.findViewById(R.id.cardViewId);
            this.linearLayout = itemView.findViewById(R.id.layoutStatusColorId);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}