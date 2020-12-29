package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import componentes.Dialogo;

public class ReporteVentaJFGui {

	private Dimension dimesion;
	private JPanel panel;

	public ReporteVentaJFGui() {

		this.atributos();
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Category 1", 43.2);
		data.setValue("Category 2", 27.9);
		data.setValue("Category 3", 79.5);

		JFreeChart chart = ChartFactory.createPieChart("Sample Pie Chart", data, true, // legend?
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
