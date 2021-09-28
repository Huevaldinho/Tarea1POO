package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import control.controlador;
public class Modificar_Sismo extends JFrame {
    private JPanel panel;
    private JComboBox comboBoxNuevaProvincia;
    private JTextField textoNuevaLocalizacion;
    private JTextField txtInstanteExacto;
    private JButton btnModificar;
    private controlador control;

    Modificar_Sismo() {    }
    Modificar_Sismo(controlador control) {
        super("Modificar Sismo");
        setContentPane(panel);
        this.pack();
        this.control=control;


        btnModificar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if (extraerDatosModificar())
                        JOptionPane.showMessageDialog(null,"Se ha modificado el sismo!");
                    else
                        JOptionPane.showMessageDialog(null,"Ha ocurrido un error");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public boolean extraerDatosModificar() throws IOException {
        Date hora=null;
        String nuevaLocalizacion;
        int provincia;
        try {
            hora = new SimpleDateFormat("hh:mm:ss").parse(txtInstanteExacto.getText());
            nuevaLocalizacion = textoNuevaLocalizacion.getText();
            provincia = comboBoxNuevaProvincia.getSelectedIndex()+1;
        } catch (ParseException e) {
            System.out.println ("El error es: " + e.getMessage());
            e.printStackTrace();
            //deberia reiniciar la interfaz grafica para que ingrese de nuevo la fecha
            return false;
        }
        //Retorna si pudo modificarlo o no
        return control.modificarSismo(hora,provincia,nuevaLocalizacion);
    }
}
