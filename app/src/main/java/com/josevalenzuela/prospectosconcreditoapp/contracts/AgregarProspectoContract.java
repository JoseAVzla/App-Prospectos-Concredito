package com.josevalenzuela.prospectosconcreditoapp.contracts;

import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public interface AgregarProspectoContract {
    interface View{
        void mostrarResultado(List<Prospecto> prospectos);
        void mostrarError(String error);
    }

    interface Presenter{
        void agregarProspecto(String nombre, String primerApellido, String segundoApellido, String calle, String numero, String colonia, String codigoPostal, String telefono, String rfc, String estatus, String observaciones);
        void verificarFormulario();
    }

    interface Interactor{
        void guardarProspecto(Prospecto prospecto);
    }

    interface CompleteListener{
        void onSucces(List<Prospecto> prospectos);
        void onError(String error);
    }
}
