package com.josevalenzuela.prospectosconcreditoapp.contracts;

import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public interface ApiTestContract {
    interface View{
        void showTest(List<Prospecto> prospectos);
        void showError(String error);
    }

    interface Presenter{
        void testApi();
    }

    interface Interactor{
        void testingApi();
    }

    interface CompleteListener{
        void onSucces(List<Prospecto> prospectos);
        void onError(String error);
    }


}
