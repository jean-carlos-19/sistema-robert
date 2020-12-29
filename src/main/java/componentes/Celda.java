package componentes;


import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Celda  extends DefaultTableCellRenderer implements TableCellRenderer {
    
  

	
	private Configuracion confi= new Configuracion();
	private String tipo;
	private Component c;
	private JLabel etiqueta;
	private JLabel Component;
	private static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	private boolean activado;
	private Boton opcion;
	private ImageIcon icono;
	public Celda(int icono, boolean activado) {
		this.activado = activado;
		
		this.opcion = new Boton();
		this.opcion.setIcon(this.icono(icono));
	}
	
	private ImageIcon icono(int icono) {
		
		switch(icono) {
			case 1:
				this.icono = new ImageIcon("src//main//java//img//guardar.png");
				this.icono.setImage((this.icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
				break;
			case 2:
				this.icono = new ImageIcon("src//main//java//img//eliminar.png");
				this.icono.setImage((this.icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
				break;
			case 3:
				this.icono = new ImageIcon("src//main//java//img//lupa.png");
				this.icono.setImage((this.icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
				break;
		}
		
		return this.icono;
	}
	
	
	 @Override
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		 	
		 	this.c = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		 	c.setFont(confi.getEtiquetas());
		 	if(activado) {
		 		 if (column == table.getModel().getColumnCount() - 1) {
		            return this.opcion;
		           
		 		 }
		 	}
		 	
		 	
		 	
	        if (row == 0 && selected == true) {
	            c.setBackground(confi.getAzulBjao());
	            c.setForeground(confi.getNegro());
	        
	        } else if (row != 0 && selected == true) {
	            c.setBackground((confi.getAzulBjao()));
	            c.setForeground(confi.getNegro());
	           
	        } else {
	            c.setBackground(confi.getBlanco());
	            c.setForeground(confi.getNegro());
	        }
	        
	        ((DefaultTableCellRenderer)c).setHorizontalAlignment(SwingConstants.CENTER);
	       

		 	
	  return this.c; 
	 }
}