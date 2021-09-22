//Proyecto 1 | POO
//Elaborado por: Sebastián Arroniz Rojas, Sebatián Bermúdez Acuña y Felipe Obando Arrieta.
//Fecha de creación: 22/09/2021
//Última modificación: 00/00/2021

package control;

import modelo.Persona;
import modelo.Sismo;
import modelo.TOrigen;
import java.util.Date;
import java.util.ArrayList;

public class BaseDatos {
    //Atributos
    private ArrayList<Sismo> sismos;
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
}
