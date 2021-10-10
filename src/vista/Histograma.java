package vista;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.*;

import control.controlador;
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

/**
 * Objeto para generar un histograma
 */

public class Histograma extends JFrame {
    private controlador control;
    public Histograma(String title,controlador control) {
        super(title);
        this.control=control;
        JPanel chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
    }
    private IntervalXYDataset crearDataset() {
        HistogramDataset dataset = new HistogramDataset();
//vecto almacena los ingresos quincenales de 45 personas
        double vector[] = new double[0];
        try {
            vector = control.reporteProvincias();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//En el ejercicio nos piden construir una distribución de frecuencias de 8 intervalos
//Por eso ponemos 8 en el tercer parámetro del addSeries
        dataset.addSeries("Provincias según número de cédula", vector, 7);
        return dataset;
    }
    private static JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                "Sismos por Provincias",
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
    public JPanel crearPanel() {
        JFreeChart chart = crearChart(crearDataset());
        return new ChartPanel(chart);
    }
}