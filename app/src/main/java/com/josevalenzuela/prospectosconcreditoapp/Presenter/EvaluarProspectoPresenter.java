package com.josevalenzuela.prospectosconcreditoapp.Presenter;

import com.josevalenzuela.prospectosconcreditoapp.DTO.ProspectoRequestDTO;
import com.josevalenzuela.prospectosconcreditoapp.Interactor.EvaluarProspectoInteractor;
import com.josevalenzuela.prospectosconcreditoapp.contracts.EvaluarProspectoContract;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

public class EvaluarProspectoPresenter implements EvaluarProspectoContract.Presenter, EvaluarProspectoContract.CompleteListener {
    private EvaluarProspectoContract.View view;
    private EvaluarProspectoContract.Interactor interactor;

    public EvaluarProspectoPresenter(EvaluarProspectoContract.View view) {
        this.view = view;
        this.interactor = new EvaluarProspectoInteractor(this);
    }

    @Override
    public void updateProspecto(Prospecto prospecto, String estatus, String observaciones) {
        ProspectoRequestDTO requestDTO = new ProspectoRequestDTO();
        requestDTO.setProspectoId(prospecto.getProspectoId());
        requestDTO.setNombre(prospecto.getNombre());
        requestDTO.setPrimerApellido(prospecto.getPrimerApellido());
        requestDTO.setSegundoApellido(prospecto.getSegundoApellido());
        requestDTO.setCalle(prospecto.getCalle());
        requestDTO.setColonia(prospecto.getColonia());
        requestDTO.setCodigoPostal(prospecto.getCodigoPostal());
        requestDTO.setEstatus(estatus);
        requestDTO.setRfc(prospecto.getRfc());
        requestDTO.setNumero(prospecto.getNumero());
        requestDTO.setTelefono(prospecto.getTelefono());
        requestDTO.setDocumentosUrl(prospecto.getDocumetosUrl());
        requestDTO.setObservaciones(observaciones);
        interactor.actualizarEstadoProspecto(requestDTO);
    }

    @Override
    public boolean validarFormulario(String estatus, String observaciones) {
        boolean esValido = true;
        if (estatus.isEmpty()){
            esValido = false;
            view.mostrarEstatusError("Agregar un estatus");
        }
        if (estatus.equals("RECHAZADO") && observaciones.isEmpty()){
            esValido = false;
            view.mostrarObservacionesError("Campo de observaciones obligatorio");
        }
        return esValido;
    }

    @Override
    public void onSucces(Prospecto prospecto) {
        view.mostrarEvaluacionSucces("El prospecto " + prospecto.getNombre() + " fue evaluado correctamente");
    }

    @Override
    public void onError(String error) {
        view.mostrarEvalucionError("No se pudo realizar la evaluación: \n" + error);
    }
}
