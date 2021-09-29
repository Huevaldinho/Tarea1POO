package vista;

import javax.swing.*;
import control.controlador;
import modelo.NProvincia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Anadir_Persona extends JFrame {
    private JPanel anadirPersonaPanel;
    private JTextField textoNombre;
    private JTextField textoCorreoElectronico;
    private JTextField textoNumeroTelefono;
    private JCheckBox puntarenasCheckBox;
    private JCheckBox sanJoseCheckBox;
    private JCheckBox alajuelaCheckBox;
    private JCheckBox herediaCheckBox;
    private JCheckBox guanacasteCheckBox;
    private JCheckBox cartagoCheckBox;
    private JCheckBox limonCheckBox;
    private JButton aceptarButton;
    private controlador control;

    Anadir_Persona(controlador control) {
        super("Añadir Persona");
        this.control = control;
        setContentPane(anadirPersonaPanel);
        setResizable(false);
        this.pack();

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (extraerInformacion()) {
                        JOptionPane.showMessageDialog(null, "Se ha ingresado la persona");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Inténtelo de nuevo");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private boolean extraerInformacion() throws IOException {
        String nombre = textoNombre.getText();
        String correoElectronico = textoCorreoElectronico.getText();
        String numeroTelefono = textoNumeroTelefono.getText();

        ArrayList<NProvincia> provinciasInteres = new ArrayList();
        if (sanJoseCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.SanJose);
        }
        if (alajuelaCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.Alajuela);
        }
        if (herediaCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.Heredia);
        }
        if (puntarenasCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.Puntarenas);
        }
        if (guanacasteCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.Guancaste);
        }
        if (cartagoCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.Cartago);
        }
        if (limonCheckBox.isSelected()) {
            provinciasInteres.add(NProvincia.Limon);
        }

        if (numeroTelefono.equals("####-####")) {
            return control.agregarPersona(nombre, correoElectronico, provinciasInteres);
        } else {
            return control.agregarPersona(nombre, correoElectronico, numeroTelefono, provinciasInteres);
        }

    }
}

