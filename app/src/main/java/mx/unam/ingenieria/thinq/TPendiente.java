package mx.unam.ingenieria.thinq;

public class TPendiente {
    String materia;
    String descripcion;
    String dificultad;

    public TPendiente() {
    }

    public TPendiente(String materia, String descripcion, String dificultad) {
        this.materia = materia;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

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

    @Override
    public String toString() {
        return "TPendiente{" +
                "materia='" + materia + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", dificultad='" + dificultad + '\'' +
                '}';
    }
}
