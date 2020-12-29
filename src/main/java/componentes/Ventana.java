package componentes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import consultas.Consultas;
import procesos.Procesos;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class Ventana extends JFrame implements MouseListener, MouseMotionListener, ActionListener {

    private Toolkit herramientas;
    private JPanel Lamina[];
    private int x, y;
    private JButton boton[];
    private ImageIcon icono;
    private ImageIcon logo;
    private Configuracion configuracion;
    private Rectangle winSize;

    public Ventana() {

        this.winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.herramientas = getToolkit();
        this.setLocationRelativeTo(null);
        this.setLocation(0, 0);
        this.setSize(this.herramientas.getScreenSize().width, (this.winSize.height));
        this.logo = new ImageIcon("src//main//java//img//icono.png");
        this.setIconImage(this.logo.getImage());
        this.setResizable(false);
        this.setUndecorated(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.atributos();
    }

    private void atributos() {
        this.configuracion = new Configuracion();
        this.setLayout(null);
        this.setLamina();
        this.setBotones();
    }

    private void setLamina() {
        this.Lamina = new JPanel[2];
        for (int i = 0; i < Lamina.length; i++) {
            this.Lamina[i] = new JPanel();
            this.Lamina[0].setBackground(configuracion.getVerde());
            if (i == 1) {
                this.Lamina[i].setLayout(new BorderLayout());
            } else {
                this.Lamina[i].setLayout(null);
            }

        }
        this.Lamina[0].setSize(this.getSize().width, (int) (this.getSize().height * 0.05));
        this.Lamina[1].setSize(this.getSize().width, (int) (this.getSize().height * 0.95));

        this.Lamina[0].setLocation(0, 0);
        this.Lamina[1].setLocation(0, this.Lamina[0].getSize().height);

        this.add(this.Lamina[0], BorderLayout.NORTH);
        this.add(this.Lamina[1], BorderLayout.CENTER);
    }

    private void setBotones() {
        this.boton = new JButton[2];
        for (int i = 0; i < boton.length; i++) {
            this.boton[i] = new JButton();
            this.boton[i].setSize(new Dimension(50, this.Lamina[0].getSize().height));
            this.boton[i].setLocation(this.Lamina[0].getSize().width - (this.boton[i].getSize().width * (i + 1)), 0);
            this.boton[i].setFocusable(false);
            this.boton[i].setBorder(null);
            this.boton[i].addActionListener(this);
            this.boton[i].addMouseListener(this);
            this.boton[i].setBackground(this.Lamina[0].getBackground());

            if (i % 2 == 0) {
                this.icono = new ImageIcon("src//main//java//componentes//close.png");
                this.icono.setImage(this.icono.getImage().getScaledInstance(20, 20, 100));
                this.boton[i].setIcon(this.icono);

            } else {
                this.icono = new ImageIcon("src//main//java//componentes//minus1.png");
                this.icono.setImage(this.icono.getImage().getScaledInstance(20, 20, 100));
                this.boton[i].setIcon(this.icono);
            }
            this.Lamina[0].add(this.boton[i]);
        }
    }

    public void setPanel(JPanel hijo) {

        this.Lamina[1].add(hijo, BorderLayout.CENTER);
        this.Lamina[1].updateUI();
    }

    public Dimension getTamano() {
        return this.Lamina[1].getSize();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

        for (int i = 0; i < this.boton.length; i++) {
            if (this.boton[i] == e.getSource()) {
                this.boton[i].setBackground(configuracion.getBlanco());
            }

        }
    }

    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < this.boton.length; i++) {
            if (this.boton[i] == e.getSource()) {
                this.boton[i].setBackground(this.Lamina[0].getBackground());
                this.boton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
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
        if (this.boton[0] == e.getSource()) {
            Consultas.getConsultas().getUsuarioCons().Cerrar_sesion(Procesos.getProcesos().getLogin().getCedula());
            Procesos.getProcesos().getLogin().setLogin();
            System.exit(0);
        }
        if (this.boton[1] == e.getSource()) {
            this.setExtendedState(1);
        }
    }
}