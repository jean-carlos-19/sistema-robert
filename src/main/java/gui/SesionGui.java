package gui;

import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class SesionGui extends JPanel{
    
    
    private JTextField campos[][];
    private JLabel caracteristicas[][];
    private JButton enviar;
    private Dimension dimension;
    private Point ubicacion,distancia;
    private JTextArea areaTexto;
    private int contador;

    public SesionGui(Dimension dimension, Point ubicacion) {
        this.setLayout(null);
        this.setSize(dimension);
        this.setLocation(ubicacion);
        this.setBackground(Color.yellow);
        this.componentes();
    }

    public void setdato(String dato [][]){

        for (int i = 0; i < campos.length; i++) {
            for (int j = 0; j < campos.length; j++) {
                this.campos[i][j].setText(dato[i][j]);
            }
        }
        this.areaTexto.setText(dato[dato.length -1 ][dato[0].length -1]);
    }

    private void componentes() {

        this.atributos();
        this.recorrido(); 
        this.enviar();
    }
    
    private void atributos() {

        this.contador = 1;
        this.areaTexto = new JTextArea();
        this.campos = new JTextField[5][2];
        this.caracteristicas = new JLabel[5][2];
        this.dimension = new Dimension((int)(this.getSize().width * 0.1875),(int)(this.getSize().height * 0.07));
        this.distancia = new Point((int)(this.getSize().width * 0.05),(int)(this.getSize().height * 0.05));
        this.ubicacion =  this.distancia;
    }

    private void recorrido(){

        for (int i = 0; i < caracteristicas.length; i++) {
            for (int j = 0; j < caracteristicas[0].length; j++) {
                this.caracterisicas(i, j);
                this.campos(i,j);
                if(j == (caracteristicas[0].length - 1 )) {
                    this.distancia.x = this.ubicacion.x;
                    this.distancia.y =  this.caracteristicas[i][j].getLocation().y + (dimension.height + ubicacion.y);
                }
            }
        }
    }
    private void campos(int i , int j) {
                        
        this.campos[i][j] = new JTextField();
        this.campos[i][j].setSize(dimension); 
        this.campos[i][j].setLocation(getDistanciaCampo(i, j)); 
        this.add(this.campos[i][j]);
    }
    private void caracterisicas(int i, int j){

        this.caracteristicas[i][j] = new JLabel(NombreCaracteristica(this.contador));
        this.caracteristicas[i][j].setOpaque(true);
        this.caracteristicas[i][j].setBackground(Color.red);
        this.caracteristicas[i][j].setSize(dimension); 
        this.caracteristicas[i][j].setLocation(getDistancia(i, j)); 
        this.add(this.caracteristicas[i][j]);
        if(i == 4 && j == 1){
           
            this.remove( this.caracteristicas[i][j]);
        }
       this.contador++;
        
    }
    private Point getDistancia(int i, int j){

        if(j > 0 && j < caracteristicas[0].length){

            this.distancia = new Point( this.caracteristicas[i][j-1].getLocation().x + ( dimension.width * (2) ) + ubicacion.x * (2) , distancia.y );
        }
       
        
        return this.distancia;
    }

    private Point getDistanciaCampo(int i, int j){

        if(j >= 0 && j < caracteristicas[0].length){

            this.distancia = new Point((this.caracteristicas[i][j].getLocation().x + dimension.width  ) + ubicacion.x , distancia.y );
            
        }
        
        
        return this.distancia;
    }

    private void enviar(){

        this.enviar = new JButton("enviar");
        this.enviar.setSize(this.dimension);
        this.enviar.setLocation((this.areaTexto.getLocation().x  + this.areaTexto.getSize().width) - this.enviar.getSize().width, 
        (this.areaTexto.getLocation().y  + this.areaTexto.getSize().height + this.ubicacion.y));
        this.add(this.enviar);
    }
    private void cancelar(){
        
    }

		
    private String NombreCaracteristica(int i) {
        
        switch(i) {
            case 1:
                
                return "usuario".toUpperCase();
            case 2:
                
                return "contraseña".toUpperCase();
            case 3:
                
                return "contraseña".toUpperCase();
    
		}
		return null;
	}
	
	private void limpiar() {
		
		for(int i = 0; i < this.campos.length; i++) {
			for(int j = 0; j < this.campos[0].length; j++) {
				this.campos[i][j].setText("");
			}	
        }
        this.areaTexto.setText("");
	}
}