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

public class MovimientoHistorialGui extends JPanel {

	private static Dimension di;
	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private CuadroTexto CuadroTexto;
	private String datos[][];
	private int opcion;

	public MovimientoHistorialGui(Dimension d) {

		di = d;
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		obtenerDatos(1, null, null);
		this.opcion = 1;
		this.atributos();
		this.cuadroTexto();
	}

	private void atributos() {

		if (this.contenedor != null) {
			this.remove(this.contenedor);
		}
		this.modelo = new Modelo(new String[] { "empleado", "ingreso", "retiro", "hora" }, this.datos);
		this.tabla = new Tabla(modelo, new int[] { this.getSize().width / 4, this.getSize().width / 4,
				this.getSize().width / 4, this.getSize().width / 4 }, this.getSize(), new Point(0, 0), 0, false);
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize((int) (this.getSize().width * 0.99), (int) (this.getSize().height * 0.67));
		this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
		this.add(this.contenedor);
		this.updateUI();
	}

	public void obtenerDatos(int opcion, String dato1, String dato2) {
		this.opcion = opcion;
		this.datos = Consultas.getConsultas().getRetirarDineroCosn().buscar(opcion, dato1, dato2);
		this.atributos();
	}

	public void cuadroTexto() {

		this.CuadroTexto = new CuadroTexto(2, true);
		TextPrompt placeholder = new TextPrompt("Nombres y Apellidos", this.CuadroTexto);
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
		this.CuadroTexto.addKeyListener(new eventoTeclado());
		//this.add(this.CuadroTexto);
	}

	public static Dimension getDimension() {
		return di;

	}

	private class eventoTeclado extends KeyAdapter {

		public void keyReleased(KeyEvent arg0) {

			if (CuadroTexto.getText().equalsIgnoreCase("")) {
				if (opcion == 1)
					obtenerDatos(opcion, null, null);
				else
					obtenerDatos(opcion, null, null);

			} else {
				if (opcion == 1)
					obtenerDatos(5, CuadroTexto.getText(), null);
				else
					obtenerDatos(6, CuadroTexto.getText(), null);
			}

		}
	}
}
