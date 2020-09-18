package com.josevalenzuela.prospectosconcreditoapp.Presenter;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.Interactor.AgregarProspectoInteractor;
import com.josevalenzuela.prospectosconcreditoapp.contracts.AgregarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class AgergarProspectoPresenter implements AgregarProspectoContract.Presenter, AgregarProspectoContract.CompleteListener {
    private AgregarProspectoContract.View view;
    private AgregarProspectoContract.Interactor interactor;


    public AgergarProspectoPresenter(AgregarProspectoContract.View view) {
        this.view = view;
        this.interactor = new AgregarProspectoInteractor(this);
    }


    @Override
    public void agregarProspecto(List<String> documentos, String nombre, String primerApellido, String segundoApellido, String calle, String numero, String colonia, String codigoPostal, String telefono, String rfc) {
        ProspectoRequestDTO prospecto = new ProspectoRequestDTO();
        prospecto.setNombre(nombre);
        prospecto.setPrimerApellido(primerApellido);
        prospecto.setSegundoApellido(segundoApellido);
        prospecto.setCalle(calle);
        prospecto.setColonia(colonia);
        prospecto.setCodigoPostal(codigoPostal);
        prospecto.setEstatus("ENVIADO");
        prospecto.setRfc(rfc);
        prospecto.setNumero(numero);
        prospecto.setTelefono(telefono);
        prospecto.setDocumentosEncoded(documentos);
        interactor.guardarProspecto(prospecto);
    }

    @Override
    public boolean validarFormulario(String nombre, String primerApellido, String segundoApellido, String calle, String numero, String colonia, String codigoPostal, String telefono, String rfc) {
        boolean esValido = true;
        String obligatorio = "Campo obligatorio";
        if (nombre.isEmpty()) {
            esValido = false;
            view.mostrarErrorNombre(obligatorio);
        } else if (primerApellido.isEmpty()) {
            esValido = false;
            view.mostrarErrorAppPaterno(obligatorio);
        } else if (rfc.length() < 13) {
            esValido = false;
            view.mostrarErrorRFC("Agregar RFC correctamente");
        } else if (codigoPostal.length() != 5) {
            esValido = false;
            view.mostrarErrorCodigoPostal("Agregar código postal correctamente");
        } else if (calle.isEmpty()) {
            esValido = false;
            view.mostrarErrorCalle(obligatorio);
        } else if (numero.length() > 5) {
            esValido = false;
            //Numero de casa no excede de 5 cifras..
            view.mostrarErrorNumero("Agregar número de casa valido");
        } else if (telefono.isEmpty() || telefono.length() > 10) {
            esValido = false;
            view.mostrarErrorTelefono("Agregar numero de telefono valido");
        } else if (colonia.isEmpty()) {
            view.mostrarErrorColonia(obligatorio);
        }
        return esValido;
    }

    @Override
    public void onSucces(Prospecto prospectos) {
        view.prospectoAgregadoSucces(prospectos);
    }

    @Override
    public void onError(String error) {
        view.mostrarAgregarProspectoError("Error al agregar prospecto nuevo");
    }
}
