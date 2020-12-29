package gui;

import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ClienteGui extends JPanel{
    
    
    private JTextField campos[][];
    private JLabel caracteristicas[][];
    private JButton enviar,cancelar;
    private JComboBox<String> genero,cargo;
    private Dimension dimension,dimensionArea;
    private Point ubicacion,distancia;
    private JTextArea areaTexto;
    private int contador;

    public ClienteGui(Dimension dimension, Point ubicacion) {
        this.setLayout(null);
        this.setSize(dimension);
        this.setLocation(ubicacion);
        this.setBackground(Color.yellow);
        this.componentes();
    }

    public void setDato(String dato [][]){

        for (int i = 0; i < campos.length; i++) {
            for (int j = 0; j < campos[0].length; j++) {
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
        this.campos = new JTextField[4][2];
        this.caracteristicas = new JLabel[4][2];
        this.genero  = new JComboBox<String>();
        this.cargo  = new JComboBox<String>();
        this.dimension = new Dimension((int)(this.getSize().width * 0.1875),(int)(this.getSize().height * 0.07));
        this.dimensionArea = new Dimension((int)(this.getSize().width * 0.66),(int)(this.getSize().height * 0.14));
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
        if(i == 2 && j == 1){
            this.genero(this.campos[i][j].getLocation());
            this.remove( this.campos[i][j]);
        }
        
        if(i == 3 && j == 0){
            this.areaTexto(this.campos[i][j].getLocation());
            this.remove( this.campos[i][j]);
        }
        if(i == 3 && j == 1){
           
            this.remove( this.campos[i][j]);
        }
       
      
        
    }
    private void genero(Point ubicacion){
        this.genero.setLocation(ubicacion);
        this.genero.setSize(this.dimension);
        this.add(this.genero);
    }
    private void cargo(Point ubicacion){
        this.cargo.setLocation(ubicacion);
        this.cargo.setSize(this.dimension);
        this.add(this.cargo);
    } 
    private void areaTexto(Point ubicacion){
        this.areaTexto.setLocation(ubicacion);
        this.areaTexto.setSize(this.dimensionArea);
        this.add(this.areaTexto);
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
                
                return "cedula".toUpperCase();
            case 2:
                
                return "nombres".toUpperCase();
            case 3:
                
                return "apellidos".toUpperCase();
            case 4:
                
                return "telefono".toUpperCase();
            case 5:
                
                return "telefono conv".toUpperCase();

            case 6:
                
                return "genero".toUpperCase();
            case 7:
                
                return "direccion".toUpperCase();
    
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