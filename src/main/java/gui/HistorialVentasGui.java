package gui;

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
import consultas.Consultas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistorialVentasGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private String datos[][];

	public HistorialVentasGui(Dimension d, int opcion) {

		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
		obtenerDatos(3, null);
		this.atributos(opcion);
	}

	public void ventana(String codigo) {

		VentasGui v = new VentasGui(VentasGui.getDimension());
		v.obtenerDatos(7, codigo);
		v.total(8, codigo);
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), v);
	}

	public void obtenerDatos(int opcion, String dato) {
		this.datos = Consultas.getConsultas().getVentaCons().buscar(opcion, dato);
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

		@Override
		public void mouseClicked(MouseEvent arg0) {

			establecerCodigo();
		}
	}

	public void atributos(int opcion) {

		if (this.contenedor != null) {
			this.remove(this.contenedor);
		}

		this.opcion = opcion;
		this.modelo = new Modelo(new String[] { "codigo", "mes", "total", "ganancia", "detalle" }, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5, this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), opcion, true);
		this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height));
		this.contenedor.setLocation(0, 0);
		this.add(this.contenedor);

	}

}