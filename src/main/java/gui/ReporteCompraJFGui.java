package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import componentes.Dialogo;
import consultas.Consultas;
import procesos.Fecha;

public class ReporteCompraJFGui {

	private ImageIcon imagen = new ImageIcon();
	private Dimension dimesion;
	private JPanel panel;

	public ReporteCompraJFGui() {

		this.atributos();
		DefaultPieDataset data = new DefaultPieDataset();
		String datos[][] = Consultas.getConsultas().getComprasCons().buscar(3, null);
		
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[0].length; j++) {
				
				data.setValue(datos[i][1], Double.valueOf(datos[i][2]));
			} 
		}

		String total[][] = Consultas.getConsultas().getComprasCons().buscar(6, null);
		if(total.length == 0)
			return;
		JFreeChart chart = ChartFactory.createPieChart("TOTAL "+total[0][0], data, true, // legend?
				true, // tooltips?
				false // URLs?
		);

		this.panel = new ChartPanel(chart);
		this.panel.setSize(this.dimesion);
		Dialogo d = new Dialogo(Marco.getGUI(), this.dimesion, this.panel);

	}

	private void atributos() {
		this.dimesion = new Dimension();
		this.dimesion.width = (int) (Marco.getGUI().getSize().width * 0.80);
		this.dimesion.height = (int) (Marco.getGUI().getSize().height * 0.80);
	}
}
