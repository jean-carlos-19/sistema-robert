package componentes;


import java.awt.Color;
import java.awt.Font;

public class Configuracion {
	
	private Color tomate;
	private Color blanco;
	private Color negro;
	private Color azul;
	private Color plomo;
	private Font titulo;
	private Font boton;
	private Font etiquetas;
	private Font texto;
	private Color plomo2;
	private Color azulBjao;
	private Color fondo;
	private Color verde;
	private Color verde2;
	
	public Configuracion() {
		
		this.tomate = new Color(231, 76, 60);
		this.blanco = new Color(223, 230, 233);
		this.negro = new Color(53, 59, 72);
		this.azul = new Color (9, 132, 227);
		this.azulBjao = new Color(157, 170, 224 );
		this.plomo = new Color(127, 140, 141);
		this.plomo2 = new Color(128, 200, 200);
		this.verde = new Color(26, 188, 156);
		this.verde2 = new Color(7, 153, 146);
		
		this.titulo = new Font("TREBUCHET MS",Font.BOLD,18);
		this.boton = new Font("TREBUCHET MS",Font.BOLD,17);
		this.etiquetas = new Font("TREBUCHET MS",Font.BOLD,17);
		this.texto = new Font("TREBUCHET MS",Font.PLAIN,17);
		this.fondo = new Color(76, 85, 94);
		
	}
	public Color getVerde2() {
		return verde2;
	}
	public Color getVerde() {
		return verde;
	}
	public Font getTitulo() {
		return titulo;
	}
	public Font getTexto() {
		return texto;
	}
	public Color getFondo() {
		return fondo;
	}
	public void setFondo(Color fondo) {
		this.fondo = fondo;
	}
	public Color getAzulBjao() {
		return azulBjao;
	}
	public void setAzulBjao(Color azulBjao) {
		this.azulBjao = azulBjao;
	}
	public Color getPlomo2() {
		return plomo2;
	}
	public void setPlomo2(Color plomo2) {
		this.plomo2 = plomo2;
	}
	public Color getPlomo() {
		return plomo;
	}
	public Color getTomate() {
		return this.tomate;
	}
	
	public Color getBlanco() {
		return this.blanco;
	}
	
	public Font getBoton() {
		return this.boton;
	}
	public Color getNegro() {
		return this.negro;
	}
	public Font getEtiquetas() {
		return this.etiquetas;
	}
	public Font getTextos() {
		return this.texto;
	}
	public Font getTitulos() {
		return this.titulo;
	}
	public Color getAzul() {
		return this.azul;
	}
}
