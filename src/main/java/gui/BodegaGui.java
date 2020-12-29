package gui;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import componentes.CuadroTexto;
import consultas.Consultas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BodegaGui extends JPanel {
    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private String datos[][];

    public BodegaGui(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
        this.getDato();
        this.atributos();
    }

    public void getDato() {
        this.datos = Consultas.getConsultas().getProductoCons().buscar(9, null);
    }

    private void atributos() {

        if (this.contenedor != null) {
            this.remove(this.contenedor);

        }

        this.modelo = new Modelo(new String[] { "codigo", "descripcion",  "marca","cantidad", "pvp" },
                this.datos);
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
                        this.getSize().width / 5, this.getSize().width / 5 },
                this.getSize(), new Point(0, 0), 0, false);
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height *0.88 ));
        this.contenedor.setLocation(0, 0);
        this.add(this.contenedor);
        this.updateUI();
    }


}