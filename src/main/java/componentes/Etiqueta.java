package componentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Etiqueta extends JLabel {

	private Configuracion configuracion;
	
	public Etiqueta(String texto,int ajuste) {
		
		this.atributos();
		this.setLocation(0, 0);
		this.setText(texto);
		this.ajustes(ajuste);
	}

	public Etiqueta(int ajuste, ImageIcon imagen,Dimension dimension, Point ubicacion) {
		
		this.atributos();
		this.setLocation(ubicacion);
		this.setSize(dimension);
		this.setIcon(imagen);
		this.ajustes(ajuste);
		
	}
	public Etiqueta(String texto,int ajuste,Dimension dimension, Point ubicacion) {
	
		this.atributos();
		this.setLocation(ubicacion);
		this.setSize(dimension);
		this.setText(texto);
		this.ajustes(ajuste);
	}
	public void atributos() {
		this.configuracion = new Configuracion();
	}
	public void setTamano(Dimension dimension){
		this.setSize(dimension);
	}
	public void setUbicacion(Point ubicacion){
		this.setLocation(ubicacion);
	}
	
	private void setAlineamiento(int alineamiento){

		this.setHorizontalAlignment(alineamiento);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setFont(this.configuracion.getEtiquetas());
		this.setForeground(this.configuracion.getNegro());
	}
	
	private void ajustes(int ajuste) {
		this.setAlineamiento(ajuste);
		this.updateUI();
	}
}
