package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.awt.Dimension;

public class PestanaDeudores extends JTabbedPane {

    private Dimension tamano;

    public PestanaDeudores(Dimension d, int id, int i) {
        this.setLocation(0,0);
        this.setSize(d);
        this.tamano = d;
        this.pestanas(id, i);
    }

    private PestanaDeudores pestanas(int id, int i) {
        this.removeAll();
        this.add("deudor".toUpperCase(), this.laminas(i));
        this.add("producto".toUpperCase().toUpperCase(), this.laminas(id));
        this.add("abonar".toUpperCase().toUpperCase(), this.laminas(id));
        this.add("historial".toUpperCase().toUpperCase(), this.laminas(id));
        this.updateUI();
        return this;
    }

    private JPanel laminas(int id) {

        switch(id){
            case 1:
                return new DeudoresFacturaGui(this.tamano);
            case 2:
            	return new BuscarProducto(this.tamano);

        }
        return null;
    }
}