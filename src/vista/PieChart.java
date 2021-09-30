package vista;

import javax.swing.JPanel;
import control.controlador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.text.ParseException;

public class PieChart extends ApplicationFrame {
    private controlador control;
    public PieChart( String title,controlador control ) {
        super( title );
        this.control=control;
        setContentPane(createDemoPanel());
    }

    private PieDataset createDataset( ){
        DefaultPieDataset dataset = new DefaultPieDataset( );
        double datos[] = new double[5];
        try {
            datos = control.reporteTipoOrigen();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataset.setValue( "Subducción" , new Double( datos[0] ) );
        dataset.setValue( "Choque de Placas" , new Double(  datos[1] ) );
        dataset.setValue( "Tectónico Por Falla Local" , new Double(  datos[2] ) );
        dataset.setValue( "Intra Placa" , new Double(  datos[3] ) );
        dataset.setValue( "Deformación Interna" , new Double( datos[4] ) );
        return dataset;
    }

    private static JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Sismos Por Tipo de Origen",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public JPanel createDemoPanel( ){
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel( chart );
    }

}