package com.josevalenzuela.prospectosconcreditoapp.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.josevalenzuela.prospectosconcreditoapp.Presenter.ListadoProspectosPresenter;
import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.adapters.ProspectoAdapter;
import com.josevalenzuela.prospectosconcreditoapp.contracts.ListadoProspectosContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class ListadoProspectosFragment extends Fragment implements ListadoProspectosContract.View, ProspectoAdapter.OnItemClickListener {

    private ListadoProspectosContract.Presenter presenter;
    private RecyclerView recyclerViewProspecto;
    private Context mContext;
    private Fragment infoProspectoFragment, agregarProspectoFragment, evaluarProspectoFragment;
    private List<Prospecto> prospectos;
    private ProspectoAdapter prospectoAdapter;
    private FloatingActionButton addProspectoBtn;
    private androidx.appcompat.widget.SearchView prospectosSearch;

    public ListadoProspectosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_listadoprospectos, container, false);
        this.recyclerViewProspecto = fragmentView.findViewById(R.id.recyclerProspectoViewId);
        this.infoProspectoFragment = new InfoProspectoFragment();
        this.agregarProspectoFragment = new AgegarProspectoFragmet();
        this.evaluarProspectoFragment = new EvaluarProspectoFragment();
        this.addProspectoBtn = fragmentView.findViewById(R.id.nuevoProspectoBtn);
        this.prospectosSearch = fragmentView.findViewById(R.id.searchViewProspectosId);


        presenter = new ListadoProspectosPresenter(this);
        presenter.obtenerProspectos();

        addProspectoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.contenedorFragmentos, agregarProspectoFragment).commit();
            }
        });



        prospectosSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()){
                    prospectoAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });


        getActivity().setTitle("Prospectos");
        // Inflate the layout for this fragment
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void mostrarListaProspectos(List<Prospecto> prospectos) {
        this.prospectos = prospectos;
        prospectoAdapter = new ProspectoAdapter(prospectos, mContext, this::onItemClick);

        recyclerViewProspecto.setAdapter(prospectoAdapter);
        recyclerViewProspecto.setLayoutManager(new LinearLayoutManager(mContext));
        prospectosSearch.setQuery("", false);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        Prospecto prospecto = prospectos.get(position);
        bundle.putSerializable("prospectoSeleccionado", prospecto);
        if (prospecto.getEstatus().equals("RECHAZADO") || prospecto.getEstatus().equals("AUTORIZADO")){
            infoProspectoFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(R.id.contenedorFragmentos, infoProspectoFragment).commit();
        }else {
            evaluarProspectoFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(R.id.contenedorFragmentos, evaluarProspectoFragment).commit();
        }
    }
}