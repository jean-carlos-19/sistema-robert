package componentes;

import javax.swing.table.DefaultTableModel;

import jdk.nashorn.internal.ir.ForNode;

public class Modelo extends DefaultTableModel {
    private String fila[];
	public Modelo(Object fila [],Object columnas[][]) {
		super();
		this.fila = new String[fila.length];
		this.mayuscula(fila);
		this.setDataVector(columnas, this.fila);
	}
	public void setModelo(Object fila [],Object columnas[][]) {
		this.fila = new String[fila.length];
		this.mayuscula(fila);
		this.setDataVector(columnas, this.fila);
	}
	private void mayuscula(Object f[]){
		
		for (int i = 0; i < fila.length; i++) {
			fila[i] = ((String)(f[i])).toUpperCase();
		}
	}
	
	public boolean isCellEditable(int f,int c){
		
		if(f == 0 && c == 0)
			return true;
		return false;
    }
}