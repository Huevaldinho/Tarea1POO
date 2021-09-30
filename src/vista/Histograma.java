package vista;

import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Histograma extends ApplicationFrame {
    public Histograma(String title) {
        super(title);
        JPanel chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
    }
    private static IntervalXYDataset crearDataset() {
        HistogramDataset dataset = new HistogramDataset();
//vecto almacena los ingresos quincenales de 45 personas
        double vector[] = {1,1,1,2,2,2,3,3,4,5,6,6,6,6,6,6,7,7};
//En el ejercicio nos piden construir una distribución de frecuencias de 8 intervalos
//Por eso ponemos 8 en el tercer parámetro del addSeries
        dataset.addSeries("Frecuencias de los ingresos", vector, 7);
        return dataset;
    }
    private static JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                "Histograma",
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }
    public static JPanel crearPanel() {
        JFreeChart chart = crearChart(crearDataset());
        return new ChartPanel(chart);
    }
}