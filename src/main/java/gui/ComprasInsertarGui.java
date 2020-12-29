package gui;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import componentes.CuadroTexto;
import componentes.VentanaEmergente;
import componentes.Boton;
import componentes.Etiqueta;
import componentes.TextPrompt;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.DetalleCompra;
import procesos.ListaCompras;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ComprasInsertarGui extends JPanel implements ActionListener {

	private CuadroTexto campos[][];
	private Etiqueta caracteristicas[][];
	private Boton enviar, listar;
	private JComboBox<String> iva;
	private Dimension dimension, dimensionEtiqueta;
	private Point ubicacion, distancia;
	private int contador;
	private static Dimension di;
	private ListaCompras ListaCompras = new ListaCompras();

	public ComprasInsertarGui(Dimension d) {
		di = d;
		this.setLayout(null);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		this.setLocation(0, 0);
		this.componentes();
	}

	public static Dimension getDimension() {
		return di;
	}

	public void setdato(String dato[][]) {

		for (int i = 0; i < campos.length; i++) {
			for (int j = 0; j < campos.length; j++) {
				this.campos[i][j].setText(dato[i][j]);
			}
		}
	}

	private void componentes() {

		this.atributos();
		this.recorrido();
		this.enviar();
		this.listar();

		this.iva.addItem("SI");
		this.iva.addItem("NO");
		this.iva.addActionListener(this);

	}

	private void atributos() {

		this.contador = 1;
		this.campos = new CuadroTexto[5][2];
		this.caracteristicas = new Etiqueta[5][2];
		this.iva = new JComboBox<String>();
		this.dimension = new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.10));
		this.dimensionEtiqueta = new Dimension((int) (this.getSize().width * 0.09),
				(int) (this.getSize().height * 0.10));
		this.distancia = new Point((int) (this.getSize().width * 0.05), (int) (this.getSize().height * 0.05));
		this.ubicacion = this.distancia;
	}

	private void recorrido() {

		for (int i = 0; i < caracteristicas.length; i++) {
			for (int j = 0; j < caracteristicas[0].length; j++) {
				this.caracterisicas(i, j);
				this.campos(i, j);
				if (j == (caracteristicas[0].length - 1)) {
					this.distancia.x = this.ubicacion.x;
					this.distancia.y = this.caracteristicas[i][j].getLocation().y + (dimension.height + ubicacion.y);
				}
			}
		}
	}

	private void campos(int i, int j) {

		this.campos[i][j] = new CuadroTexto(2, true);
		this.campos[i][j].setSize(dimension);
		this.campos[i][j].setLocation(getDistanciaCampo(i, j));
		if (i == 2 && j == 0) {

			TextPrompt placeholder = new TextPrompt("%".toUpperCase(), this.campos[i][j]);
		}
		if (i == 0 && j == 0) {

			TextPrompt placeholder = new TextPrompt("codigo de barra".toUpperCase(), this.campos[i][j]);
		}
		this.add(this.campos[i][j]);

		if (i == 2 && j == 1) {
			this.iva(this.campos[i][j].getLocation());
			this.remove(this.campos[i][j]);
		}
		if (i == 3 && j == 0) {
			this.remove(this.campos[i][j]);
		}
		if (i == 3 && j == 1) {

			this.remove(this.campos[i][j]);
		}
		if (i == 4 && j == 0) {

			this.remove(this.campos[i][j]);
		}
		if (i == 4 && j == 1) {

			this.remove(this.campos[i][j]);
		}

	}

	private void iva(Point ubicacion) {
		this.iva.setLocation(ubicacion);
		this.iva.setSize(this.dimension);
		this.iva.addActionListener(this);
		this.add(this.iva);
	}

	private void caracterisicas(int i, int j) {

		this.caracteristicas[i][j] = new Etiqueta(NombreCaracteristica(this.contador), JLabel.LEFT);
		this.caracteristicas[i][j].setSize(dimensionEtiqueta);
		this.caracteristicas[i][j].setLocation(getDistancia(i, j));
		this.add(this.caracteristicas[i][j]);
		if (i == 4 && j == 1) {

			this.remove(this.caracteristicas[i][j]);
		}
		this.contador++;

	}

	private Point getDistancia(int i, int j) {

		if (j > 0 && j < caracteristicas[0].length) {

			this.distancia = new Point(
					this.campos[i][j - 1].getLocation().x + (this.campos[i][j - 1].getSize().width) + ubicacion.x * (1),
					distancia.y);
		}

		return this.distancia;
	}

	private Point getDistanciaCampo(int i, int j) {

		if (j >= 0 && j < caracteristicas[0].length) {

			this.distancia = new Point(
					(this.caracteristicas[i][j].getLocation().x + dimensionEtiqueta.width) + ubicacion.x, distancia.y);

		}

		return this.distancia;
	}

	private void enviar() {

		this.enviar = new Boton("guardar", 1, true);
		this.enviar.setSize(this.dimension);
		this.enviar.setLocation((this.campos[0][1].getLocation().x + this.campos[0][1].getSize().width + ubicacion.x),
				(this.ubicacion.y));
		this.enviar.addActionListener(this);
		this.add(this.enviar);
	}

	private void listar() {

		this.listar = new Boton("listar", 1, true);
		this.listar.setSize(this.dimension);
		this.listar.setLocation((this.campos[1][1].getLocation().x + this.campos[1][1].getSize().width + ubicacion.x),
				this.campos[1][1].getLocation().y);
		this.listar.addActionListener(this);
		this.add(this.listar);
	}

	private String NombreCaracteristica(int i) {

		switch (i) {

		case 1:

			return "producto".toUpperCase();
		case 2:

			return "caja".toUpperCase();
		case 3:

			return "unidad".toUpperCase();

		case 4:

			return "precio".toUpperCase();
		case 5:

			return "descuento".toUpperCase();

		case 6:

			return "iva".toUpperCase();

		}
		return null;
	}

	private void limpiar() {

		for (int i = 0; i < this.campos.length; i++) {
			for (int j = 0; j < this.campos[0].length; j++) {
				this.campos[i][j].setText("");
			}
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.enviar) {
			DetalleCompra detalle = enviarDatos();

			if (detalle == null) {
				return;
			}

			if (detalle.validar())
				this.limpiar();

		}
		if (e.getSource() == this.listar) {
			this.ventana(this.ListaCompras);
		}

	}

	private DetalleCompra enviarDatos() {

		DetalleCompra DetalleCompra = null;

		try {
			String producto = this.campos[0][0].getText();
			int cantidad = Integer.valueOf(this.campos[0][1].getText());
			int unidad = Integer.valueOf(this.campos[1][0].getText());
			double pvp = Double.valueOf(this.campos[1][1].getText());
			double descuento = Double.valueOf(this.campos[2][0].getText());
			String iva = String.valueOf(this.iva.getSelectedItem());

			String datos[][] = Consultas.getConsultas().getProductoCons().buscar(5, producto);

			if (datos.length == 0) {
				ErrorClases.getErrorClases().ProductoNoExiste();
				return null;
			}

			DetalleCompra = new DetalleCompra(producto, cantidad, unidad, pvp, 0, iva, descuento);
			ListaCompras.insertar(DetalleCompra);
		} catch (Exception e) {
			return null;
		}

		return DetalleCompra;

	}

	public void ventana(ListaCompras ListaCompras) {

		ListaComprasGUI emp = new ListaComprasGUI(this.getSize(), ListaCompras, 2);
		VentanaEmergente d = new VentanaEmergente(Marco.getGUI(), emp.getSize(), emp);

	}
}