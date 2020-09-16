package com.josevalenzuela.prospectosconcreditoapp.contracts;

import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public interface ListadoProspectosContract {
    interface View{
        void mostrarListaProspectos(List<Prospecto> prospectos);
        void showError(String error);
    }

    interface Presenter{
        void obtenerProspectos();
    }

    interface Interactor{
        void obtenerTodosProspectos();
    }

    interface CompleteListener{
        void onSucces(List<Prospecto> prospectos);
        void onError(String error);
    }


}
