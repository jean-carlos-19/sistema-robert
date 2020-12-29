package gui;

import componentes.CuadroTexto;
import componentes.Boton;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Procesos;
import procesos.Proveedor;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProveedorInsertarGui extends JPanel implements ActionListener {

    private CuadroTexto campos[][];
    private Etiqueta caracteristicas[][];
    private Boton enviar, cancelar;
    private Dimension dimension, dimensionArea;
    private Point ubicacion, distancia;
    private JTextArea areaTexto;
    private int contador;
    private static Dimension di;
    private int opcion;
    private String id;

    public ProveedorInsertarGui(Dimension d, int opcion) {
        this.opcion = opcion;
        di = d;
        this.setLayout(null);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
        this.setLocation(0, 0);
        this.componentes();
    }

    public void setDato(String dato[][]) {

        this.id = dato[0][0];
        this.campos[0][0].setText(dato[0][0]);
        this.campos[0][1].setText(dato[0][1]);
        this.campos[1][0].setText(dato[0][2]);
        this.campos[1][1].setText(dato[0][3]);
    }

    public static Dimension getDimension() {
        return di;
    }

    private void componentes() {

        this.atributos();
        this.recorrido();
        this.enviar();
    }

    private void atributos() {

        this.contador = 1;
        this.areaTexto = new JTextArea();
        this.campos = new CuadroTexto[2][2];
        this.caracteristicas = new Etiqueta[2][2];
        this.dimension = new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.10));
        this.dimensionArea = new Dimension((int) (this.getSize().width * 0.66), (int) (this.getSize().height * 0.14));
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

    }

    private void caracterisicas(int i, int j) {

        this.caracteristicas[i][j] = new Etiqueta(NombreCaracteristica(this.contador), JLabel.LEFT);
        this.caracteristicas[i][j].setSize(dimension);
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
                    this.caracteristicas[i][j - 1].getLocation().x + (dimension.width * (2)) + ubicacion.x * (2),
                    distancia.y);
        }

        return this.distancia;
    }

    private Point getDistanciaCampo(int i, int j) {

        if (j >= 0 && j < caracteristicas[0].length) {

            this.distancia = new Point((this.caracteristicas[i][j].getLocation().x + dimension.width) + ubicacion.x,
                    distancia.y);
        }
        return this.distancia;
    }

    private void enviar() {

        this.enviar = new Boton("guardar", 1, true);
        this.enviar.setSize(this.dimension);
        this.enviar.setLocation(this.campos[1][1].getLocation().x,
                this.campos[1][1].getLocation().y + this.campos[1][1].getSize().height + ubicacion.y);
        this.enviar.addActionListener(this);
        this.add(this.enviar);
    }

    private String NombreCaracteristica(int i) {

        switch (i) {
            case 1:

                return "ruc".toUpperCase();
            case 2:

                return "nombre".toUpperCase();
            case 3:

                return "direccion".toUpperCase();
            case 4:

                return "telefono".toUpperCase();

        }
        return null;
    }

    private void limpiar() {

        for (int i = 0; i < this.campos.length; i++) {
            for (int j = 0; j < this.campos[0].length; j++) {
                this.campos[i][j].setText("");
            }
        }
        this.areaTexto.setText("");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.enviar)
            this.enviarDatos();

    }

    public boolean enviarDatos() {

        Proveedor Proveedor = null;
        String ruc = this.campos[0][0].getText();
        String nombre = this.campos[0][1].getText();
        String direccion = this.campos[1][0].getText();
        String telefono = this.campos[1][1].getText();

        Proveedor = Procesos.getProcesos().getProveedor(ruc, nombre, telefono, direccion);
        boolean validacion = Proveedor.validar();

        if (validacion && opcion == 1) {
            Consultas.getConsultas().getProveedorCons().registar_actualizar(null, ruc, nombre, telefono, direccion, 2,
                    1);
            ErrorClases.getErrorClases().getProveedor();
            this.limpiar();
        }
        if (validacion && opcion == 2) {

            Consultas.getConsultas().getProveedorCons().registar_actualizar(this.id, ruc, nombre, telefono, direccion,
                    2, 2);
            ErrorClases.getErrorClases().actualizarProveedor();
            this.limpiar();
        }
        return validacion;
    }
}