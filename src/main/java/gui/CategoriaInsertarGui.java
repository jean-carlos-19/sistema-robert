
package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import componentes.CuadroTexto;
import componentes.Boton;
import componentes.Configuracion;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Categoria;
import procesos.Procesos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriaInsertarGui extends JPanel implements ActionListener {

    private Etiqueta caracteristicas[];
    private CuadroTexto nombre;
    private JTextArea descripcion;
    private Boton guardar, canelar;
    private Dimension dimension;
    private Point distancia;
    private ImageIcon imagen;
    private ImageIcon imagen2;
    private Configuracion configuracion;
    private static Dimension di;
    private int opcion;
    private String id;

    public CategoriaInsertarGui(Dimension d, int opcion) {

        this.opcion = opcion;
        di = d;
        this.setLayout(null);
        this.setOpaque(false);
        this.setSize((int) (d.getWidth() * 0.9), (int) (d.getHeight() * 0.45));
        this.setLocation(0, 0);
        this.componentes();
    }

    public static Dimension getDimension() {
        return di;

    }

    public void setDato(String dato[][]) {
        this.id = dato[0][0];
        this.nombre.setText(dato[0][0]);
        this.descripcion.setText(dato[0][1]);
    }

    private void componentes() {
        this.atributos();
        this.recorrido();
        this.enviar();
    }

    private void enviar() {
        this.guardar = new Boton("guardar", 1, true);
        this.guardar.setSize(dimension);
        this.guardar.setLocation(this.descripcion.getLocation().x,
                this.descripcion.getLocation().y + this.descripcion.getSize().height + this.distancia.y);
        this.guardar.addActionListener(this);
        this.add(this.guardar);
    }

    private void recorrido() {
        for (int i = 0; i < caracteristicas.length; i++) {

            this.caracteristicas[i] = new Etiqueta(NombreCaracteristica(i + 1).toUpperCase(), JLabel.LEFT);
            this.caracteristicas[i].setSize(dimension);
            if (i % 2 == 0) {
                this.caracteristicas[i].setLocation((this.getSize().width - dimension.width) / 2, 0);
                this.add(this.caracteristicas[i]);
            } else {
                this.caracteristicas[i].setLocation((this.getSize().width - dimension.width) / 2,
                        this.caracteristicas[i].getLocation().y + this.caracteristicas[i].getSize().height * (2)
                                + this.distancia.y * 2);
                this.add(this.caracteristicas[i]);
            }

        }
        this.nombre = new CuadroTexto(2, true);
        this.nombre.setSize(dimension);
        this.nombre.setLocation(this.caracteristicas[0].getLocation().x,
                this.caracteristicas[0].getLocation().y + this.caracteristicas[0].getSize().height + distancia.y);
        this.add(this.nombre);

        this.descripcion = new JTextArea();
        this.descripcion.setSize(dimension.width, (int) (dimension.height * 1.5));
        this.descripcion.setBackground(this.configuracion.getBlanco());
        this.descripcion.setForeground(this.configuracion.getNegro());
        this.descripcion.setFont(this.configuracion.getTextos());
        this.descripcion.setBorder(BorderFactory.createLineBorder(this.configuracion.getVerde2(), 2));
        this.descripcion.setLocation(this.caracteristicas[1].getLocation().x,
                this.caracteristicas[1].getLocation().y + this.caracteristicas[1].getSize().height + distancia.y);
        this.add(this.descripcion);
    }

    private String NombreCaracteristica(int i) {

        switch (i) {
            case 1:
                return "nombre";
            case 2:
                return "descripcion";
        }
        return null;
    }

    private void atributos() {
        this.caracteristicas = new Etiqueta[2];
        this.dimension = new Dimension((int) (this.getSize().width * 0.1875), (int) (this.getSize().height * 0.10));
        this.distancia = new Point((int) (this.getSize().width * 0.05), (int) (this.getSize().height * 0.05));
        this.imagen = new ImageIcon("src//main//java//img//trabajo.png");
        this.imagen2 = new ImageIcon("src//main//java//img//subir.png");
        this.configuracion = new Configuracion();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.imagen.getImage(), 0, 0, (int) (this.getSize().width * 0.35), this.getSize().height, this);

        g.drawImage(this.imagen2.getImage(), (int) (this.getSize().width * 0.65), 0,
                (int) (this.getSize().width * 0.35), this.getSize().height, this);
    }

    private void limpiar() {

        this.nombre.setText("");
        this.descripcion.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.guardar)
            this.enviarDatos();

    }

    public boolean enviarDatos() {

        Categoria Categoria = null;
        String nombre = this.nombre.getText();
        String descripcion = this.descripcion.getText();

        Categoria = Procesos.getProcesos().getCategoria(nombre, descripcion);
        boolean validacion = Categoria.validar();
        if (validacion && opcion == 1) {
            Consultas.getConsultas().getCategoria().registar_actualizar("0", nombre, descripcion, 2, 1);
            ErrorClases.getErrorClases().getCategoria();
            this.limpiar();
        }
        if (validacion && opcion == 2) {
            Consultas.getConsultas().getCategoria().registar_actualizar(this.id, nombre, descripcion, 2, 2);
            ErrorClases.getErrorClases().actualizarCategoria();
            this.limpiar();
        }
        return validacion;
    }
}