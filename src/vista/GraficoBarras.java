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

public class GraficoBarras extends ApplicationFrame {
    public GraficoBarras( String applicationTitle , String chartTitle ) {
    super( applicationTitle );
    JFreeChart barChart = ChartFactory.createBarChart(
            chartTitle,
            "Mes",
            "Cantidad",
            createDataset(),
            PlotOrientation.VERTICAL,
            true, true, false);

    ChartPanel chartPanel = new ChartPanel( barChart );
    chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
    setContentPane( chartPanel );
}

    private CategoryDataset createDataset( ) {
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

        dataset.addValue( 1.0 , enero , mes );
        dataset.addValue( 3.0 , febrero , mes );
        dataset.addValue( 5.0 , marzo , mes );
        dataset.addValue( 5.0 , abril , mes );

        dataset.addValue( 5.0 , mayo , mes );
        dataset.addValue( 6.0 , junio , mes );
        dataset.addValue( 10.0 , julio , mes );
        dataset.addValue( 4.0 , agosto , mes );

        dataset.addValue( 4.0 , septiembre , mes );
        dataset.addValue( 2.0 , octubre , mes );
        dataset.addValue( 3.0 , noviembre , mes );
        dataset.addValue( 6.0 , diciembre , mes );

        return dataset;
    }
}
