package procesos;

public class FacturaCompra implements EmpleadoFabrica{

	public FacturaCompra() {
		super();
	}

	private String ruc;

	public FacturaCompra(String ruc) {
		super();
		this.ruc = ruc;
	}
	
	public boolean validar() {
		
		return false;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
}
