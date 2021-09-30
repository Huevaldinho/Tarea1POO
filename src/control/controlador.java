package control;
import modelo.NProvincia;
import modelo.Persona;
import modelo.Sismo;
import modelo.TOrigen;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import control.Cargador;
import control.BaseDatos;
import org.apache.poi.hpsf.Array;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class controlador {
    private Cargador cargador = new Cargador();
    private BaseDatos BD;  // asociación comunicación entre el controlador y el admClientes
    private Expresiones_Regulares expresionesRegulares = new Expresiones_Regulares();

    public controlador() throws IOException, ParseException {
        BD = new BaseDatos();
        ArrayList<Sismo> arrayTmp= cargador.cargarExcelSismos();
        if (arrayTmp.isEmpty())
            cargador.crearExcelSismos();
        else{
            BD.setSismos(arrayTmp);
        }
    }
    public boolean agregarSismo(Date fecha, Date hora,double profundidad,double  magnitud,TOrigen origen, int provincia,
                                double latitud,double longitud,String localzacion,int lugarOrigen) throws IOException {
        //Falta validar fechas y horas
        Date fechaActual = new Date(System.currentTimeMillis());
        Date horaActual = new Date(System.currentTimeMillis());

        if (fecha.after(fechaActual) | hora.after(horaActual)){
            System.out.println("La fecha o la hora estan despues de la actual");
            return false;
        }
        if (magnitud<0)//Magnitud tiene que ser mayor que 0
            return false;
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        if (expresionesRegulares.verificarHoras(formatoHora.format(hora))==false){
            JOptionPane.showMessageDialog(null, "Ha ingresado una hora incorrecta");
            return false;
        }

        Sismo nuevoSismo = new Sismo(fecha, hora, profundidad, magnitud,origen,
                                    provincia,latitud,longitud,localzacion,lugarOrigen);
        return BD.annadirSismo(nuevoSismo);
    }
    public boolean modificarSismo(Date hora, int provinciaNueva, String localizacion) throws IOException {
        //Validar
        //Calendar fechaYHoraPrueba = Calendar.getInstance();
        //fechaYHoraPrueba.set(2001,9,31,05,30,00);
        //System.out.println(fechaYHoraPrueba.getTime());
        return BD.modificarSismo(hora,provinciaNueva,localizacion);
    }

    public boolean agregarPersona(String nombre, String correoElectronico, String numeroTelefono, ArrayList<NProvincia> lista) {
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            return false;
        }
        else if (correoElectronico.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un correo electronico");
            return false;
        }
        else if (numeroTelefono.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero de telefono");
            return false;
        }
        else if (lista.size() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una provincia");
            return false;
        }
        else if (expresionesRegulares.verificarCorreoElectronico(correoElectronico)) {
            if (expresionesRegulares.verificarNumeroTelefono(numeroTelefono)) {
                Persona nuevaPersona = new Persona(nombre, correoElectronico, numeroTelefono, lista);
                return BD.annadirPersona(nuevaPersona);
            }
            else {
                JOptionPane.showMessageDialog(null, "El formato del número de teléfono es incorrecto");
                return false;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "El formato del correo electronico es incorrecto");
            return false;
        }
    }

    public boolean agregarPersona(String nombre, String correoElectronico, ArrayList<NProvincia> lista) {
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            return false;
        }
        else if (correoElectronico.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un correo electronico");
            return false;
        }
        else if (lista.size() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una provincia");
            return false;
        }
        else if (expresionesRegulares.verificarCorreoElectronico(correoElectronico)) {
            Persona nuevaPersona = new Persona(nombre, correoElectronico, lista);
            return BD.annadirPersona(nuevaPersona);
        }
        else {
            JOptionPane.showMessageDialog(null, "El formato del correo electronico es incorrecto");
            return false;
        }
    }
    public ArrayList<Integer> reporteProvincias()throws IOException, ParseException {
        ArrayList<Sismo> sismos= cargador.cargarExcelSismos();
        ArrayList<Integer>datos=new ArrayList();
        for (Sismo i : sismos){
            datos.add(i.getProvincia());
        }
        return datos;
    }
    public DefaultTableModel cargarSismosTabla(){return cargador.cargarClientes(BD.getSismos());}
}

