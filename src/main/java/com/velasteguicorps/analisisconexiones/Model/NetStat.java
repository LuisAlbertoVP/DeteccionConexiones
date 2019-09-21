package com.velasteguicorps.analisisconexiones.Model;

import com.velasteguicorps.analisisconexiones.Utilities.Accion;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author luis
 */
public class NetStat {
    
    private static final String CMD = "netstat ";
    private static final String CHARSET = "CP850";
    private final ControllerAction controller;
    private final StringBuilder out;
    
    
    public NetStat(ControllerAction controller){
        this.controller = controller;
        out = new StringBuilder();
    }
    
    public void execute(final Accion action) throws IOException{
        controller.setProgress(-1);
        String title = "\n\t\t" + action.getValue() + "\n";
        out.append(title);
        switch(action){
            case Conexiones_Puertos: showTable("-a"); break;
            case Procesos_Involucrados: showTable("-o"); break;    
            case Estadisticas_Ethernet: showTreeTable("-e"); break;
            case Puertos_Direcciones_Numericos: showTable("-an"); break;
            case Identidad_Proceso: showTable("-ab"); break;
            case Tabla_Rutas: showTextFlow("-r"); break;
            case Estadisticas_Protocolos: showTreeTable("-s"); break;
        }
        controller.setProgress(1);
        controller.setOutput(out.toString());
    }
    
    
    private void showTable(final String argument) throws IOException{
        Process p = Runtime.getRuntime().exec(CMD + argument);
        try (Scanner sc = new Scanner(p.getInputStream(), CHARSET)) {
            Pattern whiteSpace = Pattern.compile("\\s*");
            int cont = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(!whiteSpace.matcher(line).matches()){
                    if(cont > 2)
                        checkTableTokens(line);
                    cont++;
                }
                out.append(line).append("\n");
            }
        }
    }
    
    private void checkTableTokens(final String line){
        StringBuilder str = new StringBuilder();
        int token = 0, start = 0, end = 0;
        char[]c = line.toCharArray();
        int size = c.length;
        Reporte netstat = new Reporte();
        for(int i = 0; i < size; i++){
            str.append(c[i]);
            String sequence = str.substring(start, end + 1);
            if(!sequence.equals(" ")){  
                if(sequence.endsWith(" ") || i == (size - 1)){
                    switch(token){
                        case 0: netstat.setProtocolo(sequence.trim());break;
                        case 1: netstat.setDireccionLocal(sequence.trim());break;
                        case 2: netstat.setDireccionRemota(sequence.trim());break;
                        case 3: netstat.setEstado(sequence.trim());break;
                        case 4: netstat.setPid(sequence.trim()); break;
                    }
                    token++;
                    start = end + 1;
                }
            }else
                start++;
            end++;
        }
        controller.addToTable(netstat);
    }
    
    private void showTextFlow(final String argument) throws IOException{
        Process p = Runtime.getRuntime().exec(CMD + argument);
        try (Scanner sc = new Scanner(p.getInputStream(), CHARSET)) {
            Pattern whiteSpace = Pattern.compile("\\s*");
            Pattern equal = Pattern.compile("\\=*");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(!whiteSpace.matcher(line).matches()){
                    boolean hasColor = false;
                    if(equal.matcher(line).matches())
                        hasColor = true;
                    controller.addToPathTable(line, hasColor);
                }
                controller.addToPathTable(System.lineSeparator(), false);
                out.append(line).append("\n");
            }
        }
    }
    
    private void showTreeTable(final String argument) throws IOException{
        Tree tree = new Tree();
        Process p = Runtime.getRuntime().exec(CMD + argument);
        try (Scanner sc = new Scanner(p.getInputStream(), CHARSET)) {
            Pattern word = Pattern.compile("([a-zA-Zá-úÁ-Ú]+\\s)+");
            Pattern whiteSpace = Pattern.compile("\\s*");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(!whiteSpace.matcher(line).matches()){
                    if(line.startsWith("Estad") || line.contains("Statistics")){
                        tree.createParent(new Estadisticas(line));
                    }else
                        checkTreeTableTokens(tree, word, line);
                }
                out.append(line).append("\n");
            }
            controller.addToTreeTable(tree);
        }
    }
    
    private void checkTreeTableTokens(final Tree tree, final Pattern word, 
        final String line)
    {
        Estadisticas netstat = new Estadisticas();
        StringBuilder str = new StringBuilder();
        int token = 0, start = 0, end = 0;
        char[]c = line.toCharArray();
        int size = c.length;
        for(int i = 0; i < size; i++){
            str.append(c[i]);
            String sequence = str.substring(start, end + 1);
            if(!sequence.equals(" ") && !sequence.equals("=") && !sequence.equals("Recibidos") && !sequence.equals("Received") 
                && !sequence.equals("Enviados") && !sequence.equals("Sent"))
            { 
                if(!word.matcher(sequence).matches()){
                    if(sequence.endsWith(" ") || i == (size - 1)){
                        token++;
                        switch(token){
                            case 1: netstat.setNombre(sequence.trim());break;
                            case 2: netstat.setNo(sequence.trim());break;
                            case 3: netstat.setEnviados(sequence.trim());break;
                        }
                        start = end + 1;
                    }
                }   
            }else
                start = start + sequence.length();
            end++;
        }
        if(token != 0){
            if(token == 3){
                netstat.setRecibidos(netstat.getNo());
                netstat.setNo("");
            }
            tree.addChild(netstat); 
        }
    }
    
}
