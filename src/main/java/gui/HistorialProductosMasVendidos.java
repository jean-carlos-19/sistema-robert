package gui;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import componentes.Dialogo;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;

public class HistorialProductosMasVendidos extends JPanel{

	private static final long serialVersionUID = 7660757156560958668L;
	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private int opcion;
	private String codigo;
	private String datos[][];
	private VentasGui v ;
	@SuppressWarnings("unused")
	private Dialogo d ;

	public HistorialProductosMasVendidos(Dimension d, int opcion) {

		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
		obtenerDatos(9, null);
		this.atributos(opcion);
	}

	public void obtenerDatos(int opcion, String dato) {
		this.datos = Consultas.getConsultas().getVentaCons().busqueda(9, null);
	}

	public void atributos(int opcion) {

		if (this.contenedor != null) {
			this.remove(this.contenedor);
		}

		this.opcion = opcion;
		this.modelo = new Modelo(new String[] { "codigo", "descripcion", "cantidad" }, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 3, this.getSize().width / 3, this.getSize().width /3 },
				this.getSize(), new Point(0, 0), opcion, false);
		this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height));
		this.contenedor.setLocation(0, 0);
		this.add(this.contenedor);

	}
}
