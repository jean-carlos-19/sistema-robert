package gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import componentes.CuadroTexto;
import componentes.TextPrompt;
import componentes.Etiqueta;

public class BuscarProducto extends JPanel {

	private CuadroTexto texto;
	private Etiqueta titulo;
	private JComboBox<String> lista;
	private TablaProductos tabla;

	public BuscarProducto(Dimension d) {

		this.setLayout(null);
		this.setSize(d);
		this.setLocation(0, 0);
		this.componetes();
		// this.textos();
		// this.titulo();
		// this.listas();
	}

	private void componetes() {
		this.texto = new CuadroTexto(4, true);
		this.titulo = new Etiqueta("categoria".toUpperCase(), JLabel.CENTER);
		this.lista = new JComboBox<String>();
		this.tabla = new TablaProductos(this.getSize());
		this.add(this.tabla);
	}

	private void textos() {

		this.texto.setLocation(0, (int) (this.getSize().height * 0.10));
		this.texto.setSize(new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.06)));
		this.add(texto);
	}

	private void titulo() {

		this.titulo.setLocation(this.texto.getLocation().x + this.texto.getSize().width,
				(int) (this.getSize().height * 0.10));
		this.titulo.setSize(new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.06)));
		this.add(titulo);
	}

	private void listas() {

		this.lista.setLocation(this.titulo.getLocation().x + this.titulo.getSize().width,
				(int) (this.getSize().height * 0.10));
		this.lista.setSize(new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.06)));
		this.add(lista);
	}
}
