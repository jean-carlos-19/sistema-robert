package gui;

import java.awt.Dimension;

import javax.swing.JPanel;

public class OrganizarBodea extends JPanel{

	public OrganizarBodea(Dimension d) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize((int) (d.getWidth()), (int) (d.getHeight()));
        OrganizarProducto p = new OrganizarProducto(d,1);
        p.updateUI();
        this.add(p);
       
    }
}
