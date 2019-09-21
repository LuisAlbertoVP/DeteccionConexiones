package com.velasteguicorps.analisisconexiones.Utilities;

/**
 *
 * @author luis
 */
public enum Accion {
    Conexiones_Puertos("Conexiones y Puertos"), Procesos_Involucrados("Procesos Involucrados en crear Conexiones"),
    Estadisticas_Ethernet("Estadisticas de Ethernet"), Puertos_Direcciones_Numericos("Direcciones y Puertos Numericos"), 
    Identidad_Proceso("Identidad de Procesos involucrados"), Tabla_Rutas("Tabla de Rutas"),
    Estadisticas_Protocolos("Estadisticas de los Protocolos");
    
    private final String value;
    
    Accion(String value){
        this.value = value;
    }
    
    public final String getValue(){
        return value;
    }
}
