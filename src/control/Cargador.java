package control;

import modelo.NProvincia;
import modelo.Persona;
import modelo.Sismo;
import modelo.TOrigen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Modelo para cargar ciertos elementos para la app
 */

public class Cargador {
    static XSSFRow row;
    private static ArrayList<Sismo> sismos = new ArrayList();
    private static ArrayList<Persona> personas = new ArrayList();
    /**
     * Crear el modelo de la tabla para mostrar sismos en la interfaz grafica.
     * @param lista: Lista de sismos que se mostraran en la interfaz.
     * @return DefaultTableModel: Modelo de la tabla.
     */
    public static DefaultTableModel cargarClientes(ArrayList<Sismo> lista) {
        String[] encabezado = {"Fecha", "Hora", "Magnitud", "Profundidad", "Origen",
                "Provincia", "Latitud", "Longitud", "LugarOrigen", "Localizacion"};

        DefaultTableModel dtm = new DefaultTableModel(encabezado, lista.size());

        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Sismo cte = lista.get(i);
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
        return dtm;
    }
    /**
     * Crear archivo de excel en caso de no encontrarlo.
     */
    public static boolean crearExcelSismos() {
        String fileName = "Excel/baseDatosSismos.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sismos");

        sheet.getRow(0).createCell(0).setCellValue("Fecha");
        sheet.getRow(0).createCell(1).setCellValue("Hora");
        sheet.getRow(0).createCell(2).setCellValue("Magnitud");
        sheet.getRow(0).createCell(3).setCellValue("Profundidad");
        sheet.getRow(0).createCell(4).setCellValue("Origen");
        sheet.getRow(0).createCell(5).setCellValue("Provincia");
        sheet.getRow(0).createCell(6).setCellValue("Latitud");
        sheet.getRow(0).createCell(7).setCellValue("Longitud");
        sheet.getRow(0).createCell(8).setCellValue("LugarOrigen");
        sheet.getRow(0).createCell(9).setCellValue("Localizacion");
        try {
            FileOutputStream fos = null;
            File file;
            file = new File(fileName);
            fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();
            System.out.println("Se ha creado archivo para base de datos de los sismos");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * Cargar los datos del excel a un arraylist.
     * @return retorna la lista de sismos.
     */
    public static ArrayList<Sismo> cargarExcelSismos() throws IOException, ParseException {//se esta cargando mal en ram
        //Trae archivo excel
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("Excel/baseDatosSismos.xlsx"));
        } catch (Exception IOException) {
            System.out.println("ERROR al cargar excel de sismos");
            JOptionPane.showMessageDialog(null, "Debe cerrar el excel de sismos");
            System.exit(0);
            return null;
        }
        //Instancia el archivo
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//
        //Se posiciona en la primera hoja
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        //Iterador de filas para avanzar en la hoja
        Iterator<Row> rowIterator = spreadsheet.iterator();

        //Recorre todas las filas

        int cantidadFilas = spreadsheet.getLastRowNum();
        System.out.println("Cantidad de sismo: " + cantidadFilas);
        int i = 0;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoInstanteExacto = new SimpleDateFormat("hh:mm:ss");
        while (i < cantidadFilas + 1) {//el primero no se cuenta
            row = (XSSFRow) spreadsheet.getRow(i);
            i++;
            //row = (XSSFRow) rowIterator.next();
            //Iterador para recorrer las celdas de la fila
            Iterator<Cell> cellIterator = row.cellIterator();
            //Si es la primera fila se la salta para que no de errores a la hora de crear los objetos
            if (row.getRowNum() == 0)
                continue;

            //Recorre todas las celdas. A partir de aqui se tiene que crear los objetos
            Sismo actual = new Sismo();
            for (int j = 0; j < 10; j++) {
                Cell cell = row.getCell(j);
                switch (j) {
                    case 0: {//Fecha
                        String sDate1 = cell.getStringCellValue();
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                        actual.setFecha(date1);//Esta fecha tiene una hora pero no se utiliza
                        break;
                    }
                    case 1: {//Hora
                        String shora = cell.getStringCellValue();
                        Date hora = new SimpleDateFormat("HH:mm:ss").parse(shora);
                        actual.setInstanteExacto(hora);
                        break;
                    }
                    case 2: {//Profundidad
                        actual.setProfundidad(cell.getNumericCellValue());
                        break;
                    }
                    case 3: {//Magitud
                        actual.setMagnitud(cell.getNumericCellValue());
                        break;
                    }
                    case 4: {//Origen //Enum
                        String origen = cell.getStringCellValue();
                        if (origen.equals("Subducción")) {
                            actual.setOrigen(TOrigen.Subduccion);
                        } else if (origen.equals("Tectónico por falla local")) {
                            actual.setOrigen(TOrigen.TectonicoPorFallaLocal);
                        } else if (origen.equals("Intra placa")) {
                            actual.setOrigen(TOrigen.IntraPlaca);
                        } else if (origen.equals("Deformación Interna")) {
                            actual.setOrigen(TOrigen.DeformacionInterna);
                        } else if (origen.equals("Choque de placas")) {
                            actual.setOrigen(TOrigen.ChoqueDePlacas);
                        }
                        break;
                    }
                    case 5: {//Provincia
                        actual.setProvincia((int) cell.getNumericCellValue());
                        break;
                    }
                    case 6: {//Latitud
                        actual.setLocalizacionLatitud(cell.getNumericCellValue());
                        break;
                    }
                    case 7: {//Longitud
                        actual.setLocalizacionLongitud(cell.getNumericCellValue());
                        break;
                    }
                    case 8: {//Origen
                        actual.setLugarOrigen((int) cell.getNumericCellValue());
                        break;
                    }
                    case 9: {//Descripcion
                        actual.setLocalizacionDescripcion(cell.getStringCellValue());
                        break;
                    }
                }
            }
            sismos.add(actual);
        }
        System.out.println("Se cargaron: " + sismos.size() + " del archivo de excel.");
        fis.close();
        return sismos;
    }

    /**
     * Crea un excel con las personas en caso de que este no exista
     * @return booleano para verificar la creacion del excel
     */

    public static boolean crearExcelPersonas() {
        String fileName = "Excel/baseDatosPersonas.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Personas");

        sheet.getRow(0).createCell(0).setCellValue("Nombre");
        sheet.getRow(0).createCell(1).setCellValue("Correo Electrónico");
        sheet.getRow(0).createCell(2).setCellValue("Número Teléfono");
        sheet.getRow(0).createCell(3).setCellValue("Provincias De Interés");
        try {
            FileOutputStream fos = null;
            File file;
            file = new File(fileName);
            fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();
            System.out.println("Se ha creado archivo para base de datos de las personas");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Se encarga de cargar el excel con los datos de las personas a una lista en RAM
     * @return Una lista con las personas cargadas
     */

    public static ArrayList<Persona> cargarExcelPersonas() throws IOException, ParseException {
        //Trae archivo excel
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("Excel/baseDatosPersonas.xlsx"));
        } catch (Exception IOException) {
            System.out.println("ERROR al cargar excel de personas");
            JOptionPane.showMessageDialog(null, "Debe cerrar el excel de personas");
            System.exit(0);
            return null;
        }
        //Instancia el archivo
        XSSFWorkbook workbook = new XSSFWorkbook(fis);//
        //Se posiciona en la primera hoja
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        //Iterador de filas para avanzar en la hoja
        Iterator<Row> rowIterator = spreadsheet.iterator();

        //Recorre todas las filas

        int cantidadFilas = spreadsheet.getLastRowNum();
        System.out.println("Cantidad de personas: " + cantidadFilas);
        int i = 0;
        while (i < cantidadFilas + 1) {//el primero no se cuenta
            row = (XSSFRow) spreadsheet.getRow(i);
            i++;
            //row = (XSSFRow) rowIterator.next();
            //Iterador para recorrer las celdas de la fila
            Iterator<Cell> cellIterator = row.cellIterator();
            //Si es la primera fila se la salta para que no de errores a la hora de crear los objetos
            if (row.getRowNum() == 0)
                continue;

            //Recorre todas las celdas. A partir de aqui se tiene que crear los objetos
            Persona actual = new Persona();
            for (int j = 0; j < 10; j++) {
                Cell cell = row.getCell(j);
                switch (j) {
                    case 0: {//Nombre
                        String nombre = cell.getStringCellValue();
                        actual.setNombre(nombre);
                        break;
                    }
                    case 1: {//Correo electronico
                        String correoElectronico = cell.getStringCellValue();
                        boolean bandera = Expresiones_Regulares.verificarCorreoElectronico(correoElectronico);
                        if (bandera) {
                            actual.setCorreoElectronico(correoElectronico);
                        }
                        break;
                    }
                    case 2: {//Numero telefono
                        String numeroTelefono = cell.getStringCellValue();
                        if (numeroTelefono.equals("####-####") || numeroTelefono.equals("-")) {
                            break;
                        }
                        boolean bandera = Expresiones_Regulares.verificarNumeroTelefono(numeroTelefono);
                        if (bandera) {
                            actual.setNumeroTelefono(numeroTelefono);
                        }
                        break;
                    }
                    case 3: {//Provincias interes
                        ArrayList<NProvincia> provinciasInteres = new ArrayList();
                        String provincias = cell.getStringCellValue();
                        String[] split = provincias.split(",");
                        for (String k : split) {
                            switch (k) {
                                case "San José": {
                                    provinciasInteres.add(NProvincia.SanJose);
                                    break;
                                }
                                case "Limón": {
                                    provinciasInteres.add(NProvincia.Limon);
                                    break;
                                }
                                case "Puntarenas": {
                                    provinciasInteres.add(NProvincia.Puntarenas);
                                    break;
                                }
                                case "Guanacaste": {
                                    provinciasInteres.add(NProvincia.Guancaste);
                                    break;
                                }
                                case "Alajuela": {
                                    provinciasInteres.add(NProvincia.Alajuela);
                                    break;
                                }
                                case "Cartago": {
                                    provinciasInteres.add(NProvincia.Cartago);
                                    break;
                                }
                                case "Heredia": {
                                    provinciasInteres.add(NProvincia.Heredia);
                                    break;
                                }
                            }
                        }
                        actual.setProvinciasInteres(provinciasInteres);
                    }
                }
            }
            if (actual.getCorreoElectronico() == null) {
               continue;
            }
            else {
                personas.add(actual);
            }
        }
        System.out.println("Se cargaron " + personas.size() + " personas del archivo de excel.");
        fis.close();
        return personas;
    }
}
