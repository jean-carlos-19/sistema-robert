package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import componentes.Etiqueta;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.BorderLayout;

public class Contenedor extends JPanel{
    
    private Dimension tamano;
    private Etiqueta titulo;
    
    public Contenedor(Dimension d, Point u){
        this.tamano = d;
        this.setLocation(u);
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.componentes();
        this.agregar();
    }
    
    private void componentes() {
        this.titulo = new Etiqueta("ninguno".toUpperCase(), JLabel.RIGHT);
    }
    private void agregar() {
        this.add(this.titulo,BorderLayout.NORTH);
    }
    public void settitulo(String titulo){
        this.titulo.setText(titulo.toUpperCase());
        this.titulo.updateUI();
        this.updateUI();
    }
    public void agregarPanel(JTabbedPane p){
        this.add(p, BorderLayout.CENTER);
        this.updateUI();
    }
}