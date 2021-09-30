package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import control.controlador;
import modelo.TOrigen;

public class Anadir_Sismo extends JFrame {
    private JPanel panel;
    private JTextField textoFecha;
    private JTextField textoInstanteExacto;
    private JTextField textoProfundidad;
    private JTextField textoMagnitud;
    private JTextField textoLocalizacion;
    private JComboBox comboBoxProvincia;
    private JComboBox comboBox1;//faltan atributos
    private JButton anadirButton;
    private JLabel lbl_latitud;
    private JTextField textoLatitud;
    private JTextField textoLongitud;
    private JComboBox comboboxOrigen;
    private controlador control;

    Anadir_Sismo() {    }
    Anadir_Sismo(controlador control) {
        super("Añadir Sismo");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        this.control = control;

        anadirButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if (extraerInformacion()){
                        JOptionPane.showMessageDialog(null,"Se ha ingresado sismo");
                    }else{
                        JOptionPane.showMessageDialog(null,"Ha ocurrido un error, interte de nuevo");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private boolean extraerInformacion() throws IOException {
        String localizacion = textoLocalizacion.getText();
        Date fecha= null;
        Date hora = null;
        double magnitud = 0;
        double profundidad = 0;
        double latitud =0;
        double longitud= 0;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss",Locale.getDefault());
        formatoFecha.setLenient(false);

        try {

            fecha = formatoFecha.parse(textoFecha.getText());
            hora = formatoHora.parse(textoInstanteExacto.getText());
            magnitud =  Double.parseDouble(textoMagnitud.getText());
            profundidad = Double.parseDouble(textoProfundidad.getText());
            latitud = Double.parseDouble(textoLatitud.getText());
            longitud = Double.parseDouble(textoLongitud.getText());
        } catch (ParseException e) {
            e.printStackTrace();//?
            //deberia reiniciar la interfaz grafica para que ingrese de nuevo la fecha
            return false;
        }
        int provincia = comboBoxProvincia.getSelectedIndex() + 1 ;//retorna el numero en orden del combobox (empieza en 0).
        TOrigen origen = null;
        switch (comboBox1.getSelectedItem().toString()){
            case "Subducción":{
                origen = TOrigen.Subduccion;
                break;
            }
            case "Choque de placas":{
                origen = TOrigen.ChoqueDePlacas;
                break;
            }
            case "Téctonico por falla local":{
                origen = TOrigen.TectonicoPorFallaLocal;
                break;
            }
            case "Intra placa":{
                origen = TOrigen.IntraPlaca;
                break;
            }
            case "Deformación interna":{
                origen = TOrigen.DeformacionInterna;
                break;
            }

        }
        int terrestreMaritmo = comboboxOrigen.getSelectedIndex()+1;

        if(control.agregarSismo(fecha,hora,profundidad,magnitud,origen,provincia,latitud,longitud,localizacion,terrestreMaritmo))
            return true;
        return false;
    }

}
