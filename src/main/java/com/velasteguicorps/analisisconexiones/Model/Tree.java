package com.velasteguicorps.analisisconexiones.Model;

import javafx.scene.control.TreeItem;

/**
 *
 * @author luis
 */
public class Tree {
    private final TreeItem<Estadisticas> root;
    private TreeItem<Estadisticas> child;
    
    public Tree(){
        root = new TreeItem<>(new Estadisticas("Estadisticas"));
        root.setExpanded(true);
    }
    
    public void createParent(Estadisticas estadisticas){
        child = new TreeItem<>(estadisticas);
        root.getChildren().add(child);
    }
    
    public void addChild(Estadisticas estadisticas){
        child.getChildren().add(new TreeItem<>(estadisticas));
    }
    
    public TreeItem<Estadisticas> getTree(){
        return root;
    }
}
