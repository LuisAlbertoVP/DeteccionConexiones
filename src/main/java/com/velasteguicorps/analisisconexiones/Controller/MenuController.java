package com.velasteguicorps.analisisconexiones.Controller;

import com.velasteguicorps.analisisconexiones.Model.ControllerPrincipal;
import com.velasteguicorps.analisisconexiones.Utilities.Accion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author luis
 */
public class MenuController implements Initializable{

    private ControllerPrincipal controller;
    
    
    @FXML
    void showAddressPortsNum(ActionEvent event) {
        controller.uploadPanel(Accion.Puertos_Direcciones_Numericos);
    }

    @FXML
    void showCnnPorts(ActionEvent event) {
        controller.uploadPanel(Accion.Conexiones_Puertos);
    }

    @FXML
    void showProcess(ActionEvent event) {
        controller.uploadPanel(Accion.Procesos_Involucrados);
    }

    @FXML
    void showProcessIndentity(ActionEvent event) {
        controller.uploadPanel(Accion.Identidad_Proceso);
    }

    @FXML
    void showProtocolsStatistics(ActionEvent event) {
        controller.uploadPanel(Accion.Estadisticas_Protocolos);
    }

    @FXML
    void showStatisticsEthernet(ActionEvent event) {
        controller.uploadPanel(Accion.Estadisticas_Ethernet);
    }

    @FXML
    void showTablePaths(ActionEvent event) {
        controller.uploadPanel(Accion.Tabla_Rutas);
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void setController(ControllerPrincipal controller){
        this.controller = controller;
    }
    
}
