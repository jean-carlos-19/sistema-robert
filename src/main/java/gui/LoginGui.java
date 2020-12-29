package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import componentes.CuadroTexto;
import componentes.Boton;
import componentes.Configuracion;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Procesos;

import java.awt.BorderLayout;

public class LoginGui extends JPanel {

    private panelIzquierdo izquierdo;
    private panelDerecho derecho;
    private PanelesGui paneles;
    private Dimension dimension;
    private Configuracion confi;

    public LoginGui(Dimension dimension, PanelesGui p) {

        this.setLayout(null);
        this.atributos(dimension, p);
        this.setSize(dimension);
        this.agregarLaminas();

    }

    private void atributos(Dimension dimension, PanelesGui p) {

        this.confi = new Configuracion();
        this.dimension = dimension;
        this.paneles = p;
        this.izquierdo = new panelIzquierdo();
        this.derecho = new panelDerecho();

    }

    private void agregarLaminas() {

        this.izquierdo.setLocation(0, 0);
        this.derecho.setLocation(this.izquierdo.getSize().width, 0);

        this.add(this.izquierdo);
        this.add(this.derecho);
    }

    private class panelIzquierdo extends JPanel implements ActionListener {

        private JPasswordField contrasena;
        private CuadroTexto usuario;
        private Etiqueta etiquetas[], login;
        private Boton boton;
        private ImageIcon icono;
        private Dimension tamano;

        public panelIzquierdo() {

            this.setBackground(confi.getVerde2());
            this.setSize((int) (dimension.width * 0.30), dimension.height);
            this.setLayout(null);
            this.componenetes();
            this.contenedores();
            this.aregarComponente();
            this.eventos();
        }

        private void eventos() {
            this.boton.addActionListener(this);
        }

        private void aregarComponente() {

            this.add(this.login);
            for (int i = 0; i < etiquetas.length; i++) {
                if (i % 2 == 0) {
                    this.add(this.etiquetas[i]);
                    this.add(this.usuario);
                } else {
                    this.add(this.etiquetas[i]);
                    this.add(this.contrasena);
                }
            }
            this.add(this.boton);
        }

        private void contenedores() {

            this.tamanoComponentes();
            for (int i = 0; i < etiquetas.length; i++) {

                this.etiquetas[i] = new Etiqueta(Nombres(i + 1), JLabel.LEFT);
                this.etiquetas[i].setTamano(this.tamano);
                this.etiquetas[i].setForeground(confi.getBlanco());
                if (i % 2 == 0) {

                    this.etiquetas[i].setUbicacion(new Point((int) (this.getSize().width * 0.05),
                            this.login.getSize().height
                                    + ((int) (this.getSize().height * 0.02) + (this.etiquetas[i].getSize().height))
                                            * (i)));

                    this.usuario.setUbicacion(new Point(this.etiquetas[i].getLocation().x, this.login.getSize().height
                            + ((int) (this.getSize().height * 0.02) + (this.etiquetas[i].getSize().height)) * (i + 1)));

                } else {

                    this.etiquetas[i]
                            .setUbicacion(new Point((int) (this.getSize().width * 0.05), this.usuario.getLocation().y
                                    + this.usuario.getSize().height + (int) (this.getSize().height * 0.02) * (i)));

                    this.contrasena.setLocation(new Point(this.etiquetas[i].getLocation().x,
                            this.etiquetas[i].getLocation().y
                                    + ((int) (this.getSize().height * 0.02) + (this.etiquetas[i].getSize().height))
                                            * (i)));
                }

                this.boton.setUbicacion(new Point(this.contrasena.getLocation().x, this.contrasena.getLocation().y
                        + this.contrasena.getSize().height + (int) (this.getSize().height * 0.02)));

            }

        }

        private void componenetes() {

            this.icono = new ImageIcon("src//main//java//img//usuario.png");
            this.icono.setImage(icono.getImage().getScaledInstance(150, 150, 100));
            this.etiquetas = new Etiqueta[2];
            this.login = new Etiqueta(JLabel.CENTER, this.icono,
                    new Dimension(this.getSize().width, (int) (this.getSize().height * 0.45)), new Point(0, 0));
            this.usuario = new CuadroTexto(4, true);
            this.contrasena = new JPasswordField();
            this.contrasena.setBackground(confi.getBlanco());
            this.contrasena.setBorder(BorderFactory.createLineBorder(confi.getVerde2(), 2));
            this.boton = new Boton("entrar".toUpperCase(), 4, true);
            this.tamano = new Dimension((int) (this.getSize().width * 0.90), (int) (this.getSize().height * 0.05));
        }

        private void tamanoComponentes() {
            this.usuario.setTamano(this.tamano);
            this.contrasena.setSize(this.tamano);
            this.boton.setTamano(this.tamano);
        }

        private String Nombres(int id) {

            switch (id) {

                case 1:

                    return "usuario".toUpperCase();

                case 2:

                    return "password".toUpperCase();
            }

            return null;
        }

        public void actionPerformed(ActionEvent e) {

            String cedula = Consultas.getConsultas().getUsuarioCons().sesion(this.usuario.getText(),
                    new String(this.contrasena.getPassword()))[0][0];
            if (cedula != null) {

                String cargo[][] = Consultas.getConsultas().getEmpleadoCons().buscar(6, cedula);

                for (int i = 0; i < cargo.length; i++) {
                    for (int j = 0; j < cargo[0].length; j++) {

                        if (cargo[i][j].equalsIgnoreCase("empleado")) {
                            this.paneles(3);
                        }
                        if (cargo[i][j].equalsIgnoreCase("administrador")) {
                            this.paneles(2);
                        }
                    }
                }

                return;
            }

            ErrorClases.getErrorClases().getSesion();

        }

        private void paneles(int id) {
            paneles.removeAll();
            paneles.agregaPanel(id);
        }
    }

    private class panelDerecho extends JPanel {

        private Etiqueta version;
        private JLabel imagen;
        private ImageIcon icono;

        private panelDerecho() {

            this.setSize((int) (dimension.width * 0.70), dimension.height);
            this.setLayout(new BorderLayout());
            this.imagen();
            this.version();
            this.agregar();
        }

        private void imagen() {
            this.icono = new ImageIcon("src//main//java//img//portada.png");
            this.icono.setImage(
                    this.icono.getImage().getScaledInstance(this.getSize().width, this.getSize().height, 100));
            this.imagen = new JLabel(this.icono);
        }

        private void version() {
            this.version = new Etiqueta("version: 2.0", JLabel.RIGHT);
            this.version.setOpaque(true);
            this.version.setForeground(confi.getBlanco());
            this.version.setBackground(confi.getNegro());
        }

        private void agregar() {
            this.add(this.imagen, BorderLayout.CENTER);
            this.add(this.version, BorderLayout.SOUTH);
        }
    }
}