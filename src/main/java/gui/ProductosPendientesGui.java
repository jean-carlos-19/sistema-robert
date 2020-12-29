package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import componentes.Dialogo;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;

public class ProductosPendientesGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private String datos[][];

	public ProductosPendientesGui(Dimension d, int opcion) {

		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		this.datos = Consultas.getConsultas().getProductoCons().buscar(10,null);
		this.atributos(opcion);
	}

	public void ventana(String dato) {

		DetalleProductoGui v = new DetalleProductoGui(ComprasInsertarGui.getDimension());
		v.obtenerDatos(2, dato);
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), v);
	}

	public void obtenerDatos(int codigo, String dato) {
		this.datos = Consultas.getConsultas().getComprasCons().busqueda(codigo, dato);
		atributos(opcion);
	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();

		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 3) {

			this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 0));
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
		this.modelo = new Modelo(new String[] { "codigo", "descripcion", "marca", "cantidad", "pvp" },
				this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5, this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), opcion, false);
		this.tabla.addMouseListener(new EventoMouse());
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.87));
		this.contenedor.setLocation(0, 0);
		this.add(this.contenedor);

	}
}