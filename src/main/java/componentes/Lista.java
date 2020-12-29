package componentes;

import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.Point;

public class Lista extends JComboBox{
    
    public Lista(Dimension dimension, Point ubicacion){
        
        this.setLocation(ubicacion);
        this.setSize(dimension);
        this.setMaximumRowCount(4);
    }
    public Lista(String dato[],Dimension dimension, Point ubicacion){
          
        this.setLocation(ubicacion);
        this.setSize(dimension);
        this.setDato(dato);
        this.setMaximumRowCount(4);
    }
    public void setDato(String dato[]){
        for (int i = 0; i < dato.length; i++) {
            this.addItem(dato[i]);
        }
        this.updateUI();
    }
    public void setTamano(Dimension dimension){
		this.setSize(dimension);
	}
	public void setUbicacion(Point ubicacion){
		this.setLocation(ubicacion);
	}
}