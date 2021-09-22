//Proyecto 1 | POO
//Elaborado por: Sebastián Arroniz Rojas, Sebastián Bermúdez Acuña y Felipe Obando Arrieta.
//Fecha de creación: 22/09/2021
//Última modificación: 00/00/2021

package modelo;

import java.util.Date;

public class Sismo {
    //Atributos
    private Date fecha;
    private Date instanteExacto;
    private double profundidad;
    private double magnitud;
    private TOrigen origen;
    private int provincia; //provincia según como número de cédula
    private double localizacionLatitud;
    private double localizacionLongitud;
    private String localizacionDescripcion;
    private int lugarOrigen; // 1 -> TIERRA | 2 -> MAR
    //Constructores
    public Sismo(){}
    public Sismo(Date _fecha,
          Date _instanteExacto,
          double _profundidad,
          double _magnitud,
          TOrigen _origen,
          int _provincia,
          double _localizacionLatitud,
          double _localizacionLongitud,
          String _localizacionDescripcion,
          int _lugarOrigen){
        this.fecha=_fecha;
        this.instanteExacto=_instanteExacto;
        this.profundidad=_profundidad;
        this.magnitud=_magnitud;
        this.origen=_origen;
        this.provincia=_provincia;
        this.localizacionLatitud=_localizacionLatitud;
        this.localizacionLongitud=_localizacionLongitud;
        this.localizacionDescripcion=_localizacionDescripcion;
        this.lugarOrigen=_lugarOrigen;
    }
    //Set y get
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getInstanteExacto() {
        return instanteExacto;
    }
    public void setInstanteExacto(Date instanteExacto) {
        this.instanteExacto = instanteExacto;
    }
    public String getLocalizacionDescripcion() {
        return localizacionDescripcion;
    }
    public void setLocalizacionDescripcion(String localizacionDescripcion) {
        this.localizacionDescripcion = localizacionDescripcion;
    }
    public double getLocalizacionLatitud() {
        return localizacionLatitud;
    }
    public void setLocalizacionLatitud(double localizacionLatitud) {
        this.localizacionLatitud = localizacionLatitud;
    }
    public double getLocalizacionLongitud() {
        return localizacionLongitud;
    }
    public void setLocalizacionLongitud(double localizacionLongitud) {
        this.localizacionLongitud = localizacionLongitud;
    }
    public int getLugarOrigen() {
        return lugarOrigen;
    }
    public void setLugarOrigen(int lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }
    public double getMagnitud() {
        return magnitud;
    }
    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }
    public TOrigen getOrigen() {
        return origen;
    }
    public void setOrigen(TOrigen origen) {
        this.origen = origen;
    }
    public double getProfundidad() {
        return profundidad;
    }
    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }
    public int getProvincia() {
        return provincia;
    }
    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }
    //toString
    @Override
    public String toString() {
        return "Sismo{" +
                "fecha=" + fecha +
                ", instanteExacto=" + instanteExacto +
                ", profundidad=" + profundidad +
                ", magnitud=" + magnitud +
                ", origen=" + origen +
                ", provincia=" + provincia +
                ", localizacionLatitud=" + localizacionLatitud +
                ", localizacionLongitud=" + localizacionLongitud +
                ", localizacionDescripcion='" + localizacionDescripcion + '\'' +
                ", lugarOrigen=" + lugarOrigen +
                '}';
    }
    //equals
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sismo sismo = (Sismo) o;
        return getFecha().equals(sismo.getFecha()) && getInstanteExacto().equals(sismo.getInstanteExacto());
    }
}
