package gui;



import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import componentes.Boton;

public class GraficoCompras extends JPanel implements ActionListener{
	
	private ImageIcon imagen;
	private Boton estaditsica;
	
	public GraficoCompras(Dimension d) {
		
		this.setLayout(null);
        this.setLocation(0,0);
        this.setOpaque(false);
        this.setSize(d);
        this.atributos();
	}
	private void atributos() {
		this.imagen = new ImageIcon("src//main//java//img//estadistica-puntos-barra.png");
		this.estaditsica = new Boton("estadistica", 1, true,  
				new Dimension((int)(this.getSize().width * 0.1) ,(int)(this.getSize().height * 0.05)),
                new Point((this.getSize().width -  (int)(this.getSize().width * 0.1))/7,(int)(this.getSize().height * 0.02))
                );
		this.estaditsica.addActionListener(this);
		this.add(this.estaditsica);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.imagen.getImage(),(int)(this.getSize().width * 0.25), 0, (int)(this.getSize().width * 0.50), this.getSize().height, this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		new ReporteCompraJFGui();
	}

}


