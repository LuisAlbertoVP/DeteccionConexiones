package com.velasteguicorps.analisisconexiones.Controller;

import com.velasteguicorps.analisisconexiones.Model.ControllerPrincipal;
import com.velasteguicorps.analisisconexiones.Utilities.Accion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

/**
 *
 * @author luis
 */
public class PrincipalController implements Initializable, ControllerPrincipal {
    
    @FXML
    private TextArea txtOutput;

    @FXML
    private MenuItem addPanel;

    @FXML
    private MenuItem closeProgram;

    @FXML
    private TabPane tabPrincipal;

    
    @FXML
    void about(ActionEvent event) {
        about();
    }

    @FXML
    void showAddressPortsNum(ActionEvent event) {
        uploadPanel(Accion.Puertos_Direcciones_Numericos);
    }

    @FXML
    void showCnnPorts(ActionEvent event) {
        uploadPanel(Accion.Conexiones_Puertos);
    }

    @FXML
    void showProcess(ActionEvent event) {
        uploadPanel(Accion.Procesos_Involucrados);
    }

    @FXML
    void showProcessIndentity(ActionEvent event) {
        uploadPanel(Accion.Identidad_Proceso);
    }

    @FXML
    void showProtocolsStatistics(ActionEvent event) {
        uploadPanel(Accion.Estadisticas_Protocolos);
    }

    @FXML
    void showStatisticsEthernet(ActionEvent event) {
        uploadPanel(Accion.Estadisticas_Ethernet);
    }

    @FXML
    void showTablePaths(ActionEvent event) {
        uploadPanel(Accion.Tabla_Rutas);
    }

    @FXML
    void showMenu(ActionEvent event) {
        if(!tabPrincipal.isVisible()){
            txtOutput.setVisible(false); 
            tabPrincipal.setVisible(true);
        }
        uploadMenu();
    }
    
    @FXML
    void showOutput(ActionEvent event) {
        if(!txtOutput.isVisible()){
            tabPrincipal.setVisible(false); 
            txtOutput.setVisible(true);
        }else{
            txtOutput.setVisible(false); 
            tabPrincipal.setVisible(true);
        }
    }
    
    @Override
    public void uploadPanel(Accion action){
        try{
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/SceneAction.fxml"));
            Parent root = (Parent)fxml.load();
            ActionController controller = fxml.getController();
            controller.setController((ControllerPrincipal)this);
            controller.setAction(action);
            int id = tabPrincipal.getSelectionModel().getSelectedIndex();
            tabPrincipal.getTabs().get(id).setText(action.name());
            tabPrincipal.getTabs().get(id).setContent(root);
        }catch(IOException ex){}
    }
    
    @Override
    public void setOutput(String out){
        txtOutput.appendText(out);
    }

    private void about(){
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText("Autor");
        alert.setContentText("Luis Velastegui Alberto Pino");
        alert.showAndWait();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> { 
            uploadMenu();
            setEvents();
        });
    }    
    
    private void uploadMenu(){
        try{
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/SceneMenu.fxml"));
            Parent root = (Parent)fxml.load();
            root.getStylesheets().add(getClass().getResource("/styles/Styles.css").toString());
            MenuController controller = fxml.getController();
            controller.setController((ControllerPrincipal)this);
            int id = tabPrincipal.getSelectionModel().getSelectedIndex();
            tabPrincipal.getTabs().get(id).setText("Menu");
            tabPrincipal.getTabs().get(id).setContent(root);
        }catch(IOException ex){}
    }
    
    
    private void setEvents(){
        addPanel.setOnAction((e) -> {
            tabPrincipal.getTabs().add(new Tab("Nueva Pestaña"));
            tabPrincipal.getSelectionModel().selectLast();
            uploadMenu();
        });
        
        closeProgram.setOnAction((e) -> System.exit(0));
    }
}
