package componentes;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Dimension;
import java.awt.Point;

public class Tabla extends JTable {
    
    private int tamano[];
	private JTableHeader jtableHeader;
    public Tabla(DefaultTableModel modelo, int tamano[],Dimension dimension,Point ubicacion,int icono, boolean activado){

        this.tamano = tamano;
        this.setModel(modelo);
        this.setSize(dimension);
        this.setLocation(ubicacion);
        this.setRowHeight(35);
        this.estilo(icono,activado);
    }
    public void setModelo(DefaultTableModel modelo,int icono, boolean activado) {
    	this.setModel(modelo);
    	 this.estilo(icono,activado);
    }
    public void estilo(int icono, boolean activado){
        for (int i = 0; i < tamano.length; i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(tamano[i]);
            this.getColumnModel().getColumn(i).setCellRenderer(new Celda(icono,activado));
        }
        jtableHeader = this.getTableHeader();
	    jtableHeader.setDefaultRenderer(new Encabezado());
	    this.setTableHeader(jtableHeader);
        this.updateUI();
    }
}