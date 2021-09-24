//Proyecto 1 | POO
//Elaborado por: Sebastián Arroniz Rojas, Sebatián Bermúdez Acuña y Felipe Obando Arrieta.
//Fecha de creación: 22/09/2021
//Última modificación: 00/00/2021

package control;

import modelo.Persona;
import modelo.Sismo;
import modelo.TOrigen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vista.Ventana_Principal;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
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
    public static boolean cargarExcelSismos() throws IOException {
        //Trae archivo excel
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(new File("D:\\OneDrive - Estudiantes ITCR\\Escritorio\\TEC\\Cursos - Semestre 2\\Programación Orientada a Objetos\\Proyectos\\Primer Proyecto\\excel\\baseDatosSismos.xlsx"));
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
        Sismo actual = new Sismo();
        while (rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();

            //Iterador para recorrer las celdas de la fila
            Iterator < Cell >  cellIterator = row.cellIterator();
            //Si es la primera fila se la salta para que no de errores a la hora de crear los objetos
            if (row.getRowNum()==0)
                continue;
            //Recorre todas las celdas. A partir de aqui se tiene que crear los objetos
            while ( cellIterator.hasNext()) {
                //celda individual
                Cell cell = cellIterator.next();
                //System.out.print("Fila: "+cell.getRowIndex()+", columna: "+cell.getColumnIndex()+"\t");
                switch (cell.getColumnIndex()){
                    case 0:{//Fecha
                        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        //objeto.setFecha(formatoFecha.format(cell.getDateCellValue()));
                        //System.out.println("Fecha: "+formatoFecha.format(cell.getDateCellValue()));
                        actual.setFecha(cell.getDateCellValue());//Esta fecha tiene una hora pero no se utiliza
                        System.out.println("Fecha: "+formatoFecha.format(actual.getFecha()));
                        break;
                    }
                    case 1:{//Hora
                        DateFormat formatoInstanteExacto = new SimpleDateFormat("hh/mm/ss");
                        System.out.println("Instante exacto: "+formatoInstanteExacto.format(cell.getDateCellValue()));
                        actual.setInstanteExacto(cell.getDateCellValue());
                        break;
                    }
                    case 2:{//Profundidad
                        System.out.println("Profundidad: "+cell.getNumericCellValue());
                        actual.setProfundidad(cell.getNumericCellValue());
                        break;
                    }
                    case 3:{//Magitud
                        System.out.println("Magnitud: "+cell.getNumericCellValue());
                        actual.setMagnitud(cell.getNumericCellValue());
                        break;
                    }
                    case 4:{//Origen //Enum
                        System.out.println("Origen: "+cell.getStringCellValue());
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
                        break;
                    }
                    case 5:{//Provincia
                        System.out.println("Provincia: "+cell.getNumericCellValue());
                        actual.setProvincia((int) cell.getNumericCellValue());
                        break;
                    }
                    case 6:{//Latitud
                        System.out.println("Latitud: "+cell.getNumericCellValue());
                        actual.setLocalizacionLatitud(cell.getNumericCellValue());
                        break;
                    }
                    case 7:{//Longitud
                        System.out.println("Longitud: "+cell.getNumericCellValue());
                        actual.setLocalizacionLongitud(cell.getNumericCellValue());
                        break;
                    }
                    case 8:{//Origen
                        System.out.println("Lugar origen (tierrra o mar): "+cell.getNumericCellValue());
                        actual.setLugarOrigen((int) cell.getNumericCellValue());
                        break;
                    }
                    case 9:{//Descripcion
                        System.out.println("Descripción localización: "+cell.getStringCellValue());
                        actual.setLocalizacionDescripcion(cell.getStringCellValue());
                        break;
                    }
                }

            }
            System.out.println();
            sismos.add(actual);
        }
        System.out.println("Se cargaron: "+sismos.size()+" del archivo de excel.");
        fis.close();
        return true;
    }
    public boolean annadirSismo(Sismo nuevoSismo) {
        for (Sismo i : sismos){
            if (i.equals(nuevoSismo))
                return false;
        }
        return sismos.add(nuevoSismo);
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
    public boolean modificarSismo(Sismo nuevoSismo){
        for (int i = 0 ; i<sismos.size() ; i++) {
            Sismo sismoEnCiclo=sismos.get(i);
            if (sismoEnCiclo.getFecha() == nuevoSismo.getFecha() && sismoEnCiclo.getInstanteExacto() == nuevoSismo.getInstanteExacto()){
                sismos.set(i,nuevoSismo);
                return true;
            }
        }
        return false;
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

    public static void main(String[] args) throws IOException {
        if (cargarExcelSismos()==false){
            crearExcelSismos();
        }else{//Se cargó el excel
            System.out.println("Ya cargó los datos supuestamente");
        }

        // creacion ventana principal
        JFrame interfaz = new Ventana_Principal();
        interfaz.setVisible(true);
    }
}
