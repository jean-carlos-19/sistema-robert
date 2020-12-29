package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import componentes.Dialogo;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import componentes.CuadroTexto;
import consultas.Consultas;
import errores.ErrorClases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductoAEGui extends JPanel {

	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private String codigo;
	private int opcion;
	private CuadroTexto CuadroTexto;
	private String datos[][];
	private Dimension d;

	public ProductoAEGui(Dimension d, int opcion) {

		this.setLayout(null);
		this.setLocation(0, 0);
		this.d = d;
		this.setSize((int) (d.getWidth() * 0.89), (int) (d.getHeight() * 0.45));
		this.datos = Consultas.getConsultas().getProductoCons().buscar(3, null);
		this.atributos(opcion);
		this.cuadroTexto();
	}

	public void ObtenerDato(int codigo, String buscar) {
		datos = Consultas.getConsultas().getProductoCons().buscar(codigo, buscar);
		this.modelo.setModelo(new String[] { "codigo", "descripcion", "marca", "cantidad", "pvp", "opcion" },
				this.datos);
		this.tabla.setModelo(this.modelo, opcion, true);
		this.tabla.updateUI();
	}

	public void ventana(String buscar) {

		ProductoInsertarGui p = new ProductoInsertarGui(this.d.getSize(), 2);
		p.setdato(Consultas.getConsultas().getProductoCons().buscar(6, buscar));
		Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), p);
	}

	public void eliminar(String buscar) {

		Consultas.getConsultas().getProductoCons().eliminar(buscar);

	}

	public void establecerCodigo() {

		int rowIndex = this.tabla.getSelectedRow();
		int colIndex = this.tabla.getSelectedColumn();
		this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 0));

		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 1) {

			this.ventana(this.codigo);
		}
		if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 2) {
			int decision = JOptionPane.showConfirmDialog(null, "¿esta seguro de eliminar el producto?".toUpperCase());
			if (decision == 0) {
				this.eliminar(this.codigo);
				ErrorClases.getErrorClases().eliminarCuenta();
			}
		}
		this.ObtenerDato(3, null);
		this.vaciarBusqueda();
	}

	private class EventoMouse extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {

			establecerCodigo();
		}
	}

	public void atributos(int opcion) {
		this.opcion = opcion;
		this.modelo = new Modelo(new String[] { "codigo", "descripcion", "marca", "cantidad", "pvp", "opcion" },
				this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6,
						this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6 },
				this.getSize(), new Point(0, 0), opcion, true);
		this.tabla.addMouseListener(new EventoMouse());
		this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.57));
		this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
		this.add(this.contenedor);
		this.updateUI();
	}

	public void cuadroTexto() {

		this.CuadroTexto = new CuadroTexto(2, true);
		TextPrompt placeholder = new TextPrompt("codigo de barra producto".toUpperCase(), this.CuadroTexto);
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.10));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
		this.CuadroTexto.addKeyListener(new eventoTeclado());
		this.add(this.CuadroTexto);
	}

	public void vaciarBusqueda() {
		TextPrompt placeholder = new TextPrompt("codigo de barra producto".toUpperCase(), this.CuadroTexto);
		this.CuadroTexto.updateUI();
	}

	private class eventoTeclado extends KeyAdapter {

		public void keyReleased(KeyEvent e) {

			if (CuadroTexto.getText().equalsIgnoreCase("")) {
				ObtenerDato(3, null);
			} else {
				ObtenerDato(4, CuadroTexto.getText());
			}

		}
	}
}