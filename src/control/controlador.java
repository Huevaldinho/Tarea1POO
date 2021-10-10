package control;
import modelo.NProvincia;
import modelo.Persona;
import modelo.Sismo;
import modelo.TOrigen;


import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import control.Cargador;
import control.BaseDatos;
import java.util.Arrays;
import org.apache.poi.hpsf.Array;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Modelo que se encarga de validar parametros e instanciar objetos
 */

public class controlador {
    private Cargador cargador = new Cargador();
    private BaseDatos BD;  // asociación comunicación entre el controlador y el admClientes
    private Expresiones_Regulares expresionesRegulares = new Expresiones_Regulares();

    /**
     * Crear un controlador que sirve de intermediario entre la UI y la Base de datos.
     */
    public controlador() throws IOException, ParseException {
        BD = new BaseDatos();
        ArrayList<Sismo> arrayTmp= cargador.cargarExcelSismos();
        if (arrayTmp.isEmpty())
            cargador.crearExcelSismos();
        else{
            BD.setSismos(arrayTmp);
        }
        ArrayList<Persona> personasTemp = cargador.cargarExcelPersonas();
        if (personasTemp.isEmpty()) {
            cargador.crearExcelPersonas();
        }
        else {
            BD.setPersonas(personasTemp);
        }
    }
    /**
     * Agregar sismo a la lista
     * @param fecha: Fecha del sismo
     * @param hora: Instante exacto del sismo.
     * @param profundidad: Profundidad del sismo.
     * @param magnitud: Magnitud del sismo.
     * @param origen: Origen del sismo.
     * @param provincia: Provincia donde se produjo.
     * @param latitud: Latitud del sismo.
     * @param longitud: Longitud del sismo.
     * @param localzacion: Localizacion: Descripcion de la localizacion.
     * @param lugarOrigen: Sismo terrestre o sismo maritimo.
     * @return boolean: true si se agrego, false de lo contrario.
     */
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
    /**
     * Modificar la localizacion de un sismo
     * @param hora: Fecha del sismo
     * @param provinciaNueva: Instante exacto del sismo.
     * @param localizacion: Localizacion: Nueva descripcion de la localizacion.
     * @return boolean: true si se modifico, false de lo contrario.
     */
    public boolean modificarSismo(Date fecha, Date hora, int provinciaNueva, String localizacion) throws IOException {
        return BD.modificarSismo(fecha, hora,provinciaNueva,localizacion);
    }

    /**
     * Metodo sobrecargado para annadir persona utilizando un numero de telefono
     * @param nombre nombre de la persona
     * @param correoElectronico correo electronico de la persona
     * @param numeroTelefono numero de telefono de la persona
     * @param lista lista que contiene las provincias de interes de la persona
     * @return retorna un booleano para verificar si se annadio la persona de forma exitosa
     */

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

    /**
     * Metodo sobrecargado para annadir persona sin numero de telefono
     * @param nombre nombre de la persona
     * @param correoElectronico correo electronico de la persona
     * @param lista lista que contiene las provincias de interes de la persona
     * @return retorna un booleano para verificar si se annadio la persona de forma exitosa
     */

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

    /**
     * Se encarga de generar el reporte por provincias
     * @return Un array con datos double
     */

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
    /**
     * Solicita al cargador que cree el modelo para la tabla de la interfaz grafica.
     * @return DefaultTableModel: Modelo de la tabla para la interfaz grafica.
     */
    public DefaultTableModel cargarSismosTabla(){return cargador.cargarClientes(BD.getSismos());}

    /**
     * Genera un reporte por tipo de origen de un sismo
     * @return Un array con datos tipo double
     */

    public double[] reporteTipoOrigen()throws IOException, ParseException {
        ArrayList<Sismo> sismos= BD.getSismos();
        int cantidadSismos= sismos.size();
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

    /**
     * Envia la tabla con la clasificacion por magnitud de los sismos
     * @param dtm Modelo por defecto de una tabla
     * @param i Iterador
     * @param opcion Clasificacion de la magnitud
     * @param sismo Objeto para extraer datos
     * @return Retorna la tabla con los nuevos datos
     */

    public DefaultTableModel enviarTablaMagnitud (DefaultTableModel dtm,int i,int opcion,Sismo sismo){
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        switch (opcion) {
            case 1 -> dtm.setValueAt("Micro", i, 0);
            case 2 -> dtm.setValueAt("Menor", i, 0);
            case 3 -> dtm.setValueAt("Ligero", i, 0);
            case 4 -> dtm.setValueAt("Moderado", i, 0);
            case 5 -> dtm.setValueAt("Fuerte", i, 0);
            case 6 -> dtm.setValueAt("Mayor", i, 0);
            case 7 -> dtm.setValueAt("Gran", i, 0);
            case 8 -> dtm.setValueAt("Épico", i, 0);
        }
        dtm.setValueAt(sismo.getMagnitud(),i,1);
        dtm.setValueAt(formatoFecha.format(sismo.getFecha()), i, 2);
        dtm.setValueAt(formatoHora.format(sismo.getInstanteExacto()), i, 3);
        dtm.setValueAt(sismo.getProfundidad(), i, 4);
        if (sismo.getOrigen().equals(TOrigen.Subduccion)) {
            dtm.setValueAt("Subducción", i, 5);
        } else if (sismo.getOrigen().equals(TOrigen.TectonicoPorFallaLocal)) {
            dtm.setValueAt("Tectónico por falla local", i, 5);
        } else if (sismo.getOrigen().equals(TOrigen.IntraPlaca)) {
            dtm.setValueAt("Intra placa", i, 5);
        } else if (sismo.getOrigen().equals(TOrigen.DeformacionInterna)) {
            dtm.setValueAt("Deformación Interna", i, 5);
        } else if (sismo.getOrigen().equals(TOrigen.ChoqueDePlacas)) {
            dtm.setValueAt("Choque de placas", i, 5);
        }
        if (sismo.getProvincia() == 1)
            dtm.setValueAt("San José", i, 6);
        else if (sismo.getProvincia() == 2)
            dtm.setValueAt("Alajuela", i, 6);
        else if (sismo.getProvincia() == 3)
            dtm.setValueAt("Cartago", i, 6);
        else if (sismo.getProvincia() == 4)
            dtm.setValueAt("Heredia", i, 6);
        else if (sismo.getProvincia() == 5)
            dtm.setValueAt("Guacanaste", i, 6);
        else if (sismo.getProvincia() == 6)
            dtm.setValueAt("Puntarenas", i, 6);
        else if (sismo.getProvincia() == 7)
            dtm.setValueAt("Limón", i, 6);
        dtm.setValueAt(sismo.getLocalizacionLatitud(), i, 7);
        dtm.setValueAt(sismo.getLocalizacionLongitud(), i, 8);
        if (sismo.getLugarOrigen() == 1)
            dtm.setValueAt("Terrestre", i, 9);
        else
            dtm.setValueAt("Marítimo", i, 9);
        dtm.setValueAt(sismo.getLocalizacionDescripcion(), i, 10);
        return dtm;
    }

    /**
     * Genera una tabla con los reportes por magnitud
     * @return Retorna la tabla con todos los datos
     */

    public DefaultTableModel reportePorMagnitud(){
        ArrayList<Sismo> sismos= BD.getSismos();

        Collections.sort(sismos, new Comparator<Sismo>() { // ordena el arrayList
            @Override
            public int compare(Sismo s1, Sismo s2) {
                //noinspection removal
                return new Double(s1.getMagnitud()).compareTo(new Double(s2.getMagnitud()));
            }
        });

        String[] encabezado = {"Descripción","Magnitud","Fecha","Hora","Profundidad","Origen",
                "Provincia", "Latitud", "Longitud", "LugarOrigen", "Localizacion"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado,sismos.size());
        for (int i = 0 ; i< sismos.size() ; i++){
            Sismo cte = sismos.get(i);
            int opcion;
            if (cte.getMagnitud()<2.0)//micro
                opcion = 1;
            else if (cte.getMagnitud()>=2.0&&cte.getMagnitud()<=3.9)//menor
                opcion = 2;
            else if (cte.getMagnitud()>=4.0&&cte.getMagnitud()<=4.9)//ligero
                opcion = 3;
            else if (cte.getMagnitud()>=5.0&&cte.getMagnitud()<=5.9)//moderado
                opcion = 4;
            else if (cte.getMagnitud()>=6.0&&cte.getMagnitud()<=6.9)//fuerte
                opcion = 5;
            else if (cte.getMagnitud()>=7.0&&cte.getMagnitud()<=7.9)//mayor
                opcion = 6;
            else if (cte.getMagnitud()>=8.0&&cte.getMagnitud()<=9.9)//gran
                opcion = 7;
            else//épico
                opcion = 8;
            dtm=enviarTablaMagnitud(dtm,i,opcion,cte);
        }
        return dtm;
    }

    /**
     * Crea una tabla con los sismos de un rango de fechas
     * @param fechaInicial Fecha inicial para filtrar
     * @param fechaFinal Fecha final para filtrar
     * @return Retorna la tabla con los sismos del rango de fechas establecidos
     */

    public DefaultTableModel reporteFechas(Date fechaInicial, Date fechaFinal){
        ArrayList <Sismo> sismos = BD.getSismos();
        Date fechaActual = new Date(System.currentTimeMillis());
        String[] encabezado = {"Fecha", "Hora", "Profundidad", "Origen",
                "Provincia", "Latitud", "Longitud", "LugarOrigen", "Localizacion","Descripción"};
        int tamannoTabla = 0;
        for (Sismo i :sismos){
            if (i.getFecha().after(fechaInicial) || i.getFecha().equals(fechaInicial)) {
                if (i.getFecha().before(fechaFinal) || i.getFecha().equals(fechaFinal))
                    tamannoTabla++;
            }
        }
        System.out.println(tamannoTabla);
        DefaultTableModel dtm = new DefaultTableModel(encabezado,tamannoTabla);
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        int contador=0;
        for (Sismo sismo : sismos) {
            if (contador > tamannoTabla) {
                break;
            }
            if (sismo.getFecha().after(fechaInicial) || sismo.getFecha().equals(fechaInicial)) {
                if (sismo.getFecha().before(fechaFinal) || sismo.getFecha().equals(fechaFinal)) {
                    dtm.setValueAt(formatoFecha.format(sismo.getFecha()), contador, 0);
                    dtm.setValueAt(formatoHora.format(sismo.getInstanteExacto()), contador, 1);
                    dtm.setValueAt(sismo.getMagnitud(), contador, 2);
                    dtm.setValueAt(sismo.getProfundidad(), contador, 3);
                    if (sismo.getOrigen().equals(TOrigen.Subduccion)) {
                        dtm.setValueAt("Subducción", contador, 4);
                    } else if (sismo.getOrigen().equals(TOrigen.TectonicoPorFallaLocal)) {
                        dtm.setValueAt("Tectónico por falla local", contador, 4);
                    } else if (sismo.getOrigen().equals(TOrigen.IntraPlaca)) {
                        dtm.setValueAt("Intra placa", contador, 4);
                    } else if (sismo.getOrigen().equals(TOrigen.DeformacionInterna)) {
                        dtm.setValueAt("Deformación Interna", contador, 4);
                    } else if (sismo.getOrigen().equals(TOrigen.ChoqueDePlacas)) {
                        dtm.setValueAt("Choque de placas", contador, 4);
                    }
                    if (sismo.getProvincia() == 1)
                        dtm.setValueAt("San José", contador, 5);
                    else if (sismo.getProvincia() == 2)
                        dtm.setValueAt("Alajuela", contador, 5);
                    else if (sismo.getProvincia() == 3)
                        dtm.setValueAt("Cartago", contador, 5);
                    else if (sismo.getProvincia() == 4)
                        dtm.setValueAt("Heredia", contador, 5);
                    else if (sismo.getProvincia() == 5)
                        dtm.setValueAt("Guacanaste", contador, 5);
                    else if (sismo.getProvincia() == 6)
                        dtm.setValueAt("Puntarenas", contador, 5);
                    else if (sismo.getProvincia() == 7)
                        dtm.setValueAt("Limón", contador, 5);
                    dtm.setValueAt(sismo.getLocalizacionLatitud(), contador, 6);
                    dtm.setValueAt(sismo.getLocalizacionLongitud(), contador, 7);
                    if (sismo.getLugarOrigen() == 1)
                        dtm.setValueAt("Terrestre", contador, 8);
                    else
                        dtm.setValueAt("Marítimo", contador, 8);
                    dtm.setValueAt(sismo.getLocalizacionDescripcion(), contador, 9);
                    contador++;
                }
            }
        }
        return dtm;
    }

    /**
     * Valida las fechas ingresadas por el usuario
     * @param fechaInicial Fecha inicial ingresada por el usuario
     * @param fechaFinal Fecha final ingresada por el usuario
     * @return booleano para validar datos
     */

    public boolean reporteFechasValidar(Date fechaInicial,Date fechaFinal){
        Date fechaActual = new Date(System.currentTimeMillis());
        if (fechaInicial.after(fechaActual)||fechaFinal.after(fechaActual)){
            return false;
        }
        return !fechaInicial.after(fechaFinal);
    }

    /**
     * Genera un reporte por mes en un determinado anno
     * @param anno Anno ingresado por el usuario
     * @return Retorna un array con datos de tipo double
     */

    public double[] reportesPorMesEnUnAnno(Date anno){
        System.out.println("Esta en los reportes");
        ArrayList<Sismo> sismos= BD.getSismos();
        Calendar annoIngresado=Calendar.getInstance();
        annoIngresado.setTime(anno);
        int e=0;int f=0;int mz=0;int ab=0;int my=0;int jn=0;int jl=0;int ag=0;int s=0;int o=0;int n=0;int d=0;
        for (Sismo i : sismos){
            Date date = i.getFecha();// the date instance
            Calendar annoSismo = Calendar.getInstance();
            annoSismo.setTime(date);
            if (annoSismo.get(Calendar.YEAR)==annoIngresado.get(Calendar.YEAR)){
                if (annoSismo.get(Calendar.MONTH)==Calendar.JANUARY)
                    e++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.FEBRUARY)
                    f++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.MARCH)
                    mz++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.APRIL)
                    ab++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.MAY)
                    my++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.JUNE)
                    jn++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.JULY)
                    jl++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.AUGUST)
                    ag++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.SEPTEMBER)
                    s++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.OCTOBER)
                    o++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.NOVEMBER)
                    n++;
                else if (annoSismo.get(Calendar.MONTH)==Calendar.DECEMBER)
                    d++;
            }
        }
        double datos[]=new double[12];
        datos[0]=e;datos[1]=f;datos[2]=mz;datos[3]=ab;datos[4]=my;datos[5]=jn;datos[6]=jl;datos[7]=ag;datos[8]=s;datos[9]=o;datos[10]=n;datos[11]=d;
        return datos;
    }

}

