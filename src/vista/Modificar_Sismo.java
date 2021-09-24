package vista;

import javax.swing.*;

public class Modificar_Sismo extends JFrame {
    private JPanel panel;
    private JComboBox comboBoxNuevaProvincia;
    private JTextField textoNuevaLocalizacion;
    private JTextField textField1;

    Modificar_Sismo() {
        super("Modificar Sismo");
        setContentPane(panel);
        this.pack();
    }
}
