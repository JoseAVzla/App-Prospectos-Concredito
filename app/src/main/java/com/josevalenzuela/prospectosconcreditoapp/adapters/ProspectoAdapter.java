package com.josevalenzuela.prospectosconcreditoapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProspectoAdapter extends RecyclerView.Adapter<ProspectoAdapter.ProspectosViewHolder> implements Filterable {
    private List<Prospecto> prospectosList;
    private List<Prospecto> prospectosListCompleta;
    private Context context;
    private OnItemClickListener clickListener;

    public ProspectoAdapter(List<Prospecto> prospectosList, Context context, OnItemClickListener clickListener) {
        this.prospectosList = prospectosList;
        this.prospectosListCompleta = new ArrayList<>(prospectosList);
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

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            List<Prospecto> listaFiltrada = new ArrayList<>();
            if (charString.isEmpty()) {
                listaFiltrada.addAll(prospectosListCompleta);
            } else {
                for (Prospecto prospecto : prospectosListCompleta) {
                    String nombre = prospecto.getNombre() + " " + prospecto.getPrimerApellido() + " " + prospecto.getSegundoApellido();
                    if (nombre.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        listaFiltrada.add(prospecto);
                    }
                }


            }

            FilterResults resultado = new FilterResults();
            resultado.values = listaFiltrada;
            return resultado;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            prospectosList.clear();
            prospectosList.addAll((Collection<? extends Prospecto>) filterResults.values);
            notifyDataSetChanged();
        }
    };


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