package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import componentes.CuadroTexto;
import componentes.Dialogo;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import consultas.Consultas;
import errores.ErrorClases;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmpleadoAEGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private String buscar;
	private int opcion;
	private CuadroTexto CuadroTexto;
	private String datos[][];

	public EmpleadoAEGui(Dimension d, int opcion) {

		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		this.datos = Consultas.getConsultas().getEmpleadoCons().buscar(1, null);
		this.atributos(opcion);
		this.cuadroTexto();

	}

	public void ventana(String buscar) {

		EmpleadoInsertarGui emp = new EmpleadoInsertarGui(EmpleadoInsertarGui.getDImension(), 2);
		emp.setdato(Consultas.getConsultas().getEmpleadoCons().buscar(5, buscar));
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), emp);

	}

	public void eliminar(String buscar) {

		Consultas.getConsultas().getEmpleadoCons().eliminar(buscar);

	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();
		this.buscar = String.valueOf(this.tabla.getValueAt(rowIndex, 0));

		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 1) {

			this.ventana(this.buscar);
		}
		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 2) {
			int decision = JOptionPane.showConfirmDialog(null, "¿esta seguro de eliminar el empleado?".toUpperCase());
			if (decision == 0) {
				this.eliminar(buscar);
				ErrorClases.getErrorClases().eliminarCuenta();
			}
		}

		obtenerdatos(1, null);
		this.vaciarBusqueda();

	}

	private class EventoMouse extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {

			establecerCodigo();
		}
	}

	private void obtenerdatos(int codigo, String buscar) {
		this.datos = Consultas.getConsultas().getEmpleadoCons().buscar(codigo, buscar);
		this.modelo.setModelo(new String[] { "cedula", "nombres", "telefono", "edad", "opcion" }, this.datos);
		this.tabla.setModelo(this.modelo, opcion, true);
		this.tabla.updateUI();
	}

	public void atributos(int opcion) {

		this.opcion = opcion;
		this.modelo = new Modelo(new String[] { "cedula", "nombres", "telefono", "edad", "opcion" }, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5, this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), opcion, true);
		this.tabla.addMouseListener(new EventoMouse());
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
		this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
		this.add(this.contenedor);
		this.updateUI();

	}

	public void cuadroTexto() {

		this.CuadroTexto = new CuadroTexto(2, true);
		TextPrompt placeholder = new TextPrompt("Nombres y Apellidos", this.CuadroTexto);
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
		this.CuadroTexto.addKeyListener(new eventoTeclado());
		this.add(this.CuadroTexto);
	}

	public void vaciarBusqueda() {
		TextPrompt placeholder = new TextPrompt("Nombres y Apellidos", this.CuadroTexto);
		this.CuadroTexto.updateUI();
	}

	private class eventoTeclado extends KeyAdapter {

		public void keyReleased(KeyEvent arg0) {

			if (CuadroTexto.getText().equalsIgnoreCase("")) {
				obtenerdatos(1, null);
			} else {
				obtenerdatos(7, CuadroTexto.getText());
			}

		}
	}
}