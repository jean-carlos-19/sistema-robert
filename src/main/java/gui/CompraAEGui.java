package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;
import errores.ErrorClases;
import componentes.CuadroTexto;

public class CompraAEGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private CuadroTexto CuadroTexto;
	private String datos[][];

	public CompraAEGui(Dimension d, int opcion) {

		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
		this.datos = Consultas.getConsultas().getComprasCons().busqueda(5, null);
		this.atributos(opcion);
		this.cuadroTexto();
	}

	public void getDatos() {
		this.datos = Consultas.getConsultas().getComprasCons().busqueda(5, null);
		this.modelo.setModelo(new String[] { "factura", "ruc", "iva", "subtotal", "total", "opcion" }, this.datos );
        this.tabla.setModelo(this.modelo, opcion, true);
        this.tabla.updateUI();
	}

	public void eliminar(String codigo) {

		Consultas.getConsultas().getComprasCons().eliminar(codigo);
		getDatos();
	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();
		this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 0));

		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 2) {
			int decision = JOptionPane.showConfirmDialog(null, "¿esta seguro de eliminar la compra?".toUpperCase());
			if (decision == 0) {
				this.eliminar(this.codigo);
				ErrorClases.getErrorClases().eliminarCompra();
			}

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
		this.modelo = new Modelo(new String[] { "factura", "ruc", "iva", "subtotal", "total", "opcion"}, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6,
						this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6 },
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
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
		this.add(this.CuadroTexto);
	}

}