package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import componentes.Configuracion;
import componentes.CuadroTexto;
import componentes.Dialogo;
import componentes.Etiqueta;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;

public class ComprasAnualesProveedoresGui extends JPanel {


	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private Etiqueta CuadroTexto;
	private String datos[][];
	private Configuracion configuracion;

	public ComprasAnualesProveedoresGui(Dimension d, int opcion) {

		this.opcion = opcion;
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight()*0.45));
		this.configuracion = new Configuracion();
		this.datos = Consultas.getConsultas().getComprasCons().busqueda(7, null);
		this.atributos(opcion);
		this.cuadroTexto();
	}

	public void ventana(String dato) {

		ComprasGui v = new ComprasGui(ComprasInsertarGui.getDimension(), 3);
		v.obtenerDatos(4, dato);
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), v);

	}

	public void obtenerDatos(int codigo, String dato) {

		
		atributos(opcion);
	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();
		
		if (colIndex == (this.tabla.getColumnCount() - 1)) {
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

		this.opcion = opcion;
		this.modelo = new Modelo(new String[] { "codigo", "proveedor","telefono", "total" }, this.datos);
		this.tabla = new Tabla(modelo, new int[] { this.getSize().width / 4, this.getSize().width / 4,
				this.getSize().width / 4,this.getSize().width / 4}, this.getSize(), new Point(0, 0), opcion, false);
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
		this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
		this.add(this.contenedor);

	}

	public void cuadroTexto() {
		
		String total[][] = Consultas.getConsultas().getComprasCons().buscar(6, null);
		this.CuadroTexto = new Etiqueta("", JLabel.LEFT);
		if(total.length > 0)
			this.CuadroTexto.setText(" El total es: " + total[0][0]);
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
		this.CuadroTexto.setForeground(this.configuracion.getTomate());
		this.add(this.CuadroTexto);
	}
}
