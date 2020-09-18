package com.josevalenzuela.prospectosconcreditoapp.contracts;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

public interface EvaluarProspectoContract {

    interface View{
        void mostrarEvaluacionSucces(String mensajeExito);
        void mostrarEvalucionError(String mensajeError);
        void mostrarEstatusError(String error);
        void mostrarObservacionesError(String error);
    }

    interface Presenter{
        void updateProspecto(Prospecto prospecto, String estatus, String observaciones);
        boolean validarFormulario(String estatus, String observaciones);
    }

    interface Interactor{
        void actualizarEstadoProspecto(ProspectoRequestDTO prospecto);
    }

    interface CompleteListener{
        void onSucces(Prospecto prospecto);
        void onError(String error);
    }

}
