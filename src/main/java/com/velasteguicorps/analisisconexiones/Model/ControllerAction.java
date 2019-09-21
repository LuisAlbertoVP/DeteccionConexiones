package com.velasteguicorps.analisisconexiones.Model;


/**
 *
 * @author luis
 */
public interface ControllerAction extends Controller{
    void setProgress(double progress);
    void addToTable(Reporte netstat);
    void addToPathTable(String line, boolean hasColor);
    void addToTreeTable(Tree tree);
}
