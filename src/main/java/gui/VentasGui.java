package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;
import consultas.Consultas;
import componentes.Configuracion;
import componentes.CuadroTexto;
import componentes.Dialogo;
import componentes.Etiqueta;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentasGui extends JPanel {
    private static Dimension di;
    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private String datos[][];
    private Etiqueta CuadroTexto;
	private Configuracion configuracion;

    public VentasGui(Dimension d) {

        di = d;
        this.setLayout(null);
        this.setLocation(0, 0);
        this.configuracion = new Configuracion();
        this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
        obtenerDatos(5, null);
        this.atributos();
        this.cuadroTexto();

    }

    public void obtenerDatos(int opcion, String dato) {
        this.datos = Consultas.getConsultas().getVentaCons().busqueda(opcion, dato);
        this.atributos();
    }

    public void atributos() {

        if (this.contenedor != null) {
            this.remove(this.contenedor);
        }
        this.modelo = new Modelo(
                new String[] { "codigo", "hora", "empleado", "ingreso", "vuelto", "total", "detalle" }, this.datos);
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 7, this.getSize().width / 7, this.getSize().width / 7,
                        this.getSize().width / 7, this.getSize().width / 7, this.getSize().width / 7,
                        this.getSize().width / 7 },
                this.getSize(), new Point(0, 0), 3, true);
        this.tabla.addMouseListener(new EventoMouse());
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height*0.90));
        this.contenedor.setLocation(0, (int)(this.getSize().height * 0.12));
        this.add(this.contenedor);
        this.updateUI();

    }

    public void cuadroTexto() {

        this.CuadroTexto = new Etiqueta("", JLabel.CENTER);
        total(6, null);
        this.CuadroTexto.setSize(this.getSize().width , (int) (this.getSize().height * 0.02));
        this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.add(this.CuadroTexto);
    }

    public void total(int codigo, String dato) {
    	String cuenta[][] = Consultas.getConsultas().getVentaCons().buscar(codigo, dato);
    	if(cuenta.length > 0) {
    		this.CuadroTexto.setText(cuenta[0][0]);
            this.CuadroTexto.setForeground(this.configuracion.getAzul());
    	}
        
    }

    public static Dimension getDimension() {
        return di;
    }

    public void ventana(String buscar) {
    	DetalleVentaGui v =new DetalleVentaGui(this.getSize(), 0);
    	v.obtenerDatos(8, buscar);
        Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), v);

    }

    public void establecerCodigo() {

        int rowIndex = this.tabla.getSelectedRow();
        int colIndex = this.tabla.getSelectedColumn();
      

        if (colIndex == (this.tabla.getColumnCount() - 1)) {
        	String buscar = String.valueOf(this.tabla.getValueAt(rowIndex, 0));
            this.ventana(buscar);
        }
    }

    private class EventoMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent arg0) {

            establecerCodigo();
        }
    }

}