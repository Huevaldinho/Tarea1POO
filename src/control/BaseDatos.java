//Proyecto 1 | POO
//Elaborado por: Sebastián Arroniz Rojas, Sebatián Bermúdez Acuña y Felipe Obando Arrieta.
//Fecha de creación: 22/09/2021
//Última modificación: 00/00/2021

package control;

import jdk.swing.interop.SwingInterOpUtils;
import modelo.*;
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

/**
 * Modelo de una base de datos que almacena en RAM los sismos y las personas
 */

public class BaseDatos {
    //Atributos

    private static ArrayList<Sismo> sismos = new ArrayList();
    private ArrayList<Persona> personas = new ArrayList();
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

    /**
     *
     * @return retorna toda la informacion
     */
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
    /**
     * Agregar sismo a la lista
     * @param nuevoSismo: Sismo que se va a agregar.
     * @return boolean: Si pudo agregar el sismo retorna true, si no, retorna false.
     */
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
        Enviar_Correo.enviarCorreo(personas, nuevoSismo);
        return true;
    }
    /**
     * Consultar sismo en la lista
     * @param fecha: Fecha del sismo.
     * @param instanteExacto: Instante exacto del sismo.
     * @return Sismo: Sismo encontrado o null si no lo encontro.
     */
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
    /**
     * Modifica la localizacion de un sismo
     * @param nuevoInstante: Instante del sismo que se va a modificar
     * @param nuevaProvincia: Provincia donde se produjo el sismo.
     * @param nuevaLocalizacion: Nueva localizacion.
     * @return true modifico el sismo, false de lo contrario.
     */
    public boolean modificarSismo(Date nuevoInstante, int nuevaProvincia, String nuevaLocalizacion) throws IOException {
        if (sismos.isEmpty())
                return false;
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Hay "+sismos.size()+" sismos registrados.");
        int index=0;
        boolean encontro=false;
        for (Sismo actual : sismos){//Revisa si ya está registrado
            index++;
            if (formatoHora.format(actual.getInstanteExacto()).equals(formatoHora.format(nuevoInstante))){
                System.out.println("Son iguales jaja xd");
                actual.setProvincia(nuevaProvincia);
                actual.setLocalizacionDescripcion(nuevaLocalizacion);
                encontro=true;
                break;
            }
        }
        if (encontro==false)
            return false;
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
    /**
     * Eliminar un sismo de la lista
     * @param sismoEliminar: Sismo que se eliminara
     * @return true modifico el sismo, false de lo contrario.
     */
    public boolean eliminarSismo (Sismo sismoEliminar){
        return sismos.remove(sismoEliminar);
    }

    /**
     * Annade la nueva persona a la base de datos y al excel
     * @param nuevaPersona persona instanciada en el controlador
     * @return booleano para verificar si se annadio exitosamente
     */

    public boolean annadirPersona(Persona nuevaPersona) {
        for (Persona i : personas){
            if (i.equals(nuevaPersona)) {
                JOptionPane.showMessageDialog(null, "Ya existen los datos de contacto ingresados");
                return false;
            }
        }
        personas.add(nuevaPersona);
        String fileName =  "Excel/baseDatosPersonas.xlsx";
        try {
            InputStream inp = new FileInputStream(new File(fileName));
            XSSFWorkbook oldWorkbook = new XSSFWorkbook(inp);
            XSSFSheet oldSheet = oldWorkbook.getSheetAt(0);
            XSSFRow oldRow = oldSheet.createRow(oldSheet.getLastRowNum() + 1);
            //oldrow es una nueva fila, le empieza a crear las celdas y asignar los datos.
            oldRow.createCell(0).setCellValue(nuevaPersona.getNombre());
            oldRow.createCell(1).setCellValue(nuevaPersona.getCorreoElectronico());
            if (nuevaPersona.getNumeroTelefono() == null) {
                oldRow.createCell(2).setCellValue("-");
            }
            else {
                oldRow.createCell(2).setCellValue(nuevaPersona.getNumeroTelefono());
            }

            String provincias = "";
            for (NProvincia i : nuevaPersona.getProvinciasInteres()) {
                if (i.equals(NProvincia.SanJose)) {
                    provincias += "San José,";
                }
                else if (i.equals(NProvincia.Guancaste)) {
                    provincias += "Guanacaste,";
                }
                else if (i.equals(NProvincia.Limon)) {
                    provincias += "Limón,";
                }
                else if (i.equals(NProvincia.Puntarenas)) {
                    provincias += "Puntarenas,";
                }
                else if (i.equals(NProvincia.Heredia)) {
                    provincias += "Heredia,";
                }
                else if (i.equals(NProvincia.Cartago)) {
                    provincias += "Cartago,";
                }
                else if (i.equals(NProvincia.Alajuela)) {
                    provincias += "Alajuela,";
                }
            }
            provincias =  provincias.substring(0, provincias.length() - 1);
            oldRow.createCell(3).setCellValue(provincias);

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

    /**
     * Consulta una persona en la lista de personas
     * @param nombrePersona nombre por el cual se va a buscar a la persona
     * @return se retorna el objeto de la persona encontrada
     */

    public Persona consultarPersona(String nombrePersona){
        Persona aBuscar = new Persona();
        aBuscar.setNombre(nombrePersona);
        for (Persona i : personas){
            if (i.equals(aBuscar))
                return aBuscar;
        }
        return null;
    }

    /**
     * Modifica una persona
     * @param nuevaPersona nuevo objeto de persona para modificar
     * @return un booleano para verificar si se realizo exitosamente
     */

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

    /**
     * Elimina una persona
     * @param personaEliminar objeto de persona a eliminar
     * @return booleano verificar la eliminacion
     */

    public boolean eliminarPersona (Persona personaEliminar){
        return personas.remove(personaEliminar);
    }


}
