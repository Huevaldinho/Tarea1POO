package control;
import modelo.Sismo;
import modelo.TOrigen;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import control.Cargador;
import control.BaseDatos;
public class controlador {
    private Cargador cargador = new Cargador();
    private BaseDatos BD;  // asociación comunicación entre el controlador y el admClientes

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
}

