
package graficas_3d;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.media.j3d.Alpha;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Sofa extends Applet{
    private SimpleUniverse universo = null;
    private Canvas3D lienzo = null;
    
    public Sofa(){
        setLayout(new BorderLayout());
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        lienzo = new Canvas3D(gc);
        add("Center",lienzo);
        universo = new SimpleUniverse(lienzo);
        BranchGroup escena = crearEscenarioGrafico(); 
        universo.addBranchGraph(escena);
    }
    
    private BranchGroup crearEscenarioGrafico(){
        BranchGroup objetoRaiz = new BranchGroup();
        BoundingSphere fronteras = new BoundingSphere(new Point3d(0,0,0),200);
        TextureLoader archivo = new TextureLoader("src/imagenes/Fondo2.jpg",this);
        Background fondo = new Background(archivo.getImage());
        fondo.setApplicationBounds(fronteras);
        objetoRaiz.addChild(fondo);
        objetoRaiz.addChild(modelarObjeto());
        objetoRaiz.addChild(iluminar());
        return objetoRaiz;
    }
    
    private BranchGroup modelarObjeto(){
        BranchGroup objetoRaiz = new BranchGroup();
        TransformGroup grupo = new TransformGroup();
        Transform3D transformacion3d = new Transform3D();
        transformacion3d.setTranslation(new Vector3d(0.0,-0.20,-1.20)); //define la posicion inicial del objeto
        transformacion3d.setScale(0.007); //define el tamaño del objeto
        grupo.setTransform(transformacion3d);
        TransformGroup producto = new TransformGroup();
        producto.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        producto.addChild(cargarObjeto("src\\imagenes\\70386_Sofa.obj"));
        
        Alpha alpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, 0,0,2000,0,0,3000,0,0);
        
        Transform3D eje = new Transform3D();
        eje.setRotation(new AxisAngle4f(-2.0f,0.0f,9.0f,-8f));
        PositionInterpolator Position = new PositionInterpolator(alpha,producto,eje,0.0f,(float) Math.PI*(4.5f));
        BoundingSphere fronteras = new BoundingSphere(new Point3d(0.0,0.0,0.0),1000.0);
        Position.setSchedulingBounds(fronteras);
        producto.addChild(Position);
        grupo.addChild(producto);
        objetoRaiz.addChild(grupo);
        objetoRaiz.addChild(iluminar());
        objetoRaiz.compile();
        
        return objetoRaiz;
    }
    
    private BranchGroup cargarObjeto(String filename){
        BranchGroup objetoRaiz = new BranchGroup();
        TransformGroup grupo = new TransformGroup();
        ObjectFile cargar = new ObjectFile();
        
        Scene s = null;
        File archivo = new java.io.File(filename);
        
        try {
           s = cargar.load(archivo.toURI().toURL());
        } catch(IncorrectFormatException | ParsingErrorException | FileNotFoundException | MalformedURLException e) {
            System.err.println(e);
            System.exit(1);
        }
        grupo.addChild(s.getSceneGroup());
        objetoRaiz.addChild(grupo);
        objetoRaiz.compile();
        
        return objetoRaiz;
    }
    
    private Light iluminar(){
        DirectionalLight luz = new DirectionalLight(true,new Color3f(Color.LIGHT_GRAY),new Vector3f(2.0f,-10.0f,-19.0f));
        luz.setInfluencingBounds(new BoundingSphere(new Point3d(),2000.0));
        return luz;
    }
    
    public static void main (String[] args){
        Sofa ejecutar = new Sofa();
        Frame ventana = new MainFrame(ejecutar,914,638);
    }
}
