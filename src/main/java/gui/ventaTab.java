package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import componentes.Modelo;
import componentes.Tabla;
import componentes.Boton;
import procesos.Detalle;
import procesos.ListaVentas;

public class ventaTab extends JPanel {

    private JScrollPane contenedor;
    private Tabla tabla;
    private Modelo modelo;
    private Boton boton;
    private int opcion;
    private String codigo;
    private ListaVentas ListaVentas;

    public ventaTab(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth()), (int) (d.getHeight() * 0.80));
        this.atributos(null);

    }

    private String[][] subidaDatos(ListaVentas ListaVentas) {

        if (ListaVentas == null)
            return null;

        String detalle[][] = null;

        try {
            ListaVentas.inicilizaAux();
            int i = 0;
            detalle = new String[ListaVentas.getTamano()][5];

            while (ListaVentas.getAux() != null) {

                Detalle compras = ListaVentas.getCompras();
                detalle[i][0] = compras.getNumFactura();
                detalle[i][1] = String.valueOf(compras.getProducto());
                detalle[i][2] = String.valueOf(compras.getCantidad());
                detalle[i][3] = String.valueOf(compras.getPvp());
                detalle[i][4] = String.valueOf(compras.getTotal());
                i++;
            }

        } catch (Exception e) {

        }
        return detalle;
    }

    public void atributos(ListaVentas ListaVentas) {
        this.ListaVentas = ListaVentas;

        if (this.contenedor != null) {
            this.remove(this.contenedor);
        }
        this.modelo = new Modelo(new String[] { "codigo", "descripcion", "cant", "pvp", "total", "eliminar" },
                this.subidaDatos(this.ListaVentas));
        this.tabla = new Tabla(modelo,
                new int[] { this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6,
                        this.getSize().width / 6, this.getSize().width / 6, this.getSize().width / 6 },
                this.getSize(), new Point(0, 0), 2, true);
        this.tabla.addMouseListener(new EventoMouse());

        this.contenedor = new JScrollPane(this.tabla);
        this.contenedor.setSize(this.getSize());
        this.contenedor.setLocation(0, 0);

        this.add(this.contenedor);

    }

    public void establecerCodigo() {

        int rowIndex = this.tabla.getSelectedRow();
        int colIndex = this.tabla.getSelectedColumn();

        if (colIndex == (this.tabla.getColumnCount() - 1)) {

            this.codigo = String.valueOf(this.tabla.getValueAt(rowIndex, 0));
            this.ListaVentas.eliminar(this.codigo);
            this.atributos(this.ListaVentas);
            VentaGui.getVentaGui(null).setTotal(this.ListaVentas);
            this.ListaVentas.inicilizaAux();
            if (this.ListaVentas.getAux() == null) {
                this.ListaVentas.vaciar();
                this.atributos(null);
            }

        }

    }

    private class EventoMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent arg0) {

            establecerCodigo();
        }
    }

}