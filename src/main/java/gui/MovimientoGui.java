package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import componentes.Dialogo;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import componentes.CuadroTexto;
import consultas.Consultas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovimientoGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private CuadroTexto CuadroTexto;
	private String datos[][];

	public MovimientoGui(Dimension d, int opcion) {
		this.opcion = opcion;
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		this.datos = Consultas.getConsultas().getRetirarDineroCosn().buscar(2, null, null);
		this.atributos(opcion);
		this.cuadroTexto();
	}

	public void ventana(String codigo) {

		MovimientoHistorialGui MovimientoHistorialGui = new MovimientoHistorialGui(
				gui.MovimientoHistorialGui.getDimension());
		MovimientoHistorialGui.obtenerDatos(3, codigo, null);
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), MovimientoHistorialGui);
	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();

		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 3) {

			this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 1));
			this.ventana(this.codigo);
		}

	}

	private class EventoMouse extends MouseAdapter {

		public void mouseClicked(MouseEvent arg0) {

			establecerCodigo();
		}
	}

	public void atributos(int opcion) {

		if (this.contenedor != null) {
			this.remove(this.contenedor);
		}

		this.modelo = new Modelo(new String[] { "caja", "mes", "ingreso", "retiro", "detalle" }, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5, this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), opcion, true);
		this.tabla.addMouseListener(new EventoMouse());
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
		this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
		this.add(this.contenedor);
	}

	public void cuadroTexto() {

		this.CuadroTexto = new CuadroTexto(2, true);
		TextPrompt placeholder = new TextPrompt("Mes", this.CuadroTexto);
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
		this.CuadroTexto.addKeyListener(new eventoTeclado());
		//this.add(this.CuadroTexto);
	}

	private class eventoTeclado extends KeyAdapter {

		public void keyReleased(KeyEvent arg0) {

			if (CuadroTexto.getText().equalsIgnoreCase("")) {
				datos = Consultas.getConsultas().getRetirarDineroCosn().buscar(2, null, null);
				atributos(opcion);
			} else {
				datos = Consultas.getConsultas().getRetirarDineroCosn().buscar(2, CuadroTexto.getText(), null);
				atributos(opcion);
			}

		}
	}
}
