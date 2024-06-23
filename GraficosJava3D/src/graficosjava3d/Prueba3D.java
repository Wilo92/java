
package graficosjava3d;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;

/**
 *
 * @author julian.ruiz.aya
 */
public class Prueba3D {
    public Prueba3D(){
        SimpleUniverse universo = new SimpleUniverse();
        BranchGroup grupo = new BranchGroup();
        grupo.addChild(new ColorCube(0.3));
        universo.getViewingPlatform().setNominalViewingTransform();
        universo.addBranchGraph(grupo);
    }
    public static void main(String[]args){
        Prueba3D pruebacubo3D = new Prueba3D();
    }    
}
