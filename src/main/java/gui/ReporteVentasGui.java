package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;
import componentes.CuadroTexto;

public class ReporteVentasGui extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private CuadroTexto CuadroTexto;

    public ReporteVentasGui(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
        this.atributos();
        this.cuadroTexto();
    }

    private void atributos() {
        this.modelo = new Modelo(new String[] { "n°", "mes", "ingreso", "total", "ganancia", "producto" },
                new String[][] { { "1" } });
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6,
                        this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6 },
                this.getSize(), new Point(0, 0), 0, false);
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
        this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
        this.add(this.contenedor);
    }

    public void cuadroTexto() {

        this.CuadroTexto = new CuadroTexto(2, true);
        this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
        this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.add(this.CuadroTexto);
    }
}