package consultas;

public class ReporteCons {

    private static ReporteCons ReporteCons;

    private ReporteCons() {

    }

    public static ReporteCons getReporteCons() {
        if (ReporteCons == null)
            ReporteCons = new ReporteCons();
        return ReporteCons;
    }

}