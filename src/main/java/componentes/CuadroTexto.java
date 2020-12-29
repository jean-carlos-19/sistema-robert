package componentes;


import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;

public class CuadroTexto extends JTextField{

	private Configuracion configuracion;
	public static final int basica = 1;
	public static final int clasica = 2;
	public static final int moderna = 3;
	
	public CuadroTexto(int ajuste,boolean habilitado) {
		
		this.atributos();
		this.ajustes(ajuste,habilitado);
		this.setLocation(0, 0);
		this.setSize(300, 40);
		this.updateUI();
	}
	public CuadroTexto(int ajuste,boolean habilitado,Dimension dimension, Point ubicacion) {
		
		this.atributos();
		this.ajustes(ajuste,habilitado);
		this.setLocation(ubicacion);
		this.setSize(dimension);
		this.updateUI();
	}

	public CuadroTexto(int ajuste,boolean habilitado, JTextField Placholder,Dimension dimension, Point ubicacion) {
		
		this.atributos();
		this.ajustes(ajuste,habilitado);
		this.setLocation(ubicacion);
		this.setSize(dimension);
		this.updateUI();
	}
	public void setTamano(Dimension dimension){
		this.setSize(dimension);
	}
	public void setUbicacion(Point ubicacion){
		this.setLocation(ubicacion);
	}
	private void setEstilo(Color fondo, Color letra, Font estilo, Border border){

			
		this.setBackground(fondo);
		this.setForeground(letra);
		this.setFont(estilo);
		this.setBorder(border);
	}
	public void ajustes(int ajuste,boolean habilitado) {
		
		if (!habilitado) {
			this.setEditable(false);
		}
		switch(ajuste) {
		
			case 1:
				
				this.setEstilo(this.configuracion.getBlanco(), this.configuracion.getNegro(), this.configuracion.getTextos(), BorderFactory.createLineBorder(this.configuracion.getTomate(), 2));
				break;
				
			case 2:
				
				this.setEstilo(this.configuracion.getBlanco(), this.configuracion.getNegro(), this.configuracion.getTextos(), BorderFactory.createLineBorder(this.configuracion.getVerde2(), 2));
				break;
			case 3:
				this.setEstilo(this.configuracion.getBlanco(), this.configuracion.getNegro(), this.configuracion.getTextos(), BorderFactory.createLineBorder(this.configuracion.getVerde(), 2));
				break;
			case 4:
				
				this.setEstilo(this.configuracion.getBlanco(), this.configuracion.getNegro(), this.configuracion.getTextos(), BorderFactory.createLineBorder(this.configuracion.getVerde2(), 2));
				break;
				
			
				
		}
		this.updateUI();	
	}

	private void atributos() {
		
		this.configuracion = new Configuracion();
	}
}
