package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import componentes.CuadroTexto;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Detalle;
import procesos.ListaVentas;
import procesos.Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentaGui extends JPanel {

    private JPanel izquierda, derecha;
    private ventaTab tabla;
    private Etiqueta titulo[];
    private CuadroTexto texto[];
    private ListaVentas ListarVenta = new ListaVentas();
    private static VentaGui VentaGui;
    private double vuelto, ingreso;

    private VentaGui(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize(d);
        this.componentes();
        this.estructura();
        this.tabla();
        this.etiquetas();
        this.textos();
        this.agregar();

    }

    public static VentaGui getVentaGui(Dimension d) {
        if (VentaGui == null)
            VentaGui = new VentaGui(d);
        return VentaGui;
    }

    private void etiquetas() {
        for (int i = 0; i < titulo.length; i++) {
            this.titulo[i] = new Etiqueta(this.getNombre(i + 1).toUpperCase(), JLabel.LEFT);
            this.titulo[i].setSize(this.derecha.getSize().width, (int) (this.derecha.getSize().height * 0.06));
            this.titulo[i].setLocation(0, i * this.titulo[i].getSize().height * 2);
            this.derecha.add(this.titulo[i]);
        }
    }

    private void textos() {
        for (int i = 0; i < texto.length; i++) {
            this.texto[i] = new CuadroTexto(4, true);
            this.texto[i].addKeyListener(new eventoTeclado());
            this.texto[i].setSize(this.derecha.getSize().width, (int) (this.derecha.getSize().height * 0.06));
            this.texto[i].setLocation(0, (this.titulo[i].getLocation().y + this.titulo[i].getSize().height));
            if (!(i % 2 == 0))
                this.texto[i].setEditable(false);
            this.derecha.add(this.texto[i]);
        }
    }

    private String getNombre(int id) {
        switch (id) {
            case 1:
                return "producto";
            case 2:
                return "total";
            case 3:
                return "ingreso";
            case 4:
                return "vuelto";

        }
        return "no encontrado";
    }

    private void tabla() {

        this.tabla = new ventaTab(this.izquierda.getSize());
        this.tabla.setLocation(0, 0);
        this.izquierda.add(this.tabla);
    }

    private void componentes() {

        this.izquierda = new JPanel();
        this.derecha = new JPanel();

        this.derecha.setBackground(Color.WHITE);

        this.izquierda.setLayout(null);
        this.derecha.setLayout(null);

        this.titulo = new Etiqueta[4];
        this.texto = new CuadroTexto[4];

    }

    private void estructura() {
        this.izquierda.setLocation(0, 0);
        this.izquierda.setSize((int) (this.getSize().width * 0.75), this.getSize().height);
        this.derecha.setLocation(this.izquierda.getLocation().x + this.izquierda.getSize().width, 0);
        this.derecha.setSize((int) (this.getSize().width * 0.25), this.getSize().height);
    }

    private void agregar() {
        this.add(this.izquierda);
        this.add(this.derecha);
    }

    public Double setTotal(ListaVentas ListarVenta) {
        this.texto[1].setText(ListarVenta.total().toString());
        return ListarVenta.total();
    }

    private Dimension Vuelto() {

        if (!ValidarNumero(this.texto[2].getText()) && !ValidarNumero(this.texto[1].getText())) {
            ErrorClases.getErrorClases().ErrorIngreso();
            return null;
        }
        if (!ValidarNumero(this.texto[2].getText()) && !ValidarNumero(this.texto[1].getText())) {
            ErrorClases.getErrorClases().ErrorIngreso();
            return null;
        }
        this.ingreso = Double.valueOf(this.texto[2].getText());
        double total = Double.valueOf(this.texto[1].getText());

        if (ingreso < total) {
            ErrorClases.getErrorClases().ErrorCantidad();
            return null;
        }

        this.vuelto = this.ingreso - total;
        this.texto[3].setText("" + this.vuelto);
        Dimension d = new Dimension();
        d.setSize(this.vuelto, this.ingreso);
        return d;
    }

    private boolean ValidarNumero(String dato) {
        try {
            Double.valueOf(dato);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void limpiar() {
        for (int i = 0; i < this.texto.length; i++) {
            this.texto[i].setText("");
        }
    }

    private class eventoTeclado extends KeyAdapter {

        public void keyReleased(KeyEvent e) {

            if (ComprobarProducto(e, texto[0], 10))
                obtenerProducto();

            if (ComprobarVenta(e, texto[2], 10))
                subirVenta();
        }

        private boolean ComprobarVenta(KeyEvent e, CuadroTexto texto, int codigo) {
            return e.getSource() == texto && e.getKeyCode() == codigo;
        }

        private void subirVenta() {

            Dimension vueltos = Vuelto();
            if (vueltos == null)
                return;

            ErrorClases.getErrorClases().getVenta();
            limpiar();

            String factura = Consultas.getConsultas().getVentaCons().factura(Login.getLogin().getCedula(), ingreso,
                    vuelto);

            Consultas.getConsultas().getVentaCons().detalle(factura, ListarVenta);
            ListarVenta.vaciar();
            tabla.atributos(ListarVenta);
        }

        private boolean ComprobarProducto(KeyEvent e, CuadroTexto texto, int codigo) {
            return e.getSource() == texto && e.getKeyCode() == codigo;
        }

        private void obtenerProducto() {
            Detalle detalle = getProducto();

            if (detalle == null) {

                ErrorClases.getErrorClases().ProductoNoExiste();
                return;
            }

            ListarVenta.insertar(detalle);
            tabla.atributos(ListarVenta);
            setTotal(ListarVenta);
            texto[0].setText("");

        }

        private Detalle getProducto() {

            Detalle Detalle = null;
            String datos[][] = Consultas.getConsultas().getProductoCons().buscar(5, texto[0].getText());

            if (datos.length == 0)
                return null;

            String codigo = datos[0][0];
            String descripcion = datos[0][1];
            double pvp = Double.valueOf(datos[0][2]);
            Detalle = new Detalle(codigo, descripcion, 1, pvp, 0);
            return Detalle;
        }
    }
}