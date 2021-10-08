//Proyecto 1 | POO
//Elaborado por: Sebastián Arroniz Rojas, Sebastián Bermúdez Acuña y Felipe Obando Arrieta.
//Fecha de creación: 22/09/2021
//Última modificación: 00/00/2021

package modelo;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Modelo de una persona con datos de contacto y provincias de interes
 */

public class Persona {
    //Atributos
    private String nombre;
    private String correoElectronico;
    private String numeroTelefono;
    private ArrayList<NProvincia> provinciasInteres;//provincia según como número de cédula

    //Constructores
    public Persona() {
    }

    public Persona(String _nombre, String _correoElectronico, String _numeroTelefono, ArrayList<NProvincia> _provinciasInteres) { //Con correo y número
        this.nombre = _nombre;
        this.correoElectronico = _correoElectronico;
        this.numeroTelefono = _numeroTelefono;
        this.provinciasInteres = _provinciasInteres;
    }

    public Persona(String _nombre, String _correoElectronico, ArrayList<NProvincia> _provinciasInteres) { //Solo con correo
        this.nombre = _nombre;
        this.correoElectronico = _correoElectronico;
        this.provinciasInteres = _provinciasInteres;
    }

    //Set y get
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public ArrayList<NProvincia> getProvinciasInteres() {
        return provinciasInteres;
    }

    public void setProvinciasInteres(ArrayList<NProvincia> provinciasInteres) {
        this.provinciasInteres = provinciasInteres;
    }

    //toString
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", provinciasInteres=" + provinciasInteres +
                '}';
    }
    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        if (persona.numeroTelefono == null) {
            return Objects.equals(correoElectronico, persona.correoElectronico);
        }
        else {
            return Objects.equals(correoElectronico, persona.correoElectronico) || Objects.equals(numeroTelefono, persona.numeroTelefono);
        }
    }
}
