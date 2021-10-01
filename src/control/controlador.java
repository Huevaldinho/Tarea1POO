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
    public double[] reporteProvincias()throws IOException, ParseException {
        ArrayList<Sismo> sismos= BD.getSismos();
        int cantidadSismos=0;
        for (Sismo i : sismos){
            cantidadSismos++;
        }
        double datos[]=new double[cantidadSismos];
        int cnt=0;
        for (Sismo i : sismos){
            datos[cnt]=(i.getProvincia());
            cnt++;
        }
        return datos;
    }
    public DefaultTableModel cargarSismosTabla(){return cargador.cargarClientes(BD.getSismos());}

    public double[] reporteTipoOrigen()throws IOException, ParseException {
        ArrayList<Sismo> sismos= BD.getSismos();
        int cantidadSismos=0;
        for (Sismo i : sismos){
            cantidadSismos++;
        }
        //int sb,cp,tpf,ip,di=0; <-- si lo declaro asi no me deja :( java odioso
        int sb=0;
        int cp=0;
        int tpf=0;
        int ip=0;
        int di=0;
        for (Sismo i : sismos){
            TOrigen opcion = i.getOrigen();
            switch (opcion){
                case Subduccion ->{
                    sb++;break;
                }
                case ChoqueDePlacas -> {
                    cp++;break;
                }
                case TectonicoPorFallaLocal -> {
                    tpf++;break;
                }
                case IntraPlaca -> {
                    ip++;break;
                }
                case DeformacionInterna -> {
                    di++;break;
                }
            }
        }
        double datos[]=new double[5];
        datos[0]=(sb*100)/cantidadSismos;datos[1]=(cp*100)/cantidadSismos;datos[2]=(tpf*100)/cantidadSismos;datos[3]=(ip*100)/cantidadSismos;datos[4]=(di*100)/cantidadSismos;
        return datos;

    }
    public DefaultTableModel reportePorMagnitud(){
        ArrayList<Sismo> sismos= BD.getSismos();
        String[] encabezado = {"Fecha", "Hora", "Profundidad", "Origen",
                "Provincia", "Latitud", "Longitud", "LugarOrigen", "Localizacion"};
        int tamannoTabla=0;
        for (Sismo i : sismos){
            if (i.getMagnitud() == magnitud)
                tamannoTabla++;
        }
        DefaultTableModel dtm = new DefaultTableModel(encabezado,tamannoTabla);
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0 ; i<dtm.getRowCount(); i++){
            Sismo cte = sismos.get(i);
            if (cte.getMagnitud()==magnitud){
                dtm.setValueAt(formatoFecha.format(cte.getFecha()), i, 0);
                dtm.setValueAt(formatoHora.format(cte.getInstanteExacto()), i, 1);
                dtm.setValueAt(cte.getMagnitud(), i, 2);
                dtm.setValueAt(cte.getProfundidad(), i, 3);
                if (cte.getOrigen().equals(TOrigen.Subduccion)) {
                    dtm.setValueAt("Subducción", i, 4);
                } else if (cte.getOrigen().equals(TOrigen.TectonicoPorFallaLocal)) {
                    dtm.setValueAt("Tectónico por falla local", i, 4);
                } else if (cte.getOrigen().equals(TOrigen.IntraPlaca)) {
                    dtm.setValueAt("Intra placa", i, 4);
                } else if (cte.getOrigen().equals(TOrigen.DeformacionInterna)) {
                    dtm.setValueAt("Deformación Interna", i, 4);
                } else if (cte.getOrigen().equals(TOrigen.ChoqueDePlacas)) {
                    dtm.setValueAt("Choque de placas", i, 4);
                }
                if (cte.getProvincia() == 1)
                    dtm.setValueAt("San José", i, 5);
                else if (cte.getProvincia() == 2)
                    dtm.setValueAt("Alajuela", i, 5);
                else if (cte.getProvincia() == 3)
                    dtm.setValueAt("Cartago", i, 5);
                else if (cte.getProvincia() == 4)
                    dtm.setValueAt("Heredia", i, 5);
                else if (cte.getProvincia() == 5)
                    dtm.setValueAt("Guacanaste", i, 5);
                else if (cte.getProvincia() == 6)
                    dtm.setValueAt("Puntarenas", i, 5);
                else if (cte.getProvincia() == 7)
                    dtm.setValueAt("Limón", i, 5);
                dtm.setValueAt(cte.getLocalizacionLatitud(), i, 6);
                dtm.setValueAt(cte.getLocalizacionLongitud(), i, 7);
                if (cte.getLugarOrigen() == 1)
                    dtm.setValueAt("Terrestre", i, 8);
                else
                    dtm.setValueAt("Maritimo", i, 8);
                dtm.setValueAt(cte.getLocalizacionDescripcion(), i, 9);
            }
        }
        return dtm;
    }

}

