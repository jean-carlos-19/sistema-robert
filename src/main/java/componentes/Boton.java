package componentes;


import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;

public class Boton extends JButton {

	private Configuracion configuracion;

	//constructores
	public Boton() {
		
		this.atributos();
		this.setFocusable(false);
		this.setBorder(null);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new eventoMouse(this));
		this.setEstilo(this.configuracion.getBlanco(), this.configuracion.getBlanco(), this.configuracion.getBoton(),null);
		
	}
	public Boton(String texto,int ajuste, boolean habilitado) {
		
		this.atributos();
		this.setFocusable(false);
		this.setBorder(null);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new eventoMouse(this));
		this.setAjuste(texto.toUpperCase(),ajuste,habilitado);
		
	}
	public Boton(String texto,int ajuste, boolean habilitado, Dimension dimension,Point ubicacion) {
		
		this.atributos();
		this.setLocation(ubicacion);
		this.setSize(dimension);
		this.setFocusable(false);
		this.setBorder(null);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseListener(new eventoMouse(this));
		this.setAjuste(texto,ajuste,habilitado);
	}

	//metodos
	public void setTamano(Dimension dimension){
		this.setSize(dimension);
	}
	public void setUbicacion(Point ubicacion){
		this.setLocation(ubicacion);
	}
	public void setEventoMouse(boolean decision) {
		if(decision) {
			this.addMouseListener(new eventoMouse(this));
		}else {
			this.addMouseListener(null);
		}
	}
	public void setAjuste(String texto, int ajuste, boolean habilitado) {
		
		this.setText(texto.toUpperCase());
		if( !habilitado ) {
			this.setEnabled(false);
		}
		switch(ajuste) {
			case 1:
				
				this.setEstilo(this.configuracion.getVerde2(), this.configuracion.getBlanco(), this.configuracion.getBoton(),null);
				break;
			case 2:
				
				this.setEstilo(this.configuracion.getTomate(), this.configuracion.getBlanco(), this.configuracion.getBoton(),null);
				break;
				
			case 3:
				
				this.setEstilo(this.configuracion.getPlomo(), this.configuracion.getBlanco(), this.configuracion.getBoton(),null);
				break;
			case 4:
				
				this.setEstilo(this.configuracion.getVerde2(),this.configuracion.getBlanco(), this.configuracion.getBoton(),null);
				break;
		}
		
	}
	public void setAjusteEspecial(){
		this.setOpaque(true);
		this.setBorder(null);
		this.setFocusable(false);
		
	}
	private void setEstilo(Color fondo, Color letra, Font estilo,Border border){
		this.setBackground(fondo);
		this.setForeground(letra);
		this.setFont(estilo);
		this.setBorder(border);
	}
	private void atributos() {
		
		this.configuracion = new Configuracion();
	}
	
	// clase evento de mouse esitlo
	public class eventoMouse extends MouseAdapter{
		
		private Boton padre;
		private Color fondo,letra;
		private Border border;

		public eventoMouse(Boton padre) {
			this.padre = padre; 
			this.fondo =null;
			this.letra = null;
			this.border = null;
		}
		private void fondo(){
			this.fondo = padre.getBackground();
			this.letra = padre.getForeground();
			this.border = BorderFactory.createLineBorder(fondo, 2);
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			this.fondo();
			this.estilo(this.letra,this.fondo,this.border);	
			this.padre.setBorder(border);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
			this.estilo(this.fondo,this.letra,null);
			this.padre.setBorder(null);
			
		}
		private void estilo(Color fondo, Color letra, Border border){

			this.padre.setBackground(fondo);
			this.padre.setForeground(letra);
			this.padre.updateUI();
		}
	}

	
}
