package vista;

import javax.swing.*;

public class Anadir_Sismo extends JFrame {
    private JPanel panel;
    private JTextField textoFecha;
    private JTextField textoInstanteExacto;
    private JTextField textoProfundidad;
    private JTextField textoMagnitud;
    private JTextField textoLocalizacion;
    private JComboBox comboBoxProvincia;
    private JButton anadirButton;
    private JComboBox comboBox1;

    Anadir_Sismo() {
        super("Añadir Sismo");
        setContentPane(panel);
        this.pack();
    }
}
