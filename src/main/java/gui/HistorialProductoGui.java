package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import componentes.Configuracion;
import componentes.CuadroTexto;
import componentes.Etiqueta;
import consultas.Consultas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistorialProductoGui extends JPanel {
	private JScrollPane contenedor;
	private Tabla tabla;
	private Modelo modelo;
	private CuadroTexto CuadroTexto;
	private Etiqueta TotalProductos;
	private String datos[][];
	private int id;
	private Configuracion configuracion;

	public HistorialProductoGui(Dimension d, int id) {
		 this.configuracion = new Configuracion();
		this.setLayout(null);
		this.setLocation(0, 0);
		this.id = id;
		if (id == 1)
			this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
		else
			this.setSize((int) (d.getWidth()), (int) (d.getHeight() * 0.45));
		this.getDato();
		this.atributos();
		this.cuadroTexto();
		this.TotalProductos();
	}

	public void getDato() {
		this.datos = Consultas.getConsultas().getProductoCons().buscar(7, null);
	}

	private void atributos() {

		if (this.contenedor != null) {
			this.remove(this.contenedor);

		}

		this.modelo = new Modelo(new String[] { "codigo", "descripcion", "marca", "cantidad", "pvp" }, this.datos);
		this.tabla = new Tabla(modelo,
				new int[] { this.getSize().width / 5, this.getSize().width / 5, this.getSize().width / 5,
						this.getSize().width / 5, this.getSize().width / 5 },
				this.getSize(), new Point(0, 0), 0, false);
		this.contenedor = new JScrollPane(this.tabla);

		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.86));
		this.contenedor.setLocation(0, (int) (this.getSize().height * 0.15));

		this.add(this.contenedor);
		this.updateUI();
	}

	public void cuadroTexto() {

		this.CuadroTexto = new CuadroTexto(2, true);
		TextPrompt placeholder = new TextPrompt("codigo de barra producto".toUpperCase(), this.CuadroTexto);
		this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.05));
		this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));

		this.CuadroTexto.addKeyListener(new eventoTeclado());
		this.add(this.CuadroTexto);
	}
	
	 public void TotalProductos() {

        this.TotalProductos = new Etiqueta("", JLabel.RIGHT);
        total(11, null);
        this.TotalProductos.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.02));
        this.TotalProductos.setLocation((int) (this.getSize().width - this.getSize().width / 4 - (int) (this.getSize().height * 0.05)), (int) (this.getSize().height * 0.05));
        this.add(this.TotalProductos);
    }

    public void total(int codigo, String dato) {
    	String cuenta[][] = Consultas.getConsultas().getProductoCons().buscar(codigo, dato);
    	if(cuenta.length > 0) {
    		this.TotalProductos.setText(cuenta[0][0]);
    		this.TotalProductos.setForeground(this.configuracion.getAzul());
    	}
        
    }


	private class eventoTeclado extends KeyAdapter {

		public void keyReleased(KeyEvent arg0) {

			if (CuadroTexto.getText().equalsIgnoreCase("")) {

				datos = Consultas.getConsultas().getProductoCons().buscar(7, null);
				atributos();
			} else {
				datos = Consultas.getConsultas().getProductoCons().buscar(8, CuadroTexto.getText());
				atributos();
			}

		}
	}
}