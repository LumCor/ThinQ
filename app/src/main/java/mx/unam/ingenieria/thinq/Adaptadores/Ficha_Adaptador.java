package mx.unam.ingenieria.thinq.Adaptadores;

public class Ficha_Adaptador {
    private String name;
    private int duracion;
    private  long fecha;


    public Ficha_Adaptador(String name, int duracion,long fecha) {
        this.name = name;
        this.duracion = duracion;
        this.fecha=fecha;
    }
    public String getName(){return name;}
    public int getDuracion(){return duracion;}
    public long getFecha(){return fecha;}
}
