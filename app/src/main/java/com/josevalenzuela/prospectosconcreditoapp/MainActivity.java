package com.josevalenzuela.prospectosconcreditoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.josevalenzuela.prospectosconcreditoapp.Presenter.ListadoProspectosPresenter;
import com.josevalenzuela.prospectosconcreditoapp.View.ListadoProspectosFragment;
import com.josevalenzuela.prospectosconcreditoapp.adapters.ProspectoAdapter;
import com.josevalenzuela.prospectosconcreditoapp.contracts.ListadoProspectosContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListadoProspectosContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment listadoProspectosFragment = new ListadoProspectosFragment();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.contenedorFragmentos, listadoProspectosFragment).commit();

    }

    @Override
    public void mostrarListaProspectos(List<Prospecto> prospectos) {

    }

    @Override
    public void showError(String error) {

    }
}