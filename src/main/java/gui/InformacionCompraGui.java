package gui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

public class InformacionCompraGui extends JPanel{

	private Dimension  d1;
	private Point l1,l2;
	
	public InformacionCompraGui(Dimension d) {
		
		this.setSize(d);
		this.setLocation(0, 0);
		this.atributos();
	}
	private void atributos() {
		
		this.d1 = new Dimension(this.getWidth(),(int) (this.getHeight() * 0.40));

		this.l1 = new Point(0,(int)(this.getHeight() * 0.10));
		this.l2 = new Point(0,(int)(this.getHeight() * 0.10));
	}
	private void contenido() {
		
	}
}
