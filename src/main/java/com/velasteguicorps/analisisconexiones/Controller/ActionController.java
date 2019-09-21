package com.velasteguicorps.analisisconexiones.Controller;

import com.velasteguicorps.analisisconexiones.Model.NetStat;
import com.velasteguicorps.analisisconexiones.Model.ControllerAction;
import com.velasteguicorps.analisisconexiones.Model.Estadisticas;
import com.velasteguicorps.analisisconexiones.Model.Controller;
import com.velasteguicorps.analisisconexiones.Model.Reporte;
import com.velasteguicorps.analisisconexiones.Model.Tree;
import com.velasteguicorps.analisisconexiones.Utilities.Accion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author luis
 */
public class ActionController implements Initializable, ControllerAction{
    private Controller controller;
    
    @FXML
    private Label labelTitle;
    
    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private TableView<Reporte> table;
    
    @FXML
    private TableColumn<Reporte, String> colProtocolo;

    @FXML
    private TableColumn<Reporte, String> colDirecLocal;

    @FXML
    private TableColumn<Reporte, String> colDirecRemota;

    @FXML
    private TableColumn<Reporte, String> colEstado;
    
    @FXML
    private ScrollPane panePathTable;
     
    @FXML
    private TextFlow pathTable;
    
    @FXML
    private TreeTableView<Estadisticas> treeTable;

    @FXML
    private TreeTableColumn<Estadisticas, String> colEstadistica;

    @FXML
    private TreeTableColumn<Estadisticas, String> colNo;

    @FXML
    private TreeTableColumn<Estadisticas, String> colRecibidos;

    @FXML
    private TreeTableColumn<Estadisticas, String> colEnviados;

    
    public void setController(Controller controller){
        this.controller = controller;
    }
    
    public void setAction(Accion action){
        Platform.runLater(() -> {
            labelTitle.setText(action.getValue());
            prepareGUI(action);
            final NetStat netstat = new NetStat((ControllerAction)this);
            Executors.newSingleThreadExecutor().execute(() -> {
                try{
                    netstat.execute(action);
                }catch(IOException ex){} 
            });
        });
    }
    
    private void prepareGUI(Accion action){
        switch(action){
            case Procesos_Involucrados: addColumn(); break;    
            case Estadisticas_Ethernet: showTreeTable(); break;
            case Identidad_Proceso: modifyColumn(); break;
            case Tabla_Rutas: showTextPane(); break;
            case Estadisticas_Protocolos: showTreeTable(); break;
        }
    }
    
    private void addColumn(){
        TableColumn<Reporte,String> col6 = new TableColumn<>("PID");
        col6.setCellValueFactory((e) -> e.getValue().getPidProperty());
        table.getColumns().add(col6);
    }
    
    private void showTreeTable(){
        table.setVisible(false);
        treeTable.setVisible(true);
    }

    private void modifyColumn(){
        colProtocolo.setText("Protocolo/Proceso");
    }
    
    private void showTextPane(){
        table.setVisible(false);
        panePathTable.setVisible(true);
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTablePolicy();
    }
 
    
    private void setTablePolicy(){
        Platform.runLater(() -> {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);           
            colProtocolo.setCellValueFactory((e) -> e.getValue().getProtocoloProperty());
            colDirecLocal.setCellValueFactory((e) -> e.getValue().getDireccionLocalProperty());
            colDirecRemota.setCellValueFactory((e) -> e.getValue().getDireccionRemotaProperty());
            colEstado.setCellValueFactory((e) -> e.getValue().getEstadoProperty());   
            
            treeTable.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
            colEstadistica.setCellValueFactory((e) -> e.getValue().getValue().getNombreProperty());
            colNo.setCellValueFactory((e) -> e.getValue().getValue().getNoProperty());
            colRecibidos.setCellValueFactory((e) -> e.getValue().getValue().getRecibidosProperty());
            colEnviados.setCellValueFactory((e) -> e.getValue().getValue().getEnviadosProperty()); 
        });
    }

    @Override
    public void setProgress(double progress) {
        Platform.runLater(() -> progressBar.setProgress(progress));
    }
    
    @Override
    public synchronized void setOutput(String out){
        Platform.runLater(() -> controller.setOutput(out));
    }

    @Override
    public void addToTable(Reporte netstat) {
        Platform.runLater(() -> table.getItems().add(netstat));
    }

    @Override
    public void addToPathTable(String line, boolean hasColor){
        Platform.runLater(() -> {
            Text txt = new Text(line);
            if(hasColor == true)
                txt.setFill(Color.TOMATO);
            pathTable.getChildren().add(txt);
        });
    }
    
    @Override
    public void addToTreeTable(Tree tree) {
        Platform.runLater(() -> treeTable.setRoot(tree.getTree()));
    }
    
}
