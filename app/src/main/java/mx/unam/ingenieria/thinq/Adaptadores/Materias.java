package mx.unam.ingenieria.thinq.Adaptadores;

/**
 * Adaptador el cual contendra las variables que nos interesan de la base de datos de la collecion
 * "materias"
 * Para cada variable de realizo un "Getter and Setter"
 */

public class Materias {
    private String Asignatura;
    private String Dias;
    private String Notas;
    private int Grupo;

    public Materias(){
    }

    public  Materias(String asignatura, String dias, String notas){
        this.Asignatura=asignatura;
        this.Dias=dias;
        this.Notas=notas;
    }
    public String getAsignatura() {
        return Asignatura;
    }

    public void setAsignatura(String asignatura) {
        Asignatura = asignatura;
    }

    public String getDias() {
        return Dias;
    }

    public void setDias(String dias) {
        Dias = dias;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String notas) {
        Notas = notas;
    }

    public int getGrupo() {
        return Grupo;
    }

    public void setGrupo(int grupo) {
        Grupo = grupo;
    }

}


