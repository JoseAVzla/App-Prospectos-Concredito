package com.josevalenzuela.prospectosconcreditoapp.Presenter;

import com.josevalenzuela.prospectosconcreditoapp.Interactor.TestInteractor;
import com.josevalenzuela.prospectosconcreditoapp.contracts.ApiTestContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class TestPresenter implements ApiTestContract.Presenter, ApiTestContract.CompleteListener {
    private ApiTestContract.View view;
    private ApiTestContract.Interactor interactor;


    public TestPresenter(ApiTestContract.View view) {
        this.view = view;
        this.interactor = new TestInteractor(this);
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
