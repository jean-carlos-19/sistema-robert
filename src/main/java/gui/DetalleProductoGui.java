package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import componentes.CuadroTexto;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;

public class DetalleProductoGui extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private String datos[][];

    public DetalleProductoGui(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
    }

    public void obtenerDatos(int codigo, String dato) {

        this.datos = Consultas.getConsultas().getComprasCons().busqueda(codigo, dato);
        atributos();
    }

    public void atributos() {

        if (this.contenedor != null) {
            remove(this.contenedor);
        }
        // producto,detallecompra.Cantidad,unidad,iva,descuento,precio
        this.modelo = new Modelo(new String[] { "descripcion", "cantidad", "unidad", "iva", "descuento", "precio","total" },
                this.datos);
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 7, this.getSize().width / 7, this.getSize().width /7,
                        this.getSize().width / 7, this.getSize().width / 7, this.getSize().width / 7, this.getSize().width / 7 },
                this.getSize(), new Point(0, 0), 0, false);
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height ));
        this.contenedor.setLocation(0, 0);
        this.add(this.contenedor);
        this.updateUI();
    }
}