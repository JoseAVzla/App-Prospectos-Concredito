package com.josevalenzuela.prospectosconcreditoapp.View;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.adapters.DocumentosAdapter;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

public class InfoProspectoFragment extends Fragment {

    private TextView nombreTxtView, direccionTxtView, telefonoTxtView, estatusTxtView, rfcTextView, observTextView;
    private RecyclerView recyclerViewDocs;
    private CardView prospectoInfoCard, observacionesInfoCard;
    public InfoProspectoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_info_prospecto, container, false);

        nombreTxtView = fragmentView.findViewById(R.id.prospectoNombreInfoTextId);
        direccionTxtView = fragmentView.findViewById(R.id.direccionInfoTextId);
        telefonoTxtView = fragmentView.findViewById(R.id.telefonoInfoTextId);
        rfcTextView = fragmentView.findViewById(R.id.rfcInfoTextId);
        estatusTxtView = fragmentView.findViewById(R.id.estatusInfoTxtView);
        observTextView = fragmentView.findViewById(R.id.observacionesInfoTxtView);
        recyclerViewDocs = fragmentView.findViewById(R.id.recyclerViewInfoDocs);
        observacionesInfoCard = fragmentView.findViewById(R.id.observacionesInfoCardViewId);
        prospectoInfoCard = fragmentView.findViewById(R.id.infoProspectoCardViewId);


        assert getArguments() != null;
        setInfo(getArguments());

        getActivity().setTitle("Información prospecto");
        return fragmentView;
    }

    private void setInfo(Bundle bundle){


        Prospecto prospecto = (Prospecto) bundle.getSerializable("prospectoSeleccionado");
        assert prospecto != null;

        if (prospecto.getEstatus().equals("RECHAZADO")) {
            prospectoInfoCard.setBackgroundColor(Color.parseColor("#0DD32F2F"));
            observacionesInfoCard.setBackgroundColor(Color.parseColor("#0DD32F2F"));
        } else {
            prospectoInfoCard.setBackgroundColor(Color.parseColor("#0D00701A"));
            observacionesInfoCard.setBackgroundColor(Color.parseColor("#0D00701A"));
        }

        String nombre = prospecto.getNombre() + " " + prospecto.getPrimerApellido();
        nombreTxtView.setText(nombre);
        String direccion = prospecto.getCalle() + ", " + prospecto.getColonia() + ", CP: " + prospecto.getCodigoPostal();
        direccionTxtView.setText(direccion);
        telefonoTxtView.setText(prospecto.getTelefono());
        rfcTextView.setText(prospecto.getRfc());
        estatusTxtView.setText(prospecto.getEstatus());
        observTextView.setText(prospecto.getObservaciones());



        DocumentosAdapter adapter = new DocumentosAdapter( prospecto.getDocumetosUrl(), getContext());
        recyclerViewDocs.setAdapter(adapter);
        recyclerViewDocs.setLayoutManager(new LinearLayoutManager(getContext()));
        bundle.clear();

    }
}