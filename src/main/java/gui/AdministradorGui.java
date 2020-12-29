package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import componentes.Lista;
import componentes.Boton;
import componentes.Configuracion;
import componentes.Etiqueta;
import consultas.Consultas;
import procesos.Procesos;

public class AdministradorGui extends JPanel {

    private menu menu;
    private estadisticas estadisticas;
    private Dimension dimension;
    private PanelesGui paneles;
    private Configuracion confi;
    private PestanaGui pestanaSup, pestanaInf;

    public AdministradorGui(Dimension dimension, PanelesGui p) {

        this.paneles = p;
        this.dimension = dimension;
        this.pestanaSup = new PestanaGui(null, 0, 0);
        this.pestanaInf = new PestanaGui(null, 0, 0);
        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize(this.dimension);
        this.agregarLaminas();
    }

    private void agregarLaminas() {

        this.confi = new Configuracion();
        this.menu = new menu();
        this.estadisticas = new estadisticas();
        this.estadisticas.cambiaPaneles(1);
        this.add(this.menu);
        this.add(this.estadisticas);

    }

    private class menu extends JPanel implements ActionListener {

        private Boton items[];
        private Lista rol;
        private Etiqueta login;
        private ImageIcon icon;

        public menu() {

            this.setBackground(confi.getVerde2());
            this.setLayout(null);
            this.setLocation(0, 0);
            this.setSize((int) (dimension.width * 0.12), dimension.height);
            this.componentes();
            this.contenedores();
            this.eventos();
            this.agregar();
        }

        private void contenedores() {

            for (int i = 0; i < items.length; i++) {
                if (i == 0) {
                    this.items[i] = new Boton(this.nombresItem(i + 1).toUpperCase(), 1, true,
                            new Dimension((int) (this.getSize().width * 0.9), (int) (this.getSize().height * 0.05)),
                            new Point((int) (this.getSize().width * 0.05), this.rol.getLocation().y
                                    + this.rol.getSize().height + (int) (this.getSize().height * 0.05)));
                } else {
                    this.items[i] = new Boton(this.nombresItem(i + 1).toUpperCase(), 1, true,
                            new Dimension((int) (this.getSize().width * 0.9), (int) (this.getSize().height * 0.05)),
                            new Point((int) (this.getSize().width * 0.05),
                                    this.items[i - 1].getLocation().y + this.items[i - 1].getSize().height));
                }

            }
        }

        private void agregar() {

            this.add(this.login);
            this.add(this.rol);

            for (int i = 0; i < items.length; i++) {
                this.add(this.items[i]);
            }
        }

        private void componentes() {

            this.icon = new ImageIcon("src//main//java//img//usuario.png");
            this.icon.setImage(icon.getImage().getScaledInstance(150, 150, 100));
            this.login = new Etiqueta(JLabel.CENTER, this.icon,
                    new Dimension(this.getSize().width, (int) (this.getSize().height * 0.30)), new Point(0, 0));

            this.items = new Boton[9];

            this.rol = new Lista(
                    new Dimension((int) (this.getSize().width * 0.9), (int) (this.getSize().height * 0.04)),
                    new Point((int) (this.getSize().width * 0.05),
                            this.login.getSize().height + (int) (this.getSize().height * 0.05)));

            String cargo[][] = Consultas.getConsultas().getEmpleadoCons().buscar(6,
                    Procesos.getProcesos().getLogin().getCedula());
            for (int i = 0; i < cargo.length; i++) {
                for (int j = 0; j < cargo[0].length; j++) {

                    if (cargo[i][j].equalsIgnoreCase("empleado")) {
                        this.rol.addItem("EMPLEADO");
                        this.rol.setSelectedItem("EMPLEADO");
                    }
                    if (cargo[i][j].equalsIgnoreCase("administrador")) {
                        this.rol.addItem("EMPLEADO");
                        this.rol.addItem("ADMINISTRADOR");
                        this.rol.setSelectedItem("ADMINISTRADOR");
                    }
                }
            }

            this.rol.addActionListener(this);
        }

        private void eventos() {

            for (int i = 0; i < items.length; i++) {
                this.items[i].addActionListener(this);
                ;
            }
        }

        private String nombresItem(int i) {
            switch (i) {
                case 1:
                    return "venta";

                case 2:
                    return "compras";
                    
                case 3:
                    return "bodega";

                case 4:
                    return "tienda";

                case 5:
                    return "empleado";

                case 6:
                    return "proveedor";

                case 7:
                    return "usuario";

                case 8:
                    return "caja";
           
                case 9:
                    return "salir";
            }
            return "";
        }

        public void actionPerformed(ActionEvent evento) {

            estadisticas.removeAll();
            estadisticas.cambiaPaneles(1);

            for (int i = 0; i < items.length; i++) {

                this.items[i].setEventoMouse(true);
                this.items[i].setForeground(confi.getBlanco());

                if ((items.length - 1) == i && this.items[i] == evento.getSource()) {

                    Consultas.getConsultas().getUsuarioCons()
                            .Cerrar_sesion(Procesos.getProcesos().getLogin().getCedula());
                    Procesos.getProcesos().getLogin().setLogin();

                    paneles.removeAll();
                    paneles.agregaPanel(1);
                }
                if (this.items[i] == evento.getSource()) {
                    this.items[i].setEventoMouse(false);
                    estadisticas.removeAll();
                    estadisticas.cambiaPaneles(i + 1);
                    this.items[i].setForeground(confi.getNegro());
                }
                this.items[i].setEventoMouse(true);
            }

            if (this.rol == evento.getSource()) {
                String nombre = String.valueOf(this.rol.getSelectedItem());
                if (nombre.equalsIgnoreCase("EMPLEADO")) {
                    PanelesGui.getPanelesGui(null).removeAll();
                    PanelesGui.getPanelesGui(null).agregaPanel(3);
                    this.rol.setSelectedItem("EMPLEADO");

                }
                if (nombre.equalsIgnoreCase("ADMINISTRADOR")) {
                    PanelesGui.getPanelesGui(null).removeAll();
                    PanelesGui.getPanelesGui(null).agregaPanel(2);
                    this.rol.setSelectedItem("ADMINISTRADOR");

                }
            }

        }
    }

    private class estadisticas extends JPanel {

        private int base, altura;
        private JPanel aux;

        public estadisticas() {

            this.base = (int) (dimension.width * 0.88);
            this.altura = dimension.height;

            this.setLayout(null);
            this.setBackground(confi.getBlanco());
            this.setSize(this.base, this.altura);
            this.setLocation(menu.getSize().width, 0);
        }

        public void cambiaPaneles(int id) {
            pestanaSup = new PestanaGui(this.getSize(), 0, 0);
            pestanaInf = new PestanaGui(this.getSize(), 0, 0);
            switch (id) {
                case 1:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "informacion de ventas" },
                            new PestanaGui[] { pestanaSup.pestanasInicioVenta() });
                    break;
                case 2:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "historial de compras","registrar compras" },
                            new PestanaGui[] { pestanaSup.pestanasCompras(), pestanaInf.pestanasCompras(21, 20) });
                    break;
                case 3:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "historial de bodega" },
                            new PestanaGui[] { pestanaSup.pestanasBodega()});
                    break;
                case 4:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "registro de productos de la tienda", "informacion de productos pendientes" },
                            new PestanaGui[] { pestanaSup.pestanasTienda(), pestanaInf.pestanasHistorial(33) });
                    break;
                case 5:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "registrar empleado", "informacion de empleado" }, new PestanaGui[] {
                                    new PestanaGui(this.getSize(), 13, 17), pestanaInf.pestanasHistorial(8) });
                    break;
                case 6:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "registrar proveedor", "informacion de proveedor" }, new PestanaGui[] {
                                    new PestanaGui(this.getSize(), 14, 18), pestanaInf.pestanasHistorial(9) });
                    break;

                case 7:

                    this.aux = new ArquitectucaInicioGui(this.getSize(), new Point(0, 0),
                            new String[] { "registro de usuario", "historial de usuario" }, new PestanaGui[] {
                                    new PestanaGui(this.getSize(), 15, 19), pestanaInf.pestanasHistorial(10) });
                    break;

                case 8:
                    this.aux = new Caja(this.getSize());
                    break;

            }
            this.add(this.aux);
            this.updateUI();
        }
    }
}