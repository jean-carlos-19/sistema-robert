package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.awt.Dimension;

public class PestanaVenta extends JTabbedPane {

    private Dimension tamano;

    public PestanaVenta(Dimension d, int id, int i) {
        this.setLocation(0, 0);
        this.setSize(d);
        this.tamano = d;
        this.pestanas(id, i);
    }

    private PestanaVenta pestanas(int id, int i) {
        this.removeAll();
        this.add("factura".toUpperCase(), this.laminas(i));
        this.add("producto".toUpperCase().toUpperCase(), this.laminas(id));
        this.add("organizar".toUpperCase().toUpperCase(), this.laminas(3));
        this.updateUI();
        return this;
    }

    private JPanel laminas(int id) {

        switch (id) {
            case 1:
                return VentaGui.getVentaGui(this.tamano);
            case 2:
                return new BuscarProducto(this.tamano);
            case 3:
                return new OrganizarBodea(this.tamano);

        }
        return new JPanel();
    }
}