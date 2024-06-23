/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficosjava3d;

/**
 *
 * @author julian.ruiz.aya
 */
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Java3D1 extends JPanel{
    
    public Java3D1(){
        GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
        
        Canvas3D canvas3D = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas3D);
        
        SimpleUniverse universo = new SimpleUniverse(canvas3D);
        universo.getViewingPlatform().setNominalViewingTransform();
        
        BranchGroup escena= crearGrafoEscena();
        escena.compile();
        
        universo.addBranchGraph(escena);
}

public BranchGroup crearGrafoEscena(){
    BranchGroup objetoraiz = new BranchGroup();
    
    objetoraiz.addChild(new ColorCube(0.2));
    
    
    return objetoraiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground","true");
        JFrame ventana = new JFrame("Mi primera aplicaciÃ³n 3D");
        Java3D1 panel= new Java3D1();
        ventana.add(panel);
        ventana.setSize(700,700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
