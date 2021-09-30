package vista;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChart extends ApplicationFrame {

    public PieChart( String title ) {
        super( title );
        setContentPane(createDemoPanel( ));
    }

    private static PieDataset createDataset( ) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "San José" , new Double( 20 ) );
        dataset.setValue( "Alajuela" , new Double( 20 ) );
        dataset.setValue( "Heredia" , new Double( 10 ) );
        dataset.setValue( "Cartago" , new Double( 15 ) );
        dataset.setValue( "Guanacaste" , new Double( 25 ) );
        dataset.setValue( "Limón" , new Double( 7 ) );
        dataset.setValue( "Puntarenas" , new Double( 3 ) );
        return dataset;
    }

    private static JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Sismos Costa Rica",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public static JPanel createDemoPanel( ) {
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel( chart );
    }

}