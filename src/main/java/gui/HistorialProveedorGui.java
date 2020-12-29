package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import consultas.Consultas;
import componentes.CuadroTexto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistorialProveedorGui extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private CuadroTexto CuadroTexto;
    private String datos[][];

    public HistorialProveedorGui(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
        this.datos = Consultas.getConsultas().getProveedorCons().buscar(1, "");
        this.atributos();
        this.cuadroTexto();
    }
    

    private void obtenerdatos(int codigo,String buscar) {
    	this.datos = Consultas.getConsultas().getProveedorCons().buscar(codigo, buscar);
		this.modelo.setModelo(new String[] { "ruc", "nombre", "telefono", "direccion" }, this.datos );
	    this.tabla.setModelo(this.modelo, 3, false);
	    this.tabla.updateUI();
	}

    public void atributos() {

        if (this.contenedor != null) {
            this.remove(this.contenedor);
        }

        this.modelo = new Modelo(new String[] { "ruc", "nombre", "telefono", "direccion" }, this.datos);
        this.tabla = new Tabla(modelo, new int[] { this.getSize().width / 4, this.getSize().width / 4,
                this.getSize().width / 4, this.getSize().width / 4 }, this.getSize(), new Point(0, 0), 0, false);
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
        this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
        this.add(this.contenedor);
        this.updateUI();
    }

    public void cuadroTexto() {

        this.CuadroTexto = new CuadroTexto(2, true);
        TextPrompt placeholder = new TextPrompt("Nombre", this.CuadroTexto);
        this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
        this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.CuadroTexto.addKeyListener(new eventoTeclado());
        this.add(this.CuadroTexto);
    }

    private class eventoTeclado extends KeyAdapter {

        public void keyReleased(KeyEvent arg0) {

            if (CuadroTexto.getText().equalsIgnoreCase("")) {
            	obtenerdatos(1,null);
            } else {
            	obtenerdatos(2, CuadroTexto.getText());
            }

        }
    }
}