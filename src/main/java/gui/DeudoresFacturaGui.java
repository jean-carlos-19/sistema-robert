package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import componentes.CuadroTexto;
import componentes.Etiqueta;

public class DeudoresFacturaGui extends JPanel{
    
    private JPanel izquierda,derecha;
    private ventaTab tabla;
    private Etiqueta titulo[];
    private CuadroTexto texto[];

    public DeudoresFacturaGui(Dimension d){
        
        this.setLayout(null);
        this.setLocation(0,0);
        this.setSize(d);
        this.componentes();
        this.estructura();
        this.tabla();
        this.etiquetas();
        this.textos();
        this.agregar();

    }
    
    private void etiquetas() {
        for (int i = 0; i < titulo.length; i++) {
            this.titulo[i] = new Etiqueta(this.getNombre(i+1).toUpperCase(), JLabel.LEFT);
            this.titulo[i].setSize(this.derecha.getSize().width,(int)(this.derecha.getSize().height * 0.06));
            this.titulo[i].setLocation(0, i*this.titulo[i].getSize().height*2);
            this.derecha.add(this.titulo[i]);
        }
    }
    private void textos() {
        for (int i = 0; i < texto.length; i++) {
            this.texto[i] = new CuadroTexto(4, true);
            this.texto[i].setSize(this.derecha.getSize().width,(int)(this.derecha.getSize().height * 0.06) );
            this.texto[i].setLocation(0, (this.titulo[i].getLocation().y + this.titulo[i].getSize().height));
            this.derecha.add(this.texto[i]);
        }
    }

    private String getNombre(int id) {
    	switch(id) {
			case 1:
				return "total";
			case 2:
				return "cliente";
			case 3:
				return "nombes y apellidos";
    		
    	}
    	return "no encontrado";
    }
    private void tabla() {
        this.tabla = new ventaTab(this.izquierda.getSize());
        this.tabla.setLocation(0,0);
        this.izquierda.add(this.tabla);
    }

    private void componentes() {

        this.izquierda = new JPanel();
        this.derecha = new JPanel();
        
        this.derecha.setBackground(Color.WHITE);

        this.izquierda.setLayout(null);
        this.derecha.setLayout(null);

        this.titulo = new Etiqueta[3];
        this.texto = new CuadroTexto[3];
        
    }
    private void estructura(){
        this.izquierda.setLocation(0, 0);
        this.izquierda.setSize((int)(this.getSize().width * 0.75),this.getSize().height);
        this.derecha.setLocation(this.izquierda.getLocation().x + this.izquierda.getSize().width, 0);
        this.derecha.setSize((int)(this.getSize().width * 0.25),this.getSize().height);
    }
    private void agregar(){
        this.add(this.izquierda);
        this.add(this.derecha);
    }
}