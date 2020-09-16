package com.josevalenzuela.prospectosconcreditoapp.Presenter;

import com.josevalenzuela.prospectosconcreditoapp.Interactor.ListadoProspectosInteractor;
import com.josevalenzuela.prospectosconcreditoapp.contracts.ListadoProspectosContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class ListadoProspectosPresenter implements ListadoProspectosContract.Presenter, ListadoProspectosContract.CompleteListener {
    private ListadoProspectosContract.View view;
    private ListadoProspectosContract.Interactor interactor;


    public ListadoProspectosPresenter(ListadoProspectosContract.View view) {
        this.view = view;
        this.interactor = new ListadoProspectosInteractor(this);
    }

    @Override
    public void testApi() {
        interactor.testingApi();
    }

    @Override
    public void onSucces(List<Prospecto> prospectos) {
        view.showTest(prospectos);
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }
}
