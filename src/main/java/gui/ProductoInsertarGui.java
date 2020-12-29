package gui;

import javax.swing.JPanel;
import componentes.CuadroTexto;
import componentes.Boton;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Procesos;
import procesos.Producto;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ProductoInsertarGui extends JPanel implements ActionListener {

    private CuadroTexto campos[][];
    private Etiqueta caracteristicas[][];
    private Boton enviar, cancelar;
    private JComboBox<String> medida, marca, categoria;
    private Dimension dimension, dimensionEtiqueta;
    private Point ubicacion, distancia;
    private int contador;
    private static Dimension di;
    private int opcion;
    private String id;

    public ProductoInsertarGui(Dimension d, int opcion) {

        this.opcion = opcion;
        di = d;
        this.setLayout(null);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
        this.setLocation(0, 0);
        this.componentes();
    }

    public static Dimension getDimension() {
        return di;
    }

    public void setdato(String dato[][]) {

        this.id = dato[0][0];
        this.campos[0][0].setText(dato[0][0]);
        this.campos[0][1].setText(dato[0][1]);
        this.campos[1][0].setText(dato[0][2]);
        this.medida.setSelectedItem(dato[0][3]);
        this.marca.setSelectedItem(dato[0][4]);
        this.categoria.setSelectedItem(dato[0][5]);
        this.campos[3][0].setText(dato[0][6]);
        this.campos[3][1].setText(dato[0][7]);
    }

    private void componentes() {

        this.atributos();
        this.recorrido();
        this.enviar();
    }

    private void atributos() {

        this.contador = 1;
        this.campos = new CuadroTexto[4][2];
        this.caracteristicas = new Etiqueta[4][2];
        this.medida = new JComboBox<String>();
        this.marca = new JComboBox<String>();
        this.categoria = new JComboBox<String>();
        this.dimension = new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.10));
        this.dimensionEtiqueta = new Dimension((int) (this.getSize().width * 0.09),
                (int) (this.getSize().height * 0.10));
        this.distancia = new Point((int) (this.getSize().width * 0.05), (int) (this.getSize().height * 0.05));
        this.ubicacion = this.distancia;
    }

    private void recorrido() {

        for (int i = 0; i < caracteristicas.length; i++) {
            for (int j = 0; j < caracteristicas[0].length; j++) {
                this.caracterisicas(i, j);
                this.campos(i, j);
                if (j == (caracteristicas[0].length - 1)) {
                    this.distancia.x = this.ubicacion.x;
                    this.distancia.y = this.caracteristicas[i][j].getLocation().y + (dimension.height + ubicacion.y);
                }
            }
        }
    }

    private void campos(int i, int j) {

        this.campos[i][j] = new CuadroTexto(2, true);
        this.campos[i][j].setSize(dimension);
        this.campos[i][j].setLocation(getDistanciaCampo(i, j));
        this.add(this.campos[i][j]);

        if (i == 1 && j == 1) {
            this.medida(this.campos[i][j].getLocation());
            this.remove(this.campos[i][j]);
        }
        if (i == 2 && j == 0) {
            this.marca(this.campos[i][j].getLocation());
            this.remove(this.campos[i][j]);
        }
        if (i == 2 && j == 1) {
            this.categoria(this.campos[i][j].getLocation());
            this.remove(this.campos[i][j]);
        }

    }

    private void medida(Point ubicacion) {
        this.medida.setLocation(ubicacion);
        this.medida.setSize(this.dimension);
        String datos[][] = Consultas.getConsultas().getMedidasCons().buscar(1, null);
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                this.medida.addItem(datos[i][j]);
            }
        }
        this.add(this.medida);
    }

    private void categoria(Point ubicacion) {
        this.categoria.setLocation(ubicacion);
        this.categoria.setSize(this.dimension);
        String datos[][] = Consultas.getConsultas().getCategoria().buscar(3, null);
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                this.categoria.addItem(datos[i][j]);
            }
        }
        this.add(this.categoria);
    }

    private void marca(Point ubicacion) {
        this.marca.setLocation(ubicacion);
        this.marca.setSize(this.dimension);
        String datos[][] = Consultas.getConsultas().getMarcaCons().buscar(3, null);
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                this.marca.addItem(datos[i][j]);
            }
        }
        this.add(this.marca);
    }

    private void caracterisicas(int i, int j) {

        this.caracteristicas[i][j] = new Etiqueta(NombreCaracteristica(this.contador), JLabel.LEFT);
        this.caracteristicas[i][j].setSize(dimensionEtiqueta);
        this.caracteristicas[i][j].setLocation(getDistancia(i, j));
        this.add(this.caracteristicas[i][j]);
        if (i == 4 && j == 1) {

            this.remove(this.caracteristicas[i][j]);
        }
        this.contador++;

    }

    private Point getDistancia(int i, int j) {

        if (j > 0 && j < caracteristicas[0].length) {

            this.distancia = new Point(
                    this.campos[i][j - 1].getLocation().x + (this.campos[i][j - 1].getSize().width) + ubicacion.x * (1),
                    distancia.y);
        }

        return this.distancia;
    }

    private Point getDistanciaCampo(int i, int j) {

        if (j >= 0 && j < caracteristicas[0].length) {

            this.distancia = new Point(
                    (this.caracteristicas[i][j].getLocation().x + dimensionEtiqueta.width) + ubicacion.x, distancia.y);

        }

        return this.distancia;
    }

    private void enviar() {

        this.enviar = new Boton("guardar", 1, true);
        this.enviar.setSize(this.dimension);
        this.enviar.setLocation((this.campos[0][1].getLocation().x + this.campos[0][1].getSize().width + ubicacion.x),
                (this.ubicacion.y));
        this.enviar.addActionListener(this);
        this.add(this.enviar);
    }

    private String NombreCaracteristica(int i) {

        switch (i) {
            case 1:

                return "codigo".toUpperCase();
            case 2:

                return "nombre".toUpperCase();
            case 3:

                return "peso".toUpperCase();
            case 4:

                return "medida".toUpperCase();

            case 5:

                return "marca".toUpperCase();
            case 6:

                return "categoria".toUpperCase();

            case 7:

                return "precio".toUpperCase();
            case 8:

                return "cantidad".toUpperCase();

        }
        return null;
    }

    private void limpiar() {

        for (int i = 0; i < this.campos.length; i++) {
            for (int j = 0; j < this.campos[0].length; j++) {
                this.campos[i][j].setText("");
            }
        }

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.enviar)
            this.enviarDatos();
    }

    public boolean enviarDatos() {

        Producto producto = null;
        String codigo = this.campos[0][0].getText();
        String nombre = this.campos[0][1].getText();
        String peso = this.campos[1][0].getText();
        String medida = this.medida.getSelectedItem().toString();
        String marca = this.marca.getSelectedItem().toString();
        String categoria = this.categoria.getSelectedItem().toString();
        String precio = this.campos[3][0].getText();
        String cantidad = this.campos[3][1].getText();
        producto = Procesos.getProcesos().getProductos(codigo, nombre, peso, medida, marca, categoria, precio,cantidad);
        boolean validacion = producto.validar();

        if (validacion && opcion == 1) {
            Consultas.getConsultas().getProductoCons().registar_actualizar(null, codigo, nombre, peso, medida,
                    marca, categoria, precio,cantidad, 1);
            ErrorClases.getErrorClases().getProducto();
            this.limpiar();
        }
        if (validacion && opcion == 2) {
 
            Consultas.getConsultas().getProductoCons().registar_actualizar(this.id, codigo, nombre, peso, medida,
                    marca, categoria, precio,cantidad, 2);
            ErrorClases.getErrorClases().actualizarProducto();
            this.limpiar();
        }

        return validacion;
    }

}