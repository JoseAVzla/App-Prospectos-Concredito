package com.josevalenzuela.prospectosconcreditoapp.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProspectoRequestDTO {
    @SerializedName("prospectoId") @Expose
    private int prospectoId;
    @SerializedName("nombre") @Expose private String nombre;
    @SerializedName("primerApellido") @Expose private String primerApellido;
    @SerializedName("segundoApellido") @Expose private String segundoApellido;
    @SerializedName("calle") @Expose private String calle;
    @SerializedName("numero") @Expose private String numero;
    @SerializedName("colonia") @Expose private String colonia;
    @SerializedName("codigoPostal") @Expose private String codigoPostal;
    @SerializedName("telefono") @Expose private String telefono;
    @SerializedName("rfc") @Expose private String rfc;
    @SerializedName("estatus") @Expose private String estatus;
    @SerializedName("observaciones") @Expose private String observaciones;
    @SerializedName("documentosEncoded") @Expose private List<String> documentosEncoded;

    public List<String> getDocumentosEncoded() {
        return documentosEncoded;
    }

    public void setDocumentosEncoded(List<String> documentosEncoded) {
        this.documentosEncoded = documentosEncoded;
    }

    public ProspectoRequestDTO() {

    }

    public ProspectoRequestDTO(int prospectoId, String nombre, String primerApellido, String segundoApellido, String calle, String numero, String colonia, String codigoPostal, String telefono, String rfc, String estatus, String observaciones) {
        this.prospectoId = prospectoId;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.rfc = rfc;
        this.estatus = estatus;
        this.observaciones = observaciones;
    }

    public int getProspectoId() {
        return prospectoId;
    }

    public void setProspectoId(int prospectoId) {
        this.prospectoId = prospectoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


}
