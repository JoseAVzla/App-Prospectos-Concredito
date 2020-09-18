package com.josevalenzuela.prospectosconcreditoapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

public class InfoProspectoFragment extends Fragment {

    private TextView nombreTxtView, direccionTxtView, telefonoTxtView, estatusTxtView, rfcTextView, observTextView;


    public InfoProspectoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_info_prospecto, container, false);

        nombreTxtView = fragmentView.findViewById(R.id.infoNombreIdTxtView);
        direccionTxtView = fragmentView.findViewById(R.id.infoDireccionIdTxtView);
        telefonoTxtView = fragmentView.findViewById(R.id.infoTelefonoIdTxtView);
        rfcTextView = fragmentView.findViewById(R.id.infoRfcIdTxtView);
        estatusTxtView = fragmentView.findViewById(R.id.infoEstatusIdTxtView);
        observTextView = fragmentView.findViewById(R.id.infoObservIdTxtView);
        assert getArguments() != null;
        setInfo(getArguments());

        getActivity().setTitle("Información prospecto");
        return fragmentView;
    }

    private void setInfo(Bundle bundle){
        Prospecto prospecto = (Prospecto) bundle.getSerializable("prospectoSeleccionado");
        assert prospecto != null;
        String nombre = prospecto.getNombre() + " " + prospecto.getPrimerApellido();
        nombreTxtView.setText(nombre);
        String direccion = prospecto.getCalle() + ", " + prospecto.getColonia() + ", CP: " + prospecto.getCodigoPostal();
        direccionTxtView.setText(direccion);
        telefonoTxtView.setText(prospecto.getTelefono());
        rfcTextView.setText(prospecto.getRfc());
        estatusTxtView.setText(prospecto.getEstatus());
        observTextView.setText(prospecto.getObservaciones());
        bundle.clear();

    }
}