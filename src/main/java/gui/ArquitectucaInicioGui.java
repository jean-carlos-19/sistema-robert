package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Point;

public class ArquitectucaInicioGui extends JPanel {

    private JTabbedPane uno, dos;
    private Contenedor cuadro[];
    private Dimension tamano;
    private String titulo[];
    private JTabbedPane pestanas[];

    public ArquitectucaInicioGui(Dimension d, Point p, String titulo[], JTabbedPane JT[]) {
        this.setOpaque(false);
        this.pestanas = JT;
        this.titulo = titulo;
        this.tamano = d;
        this.setLocation(p);
        this.setSize(d);
        this.setLayout(null);
        this.componentes();
        this.agregar();
    }

    private void componentes() {

        this.cuadro = new Contenedor[pestanas.length];
        if (pestanas.length == 1)
            contenedores2();
        else
            contenedores();

    }

    private void contenedores() {

        for (int i = 0; i < cuadro.length; i++) {
            this.cuadro[i] = new Contenedor(
                    new Dimension((int) (tamano.getWidth() * 0.9), (int) (tamano.getHeight() * 0.45)),
                    new Point((int) (tamano.getWidth() * 0.05), (int) (tamano.getHeight() * 0.05)
                            + ((int) (tamano.getHeight() * 0.45) + (int) (tamano.getHeight() * 0.05)) * i));
            this.cuadro[i].settitulo(titulo[i]);
            this.cuadro[i].agregarPanel(this.pestanas[i]);
        }
    }

    private void contenedores2() {

        for (int i = 0; i < cuadro.length; i++) {
            this.cuadro[i] = new Contenedor(new Dimension((int) (tamano.getWidth()), (int) (tamano.getHeight() * 0.95)),
                    new Point(0, 0));
            this.cuadro[i].settitulo(titulo[i]);
            this.cuadro[i].agregarPanel(this.pestanas[i]);
        }
    }

    private void agregar() {

        for (int i = 0; i < cuadro.length; i++) {
            this.add(cuadro[i]);
        }

    }

    public Dimension getDimension() {
        return this.tamano;
    }
}