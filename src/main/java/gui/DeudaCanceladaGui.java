package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import componentes.Modelo;
import componentes.Tabla;

public class DeudaCanceladaGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private String codigo;
	private int opcion;

	public DeudaCanceladaGui(Dimension d, int opcion) {
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		this.atributos(opcion);
	}

	public void ventana() {
		// TODO Auto-generated method stub

	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();

		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 1) {

			this.codigo = String.valueOf(this.tabla.getValueAt(this.tabla.getSelectedRow(), 0));
			this.ventana();
		} else {

			/*
			 * codigo
			 * 
			 */
		}

	}

	private class EventoMouse extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {

			establecerCodigo();
		}
	}

	public void atributos(int opcion) {

		this.opcion = opcion;
		this.modelo = new Modelo(new String[] { "cedula", "cliente", "direccion", "telefono", "detalle" },
				new String[][] { { "1" } });
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5, this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), opcion, true);
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
		this.contenedor.setLocation(0, 0);
		this.add(this.contenedor);

	}
}
