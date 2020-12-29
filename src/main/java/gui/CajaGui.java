package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;

public class CajaGui extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;

    public CajaGui(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));

        this.modelo = new Modelo(new String[] { "n°", "empleado", "ingreso", "retiro" }, new String[][] { { "1" } });
        this.tabla = new Tabla(modelo, new int[] { this.getSize().width / 4, this.getSize().width / 4,
                this.getSize().width / 4, this.getSize().width / 4 }, this.getSize(), new Point(0, 0), 0, false);
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
        this.contenedor.setLocation(0, 0);
        this.add(this.contenedor);
    }
}