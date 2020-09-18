package com.josevalenzuela.prospectosconcreditoapp.contracts;

import android.net.Uri;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.io.IOException;
import java.util.List;

public interface AgregarProspectoContract {
    interface View{
        void prospectoAgregadoSucces(Prospecto prospecto);
        void mostrarErrorNombre(String error);
        void mostrarErrorAppPaterno(String error);
        void mostrarErrorAppMaterno(String error);
        void mostrarErrorRFC(String error);
        void mostrarErrorTelefono(String error);
        void mostrarErrorCodigoPostal(String error);
        void mostrarErrorCalle(String error);
        void mostrarErrorColonia(String error);
        void mostrarErrorNumero(String error);
        void mostrarAgregarProspectoError(String error);
    }

    interface Presenter{
        void agregarProspecto(List<String> documentos, String nombre, String primerApellido, String segundoApellido, String calle, String numero, String colonia, String codigoPostal, String telefono, String rfc);
        boolean validarFormulario(String nombre, String primerApellido, String segundoApellido, String calle, String numero, String colonia, String codigoPostal, String telefono, String rfc);
    }

    interface Interactor{
        void guardarProspecto(ProspectoRequestDTO prospecto);
    }

    interface CompleteListener{
        void onSucces(Prospecto prospectos);
        void onError(String error);
    }
}
