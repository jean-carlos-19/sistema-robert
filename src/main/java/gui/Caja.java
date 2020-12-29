package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import componentes.CuadroTexto;
import componentes.Boton;
import componentes.Configuracion;
import componentes.Etiqueta;
import consultas.Consultas;
import errores.ErrorClases;
import procesos.Procesos;
import procesos.RetirarDinero;

public class Caja extends JPanel implements ActionListener {

	private ImageIcon imagen;
	private Configuracion confi;
	private Etiqueta titulos[], iconos[];
	private Boton botones[];
	private JPanel panles[];
	private CuadroTexto texto[];
	private Dimension dimension;

	public Caja(Dimension d) {

		this.dimension = new Dimension();
		this.dimension.width = d.width / 3;
		this.dimension.height = d.height;
		this.setSize(d);
		this.setLocation(0, 0);
		this.setLayout(new GridLayout(1, 3));
		this.atributos();
		this.componentes();
		this.totolCaja();
	}

	private void atributos() {

		this.botones = new Boton[3];
		this.texto = new CuadroTexto[3];
		this.titulos = new Etiqueta[3];
		this.iconos = new Etiqueta[3];
		this.panles = new JPanel[3];
		this.imagen = new ImageIcon("src//main//java//img//sistema.png");
		this.confi = new Configuracion();
	}

	private void componentes() {

		for (int i = 0; i < 3; i++) {

			this.panles[i] = new JPanel();
			this.panles[i].setLayout(null);
			this.add(this.panles[i]);

			this.titulos[i] = new Etiqueta(this.getNombre(i + 1).toUpperCase(), JLabel.CENTER);
			this.titulos[i].setSize(this.dimension.getSize().width / 2, (int) (this.dimension.getSize().height * 0.10));
			this.titulos[i].setLocation((this.dimension.width - this.titulos[i].getSize().width) / 2, 0);

			this.iconos[i] = new Etiqueta("", JLabel.CENTER);
			this.iconos[i].setSize(this.dimension.getSize().width, (int) (this.dimension.getSize().height * 0.50));
			this.iconos[i].setIcon(this.getIcono(i + 1));
			this.iconos[i].setLocation(0, this.titulos[i].getLocation().y + this.titulos[i].getSize().height
					+ (int) (this.dimension.getSize().height * 0.05));

			this.texto[i] = new CuadroTexto(4, true);
			this.texto[i].setSize(this.dimension.getSize().width / 2, (int) (this.dimension.getSize().height * 0.05));
			this.texto[i].setLocation(this.titulos[i].getLocation().x, this.iconos[i].getLocation().y
					+ this.iconos[i].getSize().height + (int) (this.dimension.getSize().height * 0.05));

			this.botones[i] = new Boton("ingresrar", 4, true);
			this.botones[i].setSize(this.dimension.getSize().width / 2, (int) (this.dimension.getSize().height * 0.05));
			this.botones[i].setLocation(this.texto[i].getLocation().x, this.texto[i].getLocation().y
					+ this.texto[i].getSize().height + (int) (this.dimension.getSize().height * 0.05));
			this.botones[i].addActionListener(this);
			if (i == 2)
				this.texto[2].setEditable(false);

			this.panles[i].add(this.titulos[i]);
			this.panles[i].add(this.iconos[i]);
			this.panles[i].add(this.texto[i]);
			if (i == 2)
				return;
			this.panles[i].add(this.botones[i]);
		}
	}

	public void totolCaja() {
		this.texto[2].setText(Consultas.getConsultas().getRetirarDineroCosn().buscar(4, null, null)[0][0]);
		this.texto[2].updateUI();
	}

	private String getNombre(int key) {

		switch (key) {
			case 1:

				return "ingreso";

			case 2:

				return "retiro";
			case 3:

				return "total caja";
			default:
				break;
		}
		return "ninguno";
	}

	private ImageIcon getIcono(int id) {

		switch (id) {
			case 1:

				this.imagen = new ImageIcon("src//main//java//img//ingreso-dinero.png");
				this.imagen.setImage(
						this.imagen.getImage().getScaledInstance((int) (this.iconos[0].getSize().width * 0.80),
								(int) (this.iconos[0].getSize().height * 0.80), Image.SCALE_SMOOTH));
				break;
			case 2:
				this.imagen = new ImageIcon("src//main//java//img//retiro-dinero.png");
				this.imagen.setImage(
						this.imagen.getImage().getScaledInstance((int) (this.iconos[0].getSize().width * 0.80),
								(int) (this.iconos[0].getSize().height * 0.80), Image.SCALE_SMOOTH));
				break;
			case 3:
				this.imagen = new ImageIcon("src//main//java//img//estadistica.png");
				this.imagen.setImage(
						this.imagen.getImage().getScaledInstance((int) (this.iconos[0].getSize().width * 0.80),
								(int) (this.iconos[0].getSize().height * 0.80), Image.SCALE_SMOOTH));
				break;
		}
		return this.imagen;

	}

	public void actionPerformed(ActionEvent e) {

		if (this.botones[0] == e.getSource()) {
			RetirarDinero retirar = this.enviarDatos(0);
			if (retirar == null)
				return;
			if (!retirar.validar2())
				this.limpiar();
		}

		if (this.botones[1] == e.getSource()) {
			RetirarDinero retirar = this.enviarDatos(1);
			if (retirar == null)
				return;
			if (!retirar.validar2())
				this.limpiar();
		}

		this.totolCaja();

	}

	private void limpiar() {

		this.texto[0].setText("");
		this.texto[1].setText("");
	}

	private RetirarDinero enviarDatos(int id) {

		String dinero = "";
		RetirarDinero RetirarDinero = null;

		if (id == 0 && validarNumero(this.texto[0].getText())) {

			dinero = this.texto[0].getText();
			RetirarDinero = new RetirarDinero(dinero);
			Consultas.getConsultas().getRetirarDineroCosn().registar(Procesos.getProcesos().getLogin().getCedula(), 1,
					"0", dinero);
			ErrorClases.getErrorClases().ingresarDinero();
			this.limpiar();
			return RetirarDinero;
		}
		if (id == 1 && validarNumero(this.texto[1].getText())) {
			dinero = this.texto[1].getText();
			RetirarDinero = new RetirarDinero(dinero);
			Consultas.getConsultas().getRetirarDineroCosn().registar(Procesos.getProcesos().getLogin().getCedula(), 1,
					dinero, "0");
			ErrorClases.getErrorClases().retirarDinero();
			this.limpiar();
			return RetirarDinero;

		}
		if (!validarNumero(this.texto[0].getText()) || !validarNumero(this.texto[1].getText())) {
			ErrorClases.getErrorClases().CantidadNegativa();
		}

		return RetirarDinero;
	}

	private boolean validarNumero(String numero) {
		try {
			Double dato = Double.valueOf(numero);
			if (dato <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
