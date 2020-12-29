package gui;



import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Point;

public class ArquitecturaFacturaGui extends JPanel{

    private JTabbedPane uno,dos;
    private Contenedor cuadro;
    private Dimension tamano;
    private String titulo;
    private JTabbedPane pestanas;

    public ArquitecturaFacturaGui(Dimension d, Point p, String titulo,JTabbedPane JT ){
        this.setOpaque(false);
        this.pestanas = JT;
        this.titulo = titulo;
        this.tamano = d;
        this.setLocation(p);
        this.setSize(d);
        this.setLayout(null);
        this.componentes();
        this.contenedores();
        this.agregar();
    }
    private void componentes() {

        this.cuadro = null;

    }
    private void contenedores() {

      
        this.cuadro = new Contenedor(
            new Dimension((int)(tamano.getWidth() * 0.9),(int)(tamano.getHeight() * 0.9)), 
            new Point(
                (int)(tamano.getWidth() * 0.05),
                (int)(tamano.getHeight() * 0.05) + ((int)(tamano.getHeight() * 0.45) + (int)(tamano.getHeight() * 0.05))
            )
        ); 

        this.cuadro.settitulo(titulo);
        this.cuadro.agregarPanel(this.pestanas); 
        
    }
    private void agregar() {

      
            this.add(cuadro);  
        

    }
    public Dimension getDimension(){
        return this.tamano;
    }
}
