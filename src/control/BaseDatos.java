//Proyecto 1 | POO
//Elaborado por: Sebastián Arroniz Rojas, Sebatián Bermúdez Acuña y Felipe Obando Arrieta.
//Fecha de creación: 22/09/2021
//Última modificación: 00/00/2021

package control;

import jdk.swing.interop.SwingInterOpUtils;
import modelo.Persona;
import modelo.Sismo;
import modelo.TOrigen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vista.Ventana_Principal;
import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class BaseDatos {
    //Atributos
    static XSSFRow row;
    private static ArrayList<Sismo> sismos = new ArrayList();
    private ArrayList<Persona> personas;
    //Constructor
    public BaseDatos() {}
    //Set y get
    public ArrayList<Persona> getPersonas() {
        return personas;
    }
    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    public ArrayList<Sismo> getSismos() {
        return sismos;
    }
    public void setSismos(ArrayList<Sismo> sismos) {
        this.sismos = sismos;
    }
    //toString
    public String toString(){
        StringBuilder text = new StringBuilder("Sismos:\n");
        for (Sismo i : sismos){
            text.append(i.toString());
        }
        text.append("\nPersonas:\n");
        for (Persona j : personas){
            text.append(j.toString());
        }
        return text.toString();
    }

    //Métodos
    public static void crearExcelSismos(){
        System.out.println("Función de crear excel si no carga la base de datos");


    }
    public static boolean cargarExcelSismos() throws IOException, ParseException {//se esta cargando mal en ram
        //Trae archivo excel
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(new File("Excel/baseDatosSismos.xlsx"));
        } catch (Exception IOException) {
            System.out.println("ERROR al cargar excel de sismos");
            return false;
        }
        //Instancia el archivo
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//
        //Se posiciona en la primera hoja
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        //Iterador de filas para avanzar en la hoja
        Iterator<Row> rowIterator = spreadsheet.iterator();

        //Recorre todas las filas

        int cantidadFilas = spreadsheet.getLastRowNum();
        System.out.println("Cantidad de sismo: " +cantidadFilas);
        int i=0;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoInstanteExacto = new SimpleDateFormat("hh:mm:ss");
        while (i<cantidadFilas+1) {//el primero no se cuenta
            row = (XSSFRow) spreadsheet.getRow(i);
            i++;
            //row = (XSSFRow) rowIterator.next();
            //Iterador para recorrer las celdas de la fila
            Iterator < Cell >  cellIterator = row.cellIterator();
            //Si es la primera fila se la salta para que no de errores a la hora de crear los objetos
            if (row.getRowNum()==0)
                continue;

            //Recorre todas las celdas. A partir de aqui se tiene que crear los objetos
            Sismo actual = new Sismo();
            for (int j=0;j<10;j++) {
                Cell cell = row.getCell(j);
                switch (j){
                    case 0:{//Fecha
                        String sDate1=cell.getStringCellValue();
                        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                        actual.setFecha(date1);//Esta fecha tiene una hora pero no se utiliza
                        System.out.println("\nFecha" +actual.getFecha());
                        break;
                    }
                    case 1:{//Hora
                        String shora=cell.getStringCellValue();
                        Date hora=new SimpleDateFormat("HH:mm:ss").parse(shora);
                        actual.setInstanteExacto(hora);
                        System.out.println("Hora: "+actual.getInstanteExacto());
                        break;
                    }
                    case 2:{//Profundidad
                        actual.setProfundidad(cell.getNumericCellValue());
                        System.out.println("Profundidad: "+actual.getProfundidad());
                        break;
                    }
                    case 3:{//Magitud
                        actual.setMagnitud(cell.getNumericCellValue());
                        System.out.println("Magnitud: "+actual.getMagnitud());
                        break;
                    }
                    case 4:{//Origen //Enum
                        String origen = cell.getStringCellValue();
                        if(origen.equals("Subducción")){
                            actual.setOrigen(TOrigen.Subduccion);
                        }else if(origen.equals("Tectónico por falla local")){
                            actual.setOrigen(TOrigen.TectonicoPorFallaLocal);
                        }else if(origen.equals("Intra placa")){
                            actual.setOrigen(TOrigen.IntraPlaca);
                        }else if(origen.equals("Deformación Interna")){
                            actual.setOrigen(TOrigen.DeformacionInterna);
                        }else if(origen.equals("Choque de placas")){
                            actual.setOrigen(TOrigen.ChoqueDePlacas);
                        }
                        System.out.println(actual.getOrigen());
                        break;
                    }
                    case 5:{//Provincia
                        actual.setProvincia((int) cell.getNumericCellValue());
                        System.out.println("Provincia:"+actual.getProvincia());
                        break;
                    }
                    case 6:{//Latitud
                        actual.setLocalizacionLatitud(cell.getNumericCellValue());
                        System.out.println("Latitud "+actual.getLocalizacionLatitud());
                        break;
                    }
                    case 7:{//Longitud
                        actual.setLocalizacionLongitud(cell.getNumericCellValue());
                        System.out.println("Longitud: "+actual.getLocalizacionLongitud());
                        break;
                    }
                    case 8:{//Origen
                        actual.setLugarOrigen((int) cell.getNumericCellValue());
                        System.out.println("Lugar origen  (tierra o mar):"+actual.getLugarOrigen() );
                        break;
                    }
                    case 9:{//Descripcion
                        actual.setLocalizacionDescripcion(cell.getStringCellValue());
                        System.out.println("Descripcion localizacion: "+actual.getLocalizacionDescripcion());
                        break;
                    }
                }
            }
            sismos.add(actual);
            System.out.println(formatoFecha.format(actual.getFecha()));
        }
        System.out.println("Se cargaron: "+sismos.size()+" del archivo de excel.");
        fis.close();
        return true;
    }
    public boolean annadirSismo(Sismo nuevoSismo) throws IOException {
        for (Sismo i : sismos){//Revisa si ya está registrado
            if (i.equals(nuevoSismo))
                return false;
        }
        sismos.add(nuevoSismo);//lo agrega a la lista que se maneja en RAM
        String fileName =  "Excel/baseDatosSismos.xlsx";
        try {
            InputStream inp = new FileInputStream(new File(fileName));
            XSSFWorkbook oldWorkbook = new XSSFWorkbook(inp);
            XSSFSheet oldSheet = oldWorkbook.getSheetAt(0);
            XSSFRow oldRow = oldSheet.createRow(oldSheet.getLastRowNum() + 1);
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            //oldrow es una nueva fila, le empieza a crear las celdas y asignar los datos.
            oldRow.createCell(0).setCellValue(formatoFecha.format(nuevoSismo.getFecha()));
            oldRow.createCell(1).setCellValue(formatoHora.format(nuevoSismo.getInstanteExacto()));
            oldRow.createCell(2).setCellValueImpl(nuevoSismo.getProfundidad());
            oldRow.createCell(3).setCellValueImpl(nuevoSismo.getMagnitud());
            if(nuevoSismo.getOrigen().equals(TOrigen.Subduccion)){
                oldRow.createCell(4).setCellValue("Subducción");
            }else if(nuevoSismo.getOrigen().equals(TOrigen.TectonicoPorFallaLocal)){
                oldRow.createCell(4).setCellValue("Tectónico por falla local");
            }else if(nuevoSismo.getOrigen().equals(TOrigen.IntraPlaca)){
                oldRow.createCell(4).setCellValue("Intra placa");
            }else if(nuevoSismo.getOrigen().equals(TOrigen.DeformacionInterna)){
                oldRow.createCell(4).setCellValue("Deformación Interna");
            }else if(nuevoSismo.getOrigen().equals(TOrigen.ChoqueDePlacas)){
                oldRow.createCell(4).setCellValue("Choque de placas");
            }
            oldRow.createCell(5).setCellValue(nuevoSismo.getProvincia());
            oldRow.createCell(6).setCellValueImpl(nuevoSismo.getLocalizacionLatitud());
            oldRow.createCell(7).setCellValueImpl(nuevoSismo.getLocalizacionLongitud());
            oldRow.createCell(8).setCellValue(nuevoSismo.getLugarOrigen());
            oldRow.createCell(9).setCellValue(nuevoSismo.getLocalizacionDescripcion());

            FileOutputStream fos = null;
            File file;
            file = new File(fileName);
            fos = new FileOutputStream(file);
            oldWorkbook.write(fos);
            oldWorkbook.close();
            fos.close();
            System.out.println("Se ingresó correctamente!");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public Sismo consultarSismo(Date fecha, Date instanteExacto){
        Sismo aBuscar = new Sismo();
        aBuscar.setFecha(fecha);
        aBuscar.setInstanteExacto(instanteExacto);
        for (Sismo i : sismos){
            if (i.equals(aBuscar))
                return aBuscar;
        }
        return null;
    }
    public boolean modificarSismo(Date nuevoInstante, int nuevaProvincia, String nuevaLocalizacion) throws IOException {
        if (sismos.isEmpty())
                return false;
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Hay "+sismos.size()+" sismos registrados.");
        int index=0;
        for (Sismo actual : sismos){//Revisa si ya está registrado
            index++;
            if (formatoHora.format(actual.getInstanteExacto()).equals(formatoHora.format(nuevoInstante))){
                System.out.println("Son iguales jaja xd");
                actual.setProvincia(nuevaProvincia);
                actual.setLocalizacionDescripcion(nuevaLocalizacion);
                break;
            }
        }
        System.out.println("Cambio en sismos: " +sismos.get(index-1).getLocalizacionDescripcion()+" en RAM esta en el indice: "+(index-1) );
        String nombreArchivo =  "Excel/baseDatosSismos.xlsx";
        //hay una diferencia de dos numeros en el indice del excel
        InputStream inp = new FileInputStream(new File(nombreArchivo));
        XSSFWorkbook oldWorkbook = new XSSFWorkbook(inp);
        XSSFSheet oldSheet = oldWorkbook.getSheetAt(0);
        XSSFRow aModificar = oldSheet.getRow(index);
        //Toma la celda de provincia y localizacion y les cambia los datos.
        aModificar.getCell(9).setCellValue(nuevaLocalizacion);
        aModificar.getCell(5).setCellValue(nuevaProvincia);

        FileOutputStream fos = null;
        File file;
        file = new File(nombreArchivo);
        fos = new FileOutputStream(file);
        oldWorkbook.write(fos);
        oldWorkbook.close();
        fos.close();
        System.out.println("Se modifico correctamente!");
        return  true;
    }
    public boolean eliminarSismo (Sismo sismoEliminar){
        return sismos.remove(sismoEliminar);
    }
    public boolean annadirPersona(Persona nuevaPersona) {
        for (Persona i : personas){
            if (i.equals(nuevaPersona))
                return false;
        }
        return personas.add(nuevaPersona);
    }
    public Persona consultarPersona(String nombrePersona){
        Persona aBuscar = new Persona();
        aBuscar.setNombre(nombrePersona);
        for (Persona i : personas){
            if (i.equals(aBuscar))
                return aBuscar;
        }
        return null;
    }
    public boolean modificarPersona(Persona nuevaPersona){
        for (int i = 0 ; i<personas.size() ; i++) {
            Persona personaEnCiclo=personas.get(i);
            if (personaEnCiclo.getNombre().equals(nuevaPersona.getNombre())){
                personas.set(i,nuevaPersona);
                return true;
            }
        }
        return false;
    }
    public boolean eliminarPersona (Persona personaEliminar){
        return personas.remove(personaEliminar);
    }

    public static void main(String[] args) throws IOException, ParseException {
        if (cargarExcelSismos()==false){
            crearExcelSismos();
        }else{//Se cargó el excel
            System.out.println("Carga datos");
        }

        // creacion ventana principal
        JFrame interfaz = new Ventana_Principal();
        interfaz.setVisible(true);
    }
}
