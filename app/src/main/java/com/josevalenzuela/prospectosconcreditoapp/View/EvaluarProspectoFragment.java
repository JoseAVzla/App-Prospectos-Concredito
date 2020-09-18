package com.josevalenzuela.prospectosconcreditoapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.josevalenzuela.prospectosconcreditoapp.Presenter.EvaluarProspectoPresenter;
import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.adapters.DocumentosAdapter;
import com.josevalenzuela.prospectosconcreditoapp.contracts.EvaluarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class EvaluarProspectoFragment extends Fragment implements EvaluarProspectoContract.View, AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerViewDocumentos;
    private TextInputEditText observacionesEditTxt;
    private TextView nombreTxtView, direccionTxtView, telefonoTxtView, rfcTextView;
    private String estatusSeleccionado = "";
    private TextInputLayout observacionesLayout;
    private Spinner spinner;
    private FloatingActionButton evaluarBtn;

    private EvaluarProspectoContract.Presenter presenter;
    private Prospecto prospectoEvaluado;
    private Fragment listadoFragment;


    public EvaluarProspectoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluar_prospecto, container, false);

        getActivity().setTitle("Evaluar prospecto");
        //Componentes para mostrar información del prospecto a evaluar
        nombreTxtView = view.findViewById(R.id.evaluarProspectoNombreTextId);
        direccionTxtView = view.findViewById(R.id.evalaurDireccionTextId);
        telefonoTxtView = view.findViewById(R.id.evaluarTelefonoTextId);
        rfcTextView = view.findViewById(R.id.evaluarRfcTextId);
        recyclerViewDocumentos = view.findViewById(R.id.recyclerViewDocumentos);
        observacionesEditTxt = view.findViewById(R.id.observacionesEditText);
        observacionesLayout = view.findViewById(R.id.observacionesTextLayout);
        evaluarBtn = view.findViewById(R.id.evaluarBtnId);
        listadoFragment = new ListadoProspectosFragment();

        presenter =  new EvaluarProspectoPresenter(this);


        //Sipnner para seleccionar el estatus del prospecto a evaluar
        spinner = (Spinner) view.findViewById(R.id.estatusSpinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.estatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        assert getArguments() != null;
        setInfoProspecto(getArguments());

        evaluarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter.validarFormulario(estatusSeleccionado, observacionesEditTxt.getText().toString())){
                    presenter.updateProspecto(prospectoEvaluado, estatusSeleccionado, observacionesEditTxt.getText().toString());
                }
            }
        });


        return view;
    }

    private void setInfoProspecto(Bundle bundle){
        prospectoEvaluado = (Prospecto) bundle.getSerializable("prospectoSeleccionado");
        assert prospectoEvaluado != null;
        String nombre = prospectoEvaluado.getNombre() + " " + prospectoEvaluado.getPrimerApellido();
        nombreTxtView.setText(nombre);
        String direccion = prospectoEvaluado.getCalle() + ", " + prospectoEvaluado.getColonia() + ", CP: " + prospectoEvaluado.getCodigoPostal();
        direccionTxtView.setText(direccion);
        telefonoTxtView.setText(prospectoEvaluado.getTelefono());
        rfcTextView.setText(prospectoEvaluado.getRfc());
        mostrarDocumentos(prospectoEvaluado.getDocumetosUrl());
    }

    private void mostrarDocumentos(List<String> documentos){
        DocumentosAdapter adapter = new DocumentosAdapter(documentos, getContext());
        recyclerViewDocumentos.setAdapter(adapter);
        recyclerViewDocumentos.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void mostrarEvaluacionSucces(String mensajeExito) {
        Toast.makeText(getContext(), mensajeExito, Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.contenedorFragmentos, listadoFragment).commit();
    }

    @Override
    public void mostrarEvalucionError(String mensajeError) {
        Toast.makeText(getContext(), mensajeError, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarEstatusError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarObservacionesError(String error) {
        observacionesEditTxt.setError(error);
    }
    //Estatus por defecto en AUTORIZADO, la casilla de observaciones se queda deshabilitada hasta que se se
    // seleccione RECHAZADO en el spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        estatusSeleccionado = (String) parent.getItemAtPosition(position);
        if (estatusSeleccionado.equals("RECHAZADO")){
            observacionesLayout.setEnabled(true);
            observacionesLayout.setFocusable(true);
        }else {
            observacionesLayout.setEnabled(false);
            observacionesLayout.setFocusable(false);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}