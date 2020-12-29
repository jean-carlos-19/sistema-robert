
package gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

public class ArquitecturaDeudoresGui extends JPanel {

    public ArquitecturaDeudoresGui(Dimension d, JTabbedPane lamina) {

        this.setLayout(null);
        this.setLocation(0, 0);
        this.setSize(d);
        this.add(lamina);
    }
}