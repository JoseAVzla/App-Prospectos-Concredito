package com.josevalenzuela.prospectosconcreditoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.josevalenzuela.prospectosconcreditoapp.Presenter.ListadoProspectosPresenter;
import com.josevalenzuela.prospectosconcreditoapp.contracts.ListadoProspectosContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListadoProspectosContract.View {

    private TextView textView;
    private ListadoProspectosContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        this.textView = findViewById(R.id.textView);
        Button testApiBtn = findViewById(R.id.buttonTest);
        this.presenter = new ListadoProspectosPresenter(this);


        testApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.testApi();
            }
        });

    }

    @Override
    public void showTest(List<Prospecto> prospectos) {
        String testExitoso = "Tamano de la lista de prospectos: " + prospectos.size();
        textView.setText(testExitoso);
    }

    @Override
    public void showError(String error) {
        textView.setText(error);
    }
}