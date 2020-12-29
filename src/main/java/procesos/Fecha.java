package procesos;


import java.util.Calendar;

import javax.swing.JComboBox;

public class Fecha {

	private Calendar fecha ;
	private JComboBox<String> lista;
	private int dia;
	private int mes ;
	private int ano;
	private int i;
	private String hoy;
	
	public Fecha() {
		
		fecha = Calendar.getInstance();
		this.mes = fecha.get(Calendar.MONTH) + 1;
		this.dia = fecha.get(Calendar.DATE) ;
		this.ano = fecha.get(Calendar.YEAR);
		this.i = 1;
		this.hoy =this.ano+"-"+this.mes+"-"+this.dia;
		this.lista = new JComboBox<String>();
		
		
		this.mesDelAno();
	}
	
	public Calendar getFecha() {
		return fecha;
	}

	private void mesDelAno() {
		
		this.lista.addItem("hoy".toUpperCase());
		
		while(this.i <= this.mes) {
			
			this.lista.addItem(this.mes(i));
			this.i++;
		}
	}
	public String mes(int id) {
		
		switch(id) {
			case 1:
				
				return "enero".toUpperCase();
			case 2:
				
				return "febrero".toUpperCase();
			case 3:
				
				return "marzo".toUpperCase();
			case 4:
				
				return "abril".toUpperCase();
			case 5:
				
				return "mayo".toUpperCase();
			case 6:
				
				return "junio".toUpperCase();
			case 7:
				
				return "julio".toUpperCase();
			case 8:
				
				return "agosto".toUpperCase();
			case 9:
				
				return "septiembre".toUpperCase();
			case 10:
				
				return "octubre".toUpperCase();
			case 11:
				
				return "nomviembre".toUpperCase();
			case 12:
				
				return "diciembre".toUpperCase();
		}
		return null;
	}

	public JComboBox<String> mesDelAno2() {
		
		this.i = 1;
		this.lista = new JComboBox<String>();
		while(this.i <= this.mes) {
			
			this.lista.addItem(this.mes(i));
			this.i++;
		}
		return this.lista;
	}
	public JComboBox<String> getLista() {
		return lista;
	}
	public int obtenMes(String lista) {
		
		if(lista.equalsIgnoreCase("enero")) {
			return 1;
		}
		if(lista.equalsIgnoreCase("febrero")) {
			return 2;
		}
		if(lista.equalsIgnoreCase("marzo")) {
			return 3;
		}
		if(lista.equalsIgnoreCase("abril")) {
			return 4;
		}
		if(lista.equalsIgnoreCase("mayo")) {
			return 5;
		}
		if(lista.equalsIgnoreCase("junio")) {
			return 6;
		}
		if(lista.equalsIgnoreCase("julio")) {
			return 7;
		}
		if(lista.equalsIgnoreCase("agosto")) {
			return 8;
		}
		if(lista.equalsIgnoreCase("septiembre")) {
			return 9;
		}
		if(lista.equalsIgnoreCase("octubre")) {
			return 10;
		}
		if(lista.equalsIgnoreCase("noviembre")) {
			return 11;
		}
		if(lista.equalsIgnoreCase("diciembre")) {
			return 12;
		}

		return 0;
	}
	public String getHoy() {
		return hoy;
	}
	public int getMesActual() {
		return mes;
	}
}
