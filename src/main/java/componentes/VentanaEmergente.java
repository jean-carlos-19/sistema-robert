package componentes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;

public class VentanaEmergente extends JDialog implements ActionListener, MouseListener, MouseMotionListener {

    private Point ubicacion;
    private JPanel[] Lamina;
    private JFrame ventana;
    private JButton boton;
    private ImageIcon icono;
    private int x;
    private int y;
    private Configuracion configuracion;

    public VentanaEmergente(JFrame ventana, Dimension dimension, JPanel lamnina) {

        super(ventana, false);
        this.ventana = ventana;
        this.setUndecorated(true);
        this.setSize(dimension.width, dimension.height + (int) (ventana.getSize().height * 0.50));
        this.ubicacion = new Point((ventana.getSize().width - dimension.width) / 2,
                (ventana.getSize().height - dimension.height) / 2);
        this.setLocation(ubicacion);
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.atributos();
        this.Lamina[1].add(lamnina);
        this.setVisible(true);
    }

    private void atributos() {
        this.configuracion = new Configuracion();
        this.setLamina();
        this.setBotones();
    }

    private void setLamina() {
        this.Lamina = new JPanel[2];
        for (int i = 0; i < Lamina.length; i++) {
            this.Lamina[i] = new JPanel();
            this.Lamina[0].setBackground(configuracion.getVerde());
            this.Lamina[i].setLayout(null);
        }
        this.Lamina[0].setSize(ventana.getSize().width, (int) (ventana.getSize().height * 0.05));
        this.Lamina[1].setSize(this.getSize().width, (int) (this.getSize().height * 0.95));

        this.Lamina[0].setLocation(0, 0);
        this.Lamina[1].setLocation(0, this.Lamina[0].getSize().height);

        this.add(this.Lamina[0], BorderLayout.NORTH);
        this.add(this.Lamina[1], BorderLayout.CENTER);
    }

    private void setBotones() {

        this.boton = new JButton();
        this.boton.setSize(new Dimension(50, this.Lamina[0].getSize().height));
        this.boton.setLocation(this.getSize().width - 50, 0);
        this.boton.setFocusable(false);
        this.boton.setBorder(null);
        this.boton.addActionListener(this);
        this.boton.addMouseListener(this);
        this.boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.boton.setBackground(this.Lamina[0].getBackground());
        this.icono = new ImageIcon("src//main//java//componentes//close.png");
        this.icono.setImage(this.icono.getImage().getScaledInstance(20, 20, 100));
        this.boton.setIcon(this.icono);
        this.Lamina[0].add(this.boton);

    }

    public void setPanel(JPanel hijo) {

        hijo.setLocation(0, 0);
        this.Lamina[1].add(hijo);
        this.Lamina[1].updateUI();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

        if (this.boton == e.getSource()) {
            this.boton.setBackground(configuracion.getBlanco());
        }
    }

    public void mouseExited(MouseEvent e) {

        if (this.boton == e.getSource()) {
            this.boton.setBackground(configuracion.getAzul());
        }
    }

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        this.setLocation(this.getLocation().x + e.getX() - x, this.getLocation().y + e.getY() - y);
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (this.boton == e.getSource()) {
            dispose();
        }

    }

}