package gui;

import componentes.CuadroTexto;
import componentes.Boton;
import componentes.Configuracion;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Empleado;
import procesos.Procesos;
import componentes.TextPrompt;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class EmpleadoInsertarGui extends JPanel implements ActionListener {

    private CuadroTexto campos[][];
    private Etiqueta caracteristicas[][];
    private Boton enviar, cancelar;
    private JComboBox<String> genero, cargo;
    private Dimension dimension, dimensionArea, dimensionEtiqueta;
    private Point ubicacion, distancia;
    private JTextArea areaTexto;
    private int contador;
    private Configuracion configuracion;
    private static Dimension di;
    private int opcion;
    private String id = "";

    public EmpleadoInsertarGui(Dimension d, int opcion) {

        this.opcion = opcion;
        di = d;
        this.setLayout(null);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
        this.setLocation(0, 0);
        this.componentes();
    }

    public static Dimension getDImension() {
        return di;
    }

    public void setdato(String dato[][]) {

        this.id = dato[0][0];

        this.campos[0][0].setText(dato[0][0]);
        this.campos[0][1].setText(dato[0][1]);
        this.campos[1][0].setText(dato[0][2]);
        this.campos[1][1].setText(dato[0][3]);
        this.campos[2][0].setText(dato[0][4]);
        this.campos[2][1].setText(dato[0][5]);
        this.areaTexto.setText(dato[0][6]);
        this.genero.setSelectedItem(dato[dato.length - 1][dato[0].length - 1]);
    }

    private void componentes() {

        this.atributos();
        this.recorrido();
        this.enviar();
    }

    private void atributos() {

        this.configuracion = new Configuracion();
        this.contador = 1;
        this.areaTexto = new JTextArea();
        this.campos = new CuadroTexto[5][2];
        this.caracteristicas = new Etiqueta[5][2];
        this.genero = new JComboBox<String>();
        this.genero.addItem("M");
        this.genero.addItem("F");
        this.cargo = new JComboBox<String>();
        this.cargo.addItem("Empleado");
        this.dimension = new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.10));
        this.dimensionEtiqueta = new Dimension((int) (this.getSize().width * 0.09),
                (int) (this.getSize().height * 0.10));
        this.dimensionArea = new Dimension((int) (this.getSize().width * 0.57), (int) (this.getSize().height * 0.14));
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
        if (i == 1 && j == 1) {
            TextPrompt placeholder = new TextPrompt("AA/MM/DD", this.campos[i][j]);

        }
        this.add(this.campos[i][j]);

        if (i == 3 && j == 0) {
            this.genero(this.campos[i][j].getLocation());
            this.remove(this.campos[i][j]);
        }
        if (i == 3 && j == 1) {
            this.cargo(this.campos[i][j].getLocation());
            this.remove(this.campos[i][j]);
        }
        if (i == 4 && j == 0) {
            this.areaTexto(this.campos[i][j].getLocation());
            this.remove(this.campos[i][j]);
        }
        if (i == 4 && j == 1) {

            this.remove(this.campos[i][j]);
        }

    }

    private void genero(Point ubicacion) {
        this.genero.setLocation(ubicacion);
        this.genero.setSize(this.dimension);
        this.add(this.genero);
    }

    private void cargo(Point ubicacion) {
        this.cargo.setLocation(ubicacion);
        this.cargo.setSize(this.dimension);
        this.add(this.cargo);
    }

    private void areaTexto(Point ubicacion) {
        this.areaTexto.setLocation(ubicacion);
        this.areaTexto.setSize(this.dimensionArea);
        this.areaTexto.setBackground(this.configuracion.getBlanco());
        this.areaTexto.setForeground(this.configuracion.getNegro());
        this.areaTexto.setFont(this.configuracion.getTextos());
        this.areaTexto.setBorder(BorderFactory.createLineBorder(this.configuracion.getVerde2(), 2));
        this.add(this.areaTexto);
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

                return "cedula".toUpperCase();
            case 2:

                return "nombres".toUpperCase();
            case 3:

                return "apellidos".toUpperCase();
            case 4:

                return "fecha nacimiento".toUpperCase();

            case 5:

                return "sueldo".toUpperCase();
            case 6:

                return "telefono".toUpperCase();

            case 7:

                return "genero".toUpperCase();
            case 8:

                return "cargo".toUpperCase();
            case 9:

                return "direccion".toUpperCase();

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

        Empleado Empleado = null;
        String cedula = this.campos[0][0].getText();
        String nombres = this.campos[0][1].getText();
        String apellidos = this.campos[1][0].getText();
        String fecha = this.campos[1][1].getText();
        String sueldo = this.campos[2][0].getText();
        String telefono = this.campos[2][1].getText();
        String genero = this.genero.getSelectedItem().toString();
        String cargo = this.cargo.getSelectedItem().toString();
        String direccion = this.areaTexto.getText();

        Empleado = Procesos.getProcesos().getEmpleado(cedula, nombres, apellidos, fecha, sueldo, telefono, genero,
                cargo, direccion);
        boolean validacion = Empleado.validar();
        if (validacion && opcion == 1) {
            Consultas.getConsultas().getEmpleadoCons().registar_actualizar(null, cedula, nombres, apellidos, fecha,
                    sueldo, telefono, genero, direccion, 1);
            ErrorClases.getErrorClases().getEmpleado();
            this.limpiar();
        }
        if (validacion && opcion == 2) {

            Consultas.getConsultas().getEmpleadoCons().registar_actualizar(this.id, cedula, nombres, apellidos, fecha,
                    sueldo, telefono, genero, direccion, 2);
            ErrorClases.getErrorClases().actualizarEmpleado();
            this.limpiar();
        }

        return validacion;
    }

}
