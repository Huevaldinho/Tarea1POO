package vista;

import javax.swing.*;

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

    Anadir_Persona() {
        super("Añadir Persona");
        setContentPane(anadirPersonaPanel);
        this.pack();
    }
}
