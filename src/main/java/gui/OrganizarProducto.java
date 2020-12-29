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

public class OrganizarProducto extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private String codigo;
    private int opcion;
    private CuadroTexto CuadroTexto;
    private String datos[][];
    private Dimension d;

    public OrganizarProducto(Dimension d, int opcion) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.d = d;
        this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
        this.datos = Consultas.getConsultas().getProductoCons().buscar(10, null);
        this.atributos(opcion);
        this.cuadroTexto();
    }
    
    public void ObtenerDato(int codigo,String buscar) {
    	datos = Consultas.getConsultas().getProductoCons().buscar(codigo, buscar);
    	this.modelo.setModelo(new String[] { "codigo", "descripcion",  "marca","cantidad", "pvp","opcion"}, this.datos );
	    this.tabla.setModelo(this.modelo, 3, true);
	    this.tabla.updateUI();
    }

    public void ventana(String buscar) {

    	Dimension di = new Dimension(this.d.getSize().width, (int)(this.d.getSize().height * 0.45));
        ProductoInsertarGui p = new ProductoInsertarGui(this.d.getSize(), 2);
        p.setdato(Consultas.getConsultas().getProductoCons().buscar(6, buscar));
        Dialogo d = new Dialogo(Marco.getGUI(), di.getSize(), p);
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
        this.modelo = new Modelo(new String[] { "codigo", "descripcion",  "marca","cantidad", "pvp","opcion" }, this.datos);
        this.tabla = new Tabla(modelo, new int[] { this.getSize().width / 6, this.getSize().width / 6,
                this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6  }, this.getSize(), new Point(0, 0), opcion, true);
        this.tabla.addMouseListener(new EventoMouse());
        this.contenedor = new JScrollPane(this.tabla);
		this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height * 0.67));
        this.contenedor.setLocation(0, (int) (this.getSize().height * 0.20));
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

    private class eventoTeclado extends KeyAdapter {

        public void keyReleased(KeyEvent e) {

            if (CuadroTexto.getText().equalsIgnoreCase("")) {
            	ObtenerDato(10,null);
            } else {
            	ObtenerDato(8, CuadroTexto.getText());
            }

        }
    }
}