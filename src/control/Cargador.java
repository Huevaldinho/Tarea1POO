package control;

import modelo.Sismo;
import modelo.TOrigen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

public class Cargador {
    static XSSFRow row;
    private static ArrayList<Sismo> sismos = new ArrayList();
    public static boolean crearExcelSismos(){
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
    public static ArrayList<Sismo> cargarExcelSismos() throws IOException, ParseException {//se esta cargando mal en ram
        //Trae archivo excel
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(new File("Excel/baseDatosSismos.xlsx"));
        } catch (Exception IOException) {
            System.out.println("ERROR al cargar excel de sismos");
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
        System.out.println("Cantidad de sismo: " +cantidadFilas);
        int i=0;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatoInstanteExacto = new SimpleDateFormat("hh:mm:ss");
        while (i<cantidadFilas+1) {//el primero no se cuenta
            row = (XSSFRow) spreadsheet.getRow(i);
            i++;
            //row = (XSSFRow) rowIterator.next();
            //Iterador para recorrer las celdas de la fila
            Iterator <Cell>  cellIterator = row.cellIterator();
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
        return sismos;
    }
}
