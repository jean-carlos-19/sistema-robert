package gui;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import componentes.CuadroTexto;
import consultas.Consultas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TablaProductos extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private CuadroTexto CuadroTexto;
    private String datos[][];

    public TablaProductos(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth() * 1), (int) (d.getHeight()));

        this.datos = Consultas.getConsultas().getProductoCons().buscar(1, null);
        this.atributos();
        this.cuadroTexto();
    }

    public void atributos() {

        if (this.contenedor != null)
            this.remove(this.contenedor);
        this.modelo = new Modelo(new String[] { "codigo", "descripcion","marca", "cant", "pvp" }, this.datos);
        this.tabla = new Tabla(modelo, new int[] { this.getSize().width / 5, this.getSize().width / 5,
                this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5 }, this.getSize(), new Point(0, 0), 0, false);
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.77));
        this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
        this.add(this.contenedor);
        this.updateUI();
    }

    public void cuadroTexto() {

        this.CuadroTexto = new CuadroTexto(2, true);
        TextPrompt placeholder = new TextPrompt("Descripcion", this.CuadroTexto);
        this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
        this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.CuadroTexto.addKeyListener(new eventoTeclado());
        this.add(this.CuadroTexto);
    }

    private class eventoTeclado extends KeyAdapter {

        public void keyReleased(KeyEvent arg0) {

            if (CuadroTexto.getText().equalsIgnoreCase("")) {
                datos = Consultas.getConsultas().getProductoCons().buscar(1, null);
                atributos();
            } else {
                datos = Consultas.getConsultas().getProductoCons().buscar(2, CuadroTexto.getText());
                atributos();
            }

        }
    }
}
