package vista;

import javax.swing.*;

public class pruebaUI {
    private JTable table1;
    private JComboBox comboBox1;
    private JButton button1;
    public pruebaUI(){
        JFrame ventana = new JFrame();
        ventana.setSize(200, 300);

        ventana.setTitle("Ventana");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        ventana.setVisible(true);
    }
}
