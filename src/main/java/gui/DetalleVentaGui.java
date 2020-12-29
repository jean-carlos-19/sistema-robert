
package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import componentes.Configuracion;
import componentes.CuadroTexto;
import componentes.Dialogo;
import componentes.Etiqueta;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;
import procesos.Fecha;

public class DetalleVentaGui extends JPanel {
	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private Etiqueta CuadroTexto;
	private String datos[][];
	private Fecha fecha ;
	private Configuracion configuracion;

	public DetalleVentaGui(Dimension d, int opcion) {

		this.fecha = new Fecha();
		this.opcion = opcion;
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth() ), (int) (d.getHeight() ));
		this.configuracion = new Configuracion();
		obtenerDatos(3, null);
		this.atributos(opcion);
	}

	public void ventana(String dato) {

		ComprasGui v = new ComprasGui(ComprasInsertarGui.getDimension(), 3);
		v.obtenerDatos(4, dato);
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), v);

	}

	public void obtenerDatos(int codigo, String dato) {

		this.datos = Consultas.getConsultas().getVentaCons().busqueda(codigo, dato);
		atributos(opcion);
	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();
		
		if (colIndex == (this.tabla.getColumnCount() - 1)) {
			this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 1));
			this.ventana(""+this.fecha.obtenMes(this.codigo));
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
		this.modelo = new Modelo(new String[] { "codigo","descripcion", "cantidad", "pvp", "total" }, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5,this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), opcion, false);
		this.tabla.addMouseListener(new EventoMouse());
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height));
		this.contenedor.setLocation(0,0);
		
		this.add(this.contenedor);
		this.updateUI();

	}

	

}