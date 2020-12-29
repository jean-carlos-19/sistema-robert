package consultas;

public class Consultas {

    private static Consultas Consultas;
    private CategoriaCons CategoriaCons;
    private ClienteCons ClienteCons;
    private ComprasCons ComprasCons;
    private DetalleCompraCons DetalleCompraCons;
    private DetalleCons DetalleCons;
    private DeudorCons DeudorCons;
    private EmpleadoCons EmpleadoCons;
    private FacturaDeudaCons FacturaDeudaCons;
    private FacturaCompraCons FacturaCompraCons;
    private FacturaVentacons FacturaVentacons;
    private MarcaCons MarcaCons;
    private ProductoCons ProductoCons;
    private ProveedorCons ProveedorCons;
    private ReporteCons ReporteCons;
    private RetirarDineroCosn RetirarDineroCosn;
    private SesionCons SesionCons;
    private UsuarioCons UsuarioCons;
    private VentaCons VentaCons;
    private MedidasCons MedidasCons;

    private Consultas() {

        this.CategoriaCons = consultas.CategoriaCons.getCategoriaCons();
        this.ComprasCons = ComprasCons.getComprasCons();
        this.DetalleCompraCons = DetalleCompraCons.getDetalleCompraCons();
        this.DetalleCons = DetalleCons.getDetalleCons();
        this.DeudorCons = DeudorCons.getDeudorCons();
        this.EmpleadoCons = EmpleadoCons.getEmpleadoCons();
        this.FacturaCompraCons = FacturaCompraCons.getFacturaCompraCons();
        this.FacturaDeudaCons = FacturaDeudaCons.getFacturaDeudaCons();
        this.FacturaVentacons = FacturaVentacons.getFacturaVentacons();
        this.MarcaCons = MarcaCons.getMarcaCons();
        this.ProductoCons = ProductoCons.getProductoCons();
        this.ProveedorCons = ProveedorCons.getProveedorCons();
        this.ReporteCons = ReporteCons.getReporteCons();
        this.RetirarDineroCosn = RetirarDineroCosn.getRetirarDineroCosn();
        this.UsuarioCons = UsuarioCons.getUsuarioCons();
        this.VentaCons = VentaCons.getVentaCons();
        this.SesionCons = SesionCons.getSesionCons();
        this.MedidasCons = MedidasCons.getMedidas();

    }

    public static Consultas getConsultas() {
        if (Consultas == null)
            Consultas = new Consultas();
        return Consultas;
    }

    public CategoriaCons getCategoria() {
        return this.CategoriaCons;
    }

    public ComprasCons getComprasCons() {
        return this.ComprasCons;
    }

    public DetalleCompraCons getDetalleCompraCons() {
        return this.DetalleCompraCons;
    }

    public DetalleCons getDetalleCons() {
        return this.DetalleCons;
    }

    public DeudorCons getDeudorCons() {
        return this.DeudorCons;
    }

    public EmpleadoCons getEmpleadoCons() {
        return this.EmpleadoCons;
    }

    public FacturaCompraCons getFacturaCompraCons() {
        return this.FacturaCompraCons;
    }

    public FacturaDeudaCons getFacturaDeudaCons() {
        return this.FacturaDeudaCons;
    }

    public FacturaVentacons getFacturaVentacons() {
        return this.FacturaVentacons;
    }

    public MarcaCons getMarcaCons() {
        return this.MarcaCons;
    }

    public ProductoCons getProductoCons() {
        return this.ProductoCons;
    }

    public ProveedorCons getProveedorCons() {
        return this.ProveedorCons;
    }

    public ReporteCons getReporteCons() {
        return this.ReporteCons;
    }

    public RetirarDineroCosn getRetirarDineroCosn() {
        return this.RetirarDineroCosn;
    }

    public UsuarioCons getUsuarioCons() {
        return this.UsuarioCons;
    }

    public VentaCons getVentaCons() {
        return this.VentaCons;
    }

    public SesionCons getSesionCons() {
        return this.SesionCons;
    }

    public MedidasCons getMedidasCons() {
        return this.MedidasCons;
    }
}
