
package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import componentes.Modelo;
import componentes.Tabla;
import componentes.TextPrompt;
import consultas.Consultas;
import componentes.Boton;
import componentes.Configuracion;
import componentes.CuadroTexto;
import componentes.Dialogo;
import componentes.Etiqueta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistorialSemanalGui extends JPanel implements ActionListener{
    private static Dimension di;
    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private String datos[][];
    private CuadroTexto CuadroTexto, CuadroTexto2;
    private Boton boton;
	private Configuracion configuracion;
	private Etiqueta semanal;

    public HistorialSemanalGui(Dimension d) {

        di = d;
        this.setLayout(null);
        this.setLocation(0, 0);
        this.configuracion = new Configuracion();
        this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
        this.atributos();
        this.cuadroTexto();
        this.Semanal();
    }

    public void obtenerDatos(int opcion,String fechaInicial, String fechaFinal) {
        this.datos = Consultas.getConsultas().getVentaCons().historial(opcion, fechaInicial,fechaFinal);
        this.modelo.setModelo(new String[] { "codigo", "hora", "empleado", "ingreso", "vuelto", "total", "detalle" }, this.datos );
        this.tabla.setModelo(this.modelo, 3, true);
        this.tabla.updateUI();
    }

    public void atributos() {

  
        this.modelo = new Modelo(
                new String[] { "codigo", "fecha", "empleado", "ingreso", "vuelto", "total", "detalle" }, this.datos);
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 7, this.getSize().width / 7, this.getSize().width / 7,
                        this.getSize().width / 7, this.getSize().width / 7, this.getSize().width / 7,
                        this.getSize().width / 7 },
                this.getSize(), new Point(0, 0), 3, true);
        this.tabla.addMouseListener(new EventoMouse());
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height*0.82));
        this.contenedor.setLocation(0, (int)(this.getSize().height * 0.20));
        this.add(this.contenedor);
        this.updateUI();

    }

    public void cuadroTexto() {

        this.CuadroTexto = new CuadroTexto(2, true);
        TextPrompt placeholder2 = new TextPrompt("fecha inicial - aa/mm//dd".toUpperCase(), this.CuadroTexto);
		
        this.CuadroTexto.setSize(this.getSize().width / 5, (int) (this.getSize().height * 0.05));
        this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.add(this.CuadroTexto);
        

        this.CuadroTexto2 = new CuadroTexto(2, true);
        TextPrompt placeholder = new TextPrompt("fecha final - aa/mm//dd".toUpperCase(), this.CuadroTexto2);
		
        this.CuadroTexto2.setSize(this.getSize().width / 5, (int) (this.getSize().height * 0.05));
        this.CuadroTexto2.setLocation(CuadroTexto.getLocation().x + CuadroTexto.getSize().width + (int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.add(this.CuadroTexto2);
        
        this.boton = new Boton("buscar", 1, true);
        this.boton.setSize(this.getSize().width / 5, (int) (this.getSize().height * 0.05));
        this.boton.setLocation(CuadroTexto2.getLocation().x + CuadroTexto2.getSize().width + (int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.boton.addActionListener(this);
        this.add(this.boton);
    }
    public void Semanal() {

        this.semanal = new Etiqueta("", JLabel.CENTER);
        this.semanal.setSize(this.getSize().width , (int) (this.getSize().height * 0.02));
        this.semanal.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.15));
        this.add(this.semanal);
    }

    public void total(int codigo, String fechaInicial,String fechaFinal) {
    	
		String cuenta[][] = Consultas.getConsultas().getVentaCons().historial(codigo, fechaInicial,fechaFinal);
    	if(cuenta.length > 0) {
    		this.semanal.setText(cuenta[0][0]);
            this.semanal.setForeground(this.configuracion.getAzul());
    	}
    	this.semanal.updateUI();
        
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

	public void actionPerformed(ActionEvent arg0) {
		
		if(CuadroTexto.getText().length()!=0 && CuadroTexto2.getText().length() != 0) {
			obtenerDatos(1,this.CuadroTexto.getText(),CuadroTexto2.getText());
			total(2,this.CuadroTexto.getText(),CuadroTexto2.getText());
		}else {
			this.semanal.setText("");
			this.semanal.updateUI();
			this.modelo.setModelo(new String[] { "codigo", "fecha", "empleado", "ingreso", "vuelto", "total", "detalle" }, null);
	        this.tabla.setModelo(this.modelo, 3, true);
	        this.tabla.updateUI();
		}
		
		
	}

}