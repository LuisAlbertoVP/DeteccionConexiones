package com.velasteguicorps.analisisconexiones.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author luis
 */
public class Reporte {
    private final StringProperty protocolo;
    private final StringProperty direccionLocal;
    private final StringProperty direccionRemota;
    private final StringProperty estado;
    private final StringProperty pid;

    
    public Reporte(){
        protocolo = new SimpleStringProperty("");
        direccionLocal = new SimpleStringProperty("");
        direccionRemota = new SimpleStringProperty("");
        estado = new SimpleStringProperty("");
        pid = new SimpleStringProperty("");
    }
    
    
    public String getProtocolo() {
        return protocolo.get();
    }

    public void setProtocolo(String protocolo) {
        this.protocolo.set(protocolo);
    }

    public String getDireccionLocal() {
        return direccionLocal.get();
    }

    public void setDireccionLocal(String direccionLocal) {
        this.direccionLocal.set(direccionLocal);
    }

    public String getDireccionRemota() {
        return direccionRemota.get();
    }

    public void setDireccionRemota(String direccionRemota) {
        this.direccionRemota.set(direccionRemota);
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    } 
    
    public String getPid(){
        return pid.get();
    }
    
    public void setPid(String pid){
        this.pid.set(pid);
    }
    
    
    public StringProperty getProtocoloProperty() {
        return protocolo;
    }
    
    public StringProperty getDireccionLocalProperty() {
        return direccionLocal;
    }
    
    public StringProperty getDireccionRemotaProperty() {
        return direccionRemota;
    }
    
    public StringProperty getEstadoProperty() {
        return estado;
    }
    
    public StringProperty getPidProperty() {
        return pid;
    }

    @Override
    public String toString() {
        return "Protocolo=" + protocolo.get() + ", DireccionLocal=" + direccionLocal.get() +
                ", DireccionRemota=" + direccionRemota.get() + ", Estado=" + estado.get();
    }
    
}
