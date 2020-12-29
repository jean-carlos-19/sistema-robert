package componentes;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class Encabezado implements TableCellRenderer{
    
    private Configuracion confi= new Configuracion();
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		 JComponent jcomponent = null;
         
	        if( value instanceof String ) {
	            jcomponent = new JLabel((String) value);
	            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.CENTER );
	            ((JLabel)jcomponent).setSize( 30, jcomponent.getWidth() );   
	            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth())  );
	        }         
	    
	        jcomponent.setBorder(null);
	        jcomponent.setOpaque(true);
	        jcomponent.setBackground( confi.getVerde2());
	        jcomponent.setForeground(confi.getBlanco());
	        jcomponent.setFont(confi.getEtiquetas());
	        return jcomponent;
		
	}
}