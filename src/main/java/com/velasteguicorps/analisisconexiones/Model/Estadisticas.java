package com.velasteguicorps.analisisconexiones.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author luis
 */
public class Estadisticas {
    private final StringProperty nombre;
    private final StringProperty no;
    private final StringProperty recibidos;
    private final StringProperty enviados;
    

    public Estadisticas() {
        nombre = new SimpleStringProperty();
        no = new SimpleStringProperty();
        recibidos = new SimpleStringProperty();
        enviados = new SimpleStringProperty();    
    }
    
    public Estadisticas(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
        no = null;
        recibidos = null;
        enviados = null;    
    }
    
    
    public String getNombre(){
        return nombre.get();
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);      
    }
    
    public String getNo(){
        return no.get();
    }
    
    public void setNo(String no){
        this.no.set(no);      
    }
    
    public String getRecibidos(){
        return recibidos.get();
    }
    
    public void setRecibidos(String recibidos){
        this.recibidos.set(recibidos);      
    }
    
    public String getEnviados(){
        return enviados.get();
    }
    
    public void setEnviados(String enviados){
        this.enviados.set(enviados);      
    }
    
    
    public StringProperty getNombreProperty(){
        return nombre;
    }
    
    public StringProperty getNoProperty(){
        return no;
    }
    
    public StringProperty getRecibidosProperty(){
        return recibidos;
    }
    
    public StringProperty getEnviadosProperty(){
        return enviados;
    }
    
}
