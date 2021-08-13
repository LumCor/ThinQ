package mx.unam.ingenieria.thinq.Adaptadores;

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


