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
import componentes.TextPrompt;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.DetalleCompra;
import procesos.ListaCompras;
import componentes.CuadroTexto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListaComprasGUI extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private int opcion;
    private String codigo;
    private CuadroTexto CuadroTexto;
    private ListaCompras ListaCompras;

    public ListaComprasGUI(Dimension d, ListaCompras ListaCompras, int opcion) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight()));
        this.opcion = opcion;
        this.ListaCompras = ListaCompras;
        this.atributos(this.subidaDatos(this.ListaCompras));
        this.cuadroTexto();
    }

    public void ventana() {

        Dialogo d = new Dialogo(Marco.getGUI(), this.getSize(), new ComprasGui(ComprasInsertarGui.getDimension(), 3));

    }

    public void establecerCodigo() {

        int rowIndex = this.tabla.getSelectedRow();
        int colIndex = this.tabla.getSelectedColumn();

        if (colIndex == (this.tabla.getColumnCount() - 1) && this.opcion == 2) {

            this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 0));
            this.ListaCompras.eliminar(codigo);
            this.obtenerdatos(this.subidaDatos(this.ListaCompras));
        }

    }

    private String[][] subidaDatos(ListaCompras ListaCompras) {

        String detalle[][] = null;

        ListaCompras.inicilizaAux();
        if (ListaCompras.getAux() == null) {
            ListaCompras.vaciar();
            return null;
        }

        try {

            ListaCompras.inicilizaAux();
            int i = 0;
            detalle = new String[ListaCompras.getTamano()][7];

            while (ListaCompras.getAux() != null) {

                DetalleCompra compras = ListaCompras.getCompras();
                detalle[i][0] = compras.getProducto();
                detalle[i][1] = String.valueOf(compras.getCantidad());
                detalle[i][2] = String.valueOf(compras.getUnidad());
                detalle[i][3] = String.valueOf(compras.getPvp());
                detalle[i][4] = String.valueOf(compras.getDescuento());
                detalle[i][5] = String.valueOf(compras.getTotal());
                detalle[i][6] = compras.getIva();
                i++;
            }

        } catch (Exception e) {

        }
        return detalle;
    }

    private class EventoMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent arg0) {

            establecerCodigo();
        }
    }

    public void atributos(String datos[][]) {

       

        this.modelo = new Modelo(
                new String[] { "producto", "caja", "unidad", "precio", "descuento", "iva","total", "eliminar" },
                datos);
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 8, this.getSize().width / 8, this.getSize().width / 8,
                        this.getSize().width / 8, this.getSize().width / 7, this.getSize().width / 8,
                        this.getSize().width / 8,
                        this.getSize().width / 8 },
                this.getSize(), new Point(0, 0), opcion, true);
        this.tabla.addMouseListener(new EventoMouse());
        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize().width, (int) (this.getSize().height));
        this.contenedor.setLocation(0, (int) (this.getSize().height * 0.30));
        this.add(this.contenedor);

    }
    
    private void obtenerdatos(String[][] datos) {
		
		this.modelo.setModelo(new String[] {  "producto", "caja", "unidad", "precio", "descuento", "iva","total", "eliminar"  }, datos );
	    this.tabla.setModelo(this.modelo, opcion, true);
	    this.tabla.updateUI();
	}


    public void cuadroTexto() {

        this.CuadroTexto = new CuadroTexto(2, true);
        TextPrompt placeholder = new TextPrompt("ruc".toUpperCase(), this.CuadroTexto);
        this.CuadroTexto.setSize(this.getSize().width / 4, (int) (this.getSize().height * 0.20));
        this.CuadroTexto.setLocation((int) (this.getSize().height * 0.05), (int) (this.getSize().height * 0.05));
        this.CuadroTexto.addKeyListener(new eventoTeclado());
        this.add(this.CuadroTexto);
    }

    private void limpiar() {
        this.CuadroTexto.setText("");
        ListaCompras.vaciar();
        this.atributos(subidaDatos(ListaCompras));
    }

    private class eventoTeclado extends KeyAdapter {

        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == 10  && CuadroTexto.getText() !="") {
                String datos[][] = Consultas.getConsultas().getProveedorCons().buscar(4, CuadroTexto.getText());
                if (datos.length == 0) {
                    ErrorClases.getErrorClases().NoexisteProveedor();
                    return;
                }
                this.subirDatos(datos[0][0]);
                limpiar();
            }
           

        }

        public void subirDatos(String ruc) {
            String datos[][] = subidaDatos(ListaCompras);
            String factura = Consultas.getConsultas().getComprasCons().factura(ruc);
            for (int i = 0; i < datos.length; i++) {
                String producto = datos[i][0];
                int cantidad = Integer.valueOf(datos[i][1]);
                int unidad = Integer.valueOf(datos[i][2]);
                double pvp = Double.valueOf(datos[i][3]);
                double dsc = Double.valueOf(datos[i][4]);
                int descuento = (int) (dsc);
                int iva = datos[i][6].equalsIgnoreCase("si") ? 12 : 0;
                Consultas.getConsultas().getComprasCons().detalle(factura, producto, cantidad, unidad, pvp, iva,
                        descuento);
            }
            ErrorClases.getErrorClases().CompraExito();
        }
    }
}
