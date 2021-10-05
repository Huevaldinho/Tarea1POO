package vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import control.controlador;
import java.util.Date;

public class GraficoBarras extends ApplicationFrame {
    private controlador controlador;
    public GraficoBarras( String applicationTitle , String chartTitle , Date anno,double[]info) {
    super( applicationTitle );
    JFreeChart barChart = ChartFactory.createBarChart(
            chartTitle,
            "Mes",
            "Cantidad",
            createDataset(anno,info),
            PlotOrientation.VERTICAL,
            true, true, false);

    ChartPanel chartPanel = new ChartPanel( barChart );
    chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
    setContentPane( chartPanel );
}

    private CategoryDataset createDataset(Date anno,double[]info ) {
        System.out.println("LLEGO HASTA AQUI");
        final String enero = "Enero";
        final String febrero = "Febrero";
        final String marzo = "Marzo";
        final String abril = "Abril";
        final String mayo = "Mayo";
        final String junio = "Junio";
        final String julio = "Julio";
        final String agosto = "Agosto";
        final String septiembre = "Septiembre";
        final String octubre = "Octubre";
        final String noviembre = "Noviembre";
        final String diciembre = "Diciembre";
        final String mes = " ";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        dataset.addValue( info[0], enero , mes );
        dataset.addValue( info[1] , febrero , mes );
        dataset.addValue( info[2] , marzo , mes );
        dataset.addValue( info[3], abril , mes );

        dataset.addValue( info[4], mayo , mes );
        dataset.addValue( info[5], junio , mes );
        dataset.addValue( info[6] , julio , mes );
        dataset.addValue( info[7] , agosto , mes );

        dataset.addValue( info[8] , septiembre , mes );
        dataset.addValue( info[9], octubre , mes );
        dataset.addValue( info[10] , noviembre , mes );
        dataset.addValue( info[11], diciembre , mes );

        return dataset;
    }
}
