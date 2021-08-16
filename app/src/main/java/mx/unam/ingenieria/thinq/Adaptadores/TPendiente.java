package mx.unam.ingenieria.thinq.Adaptadores;

/**
 * Se crea TPendiente, el cuál contendrá 3 parametros (materia, descripción y dificultad)
 * Estos serán los mismos que se asginaran en la base de datos
 */

public class TPendiente {
    private String materia;
    private String descripcion;
    private String dificultad;

    public TPendiente() {
    }

    //Constructor de TPendiente
    public TPendiente(String materia, String descripcion, String dificultad) {
        this.materia = materia;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
    }

    //Getter and Setter de TPendiente
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) { this.materia = materia; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }


}
