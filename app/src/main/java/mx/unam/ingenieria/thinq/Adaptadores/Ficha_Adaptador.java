package mx.unam.ingenieria.thinq.Adaptadores;

public class Ficha_Adaptador {
    private String name;
    private int duracion;
    //private  int fecha;


    public Ficha_Adaptador(String name, int duracion) {
        this.name = name;
        this.duracion = duracion;
        //this.fecha=fecha;
    }
    public String getName(){return name;}
    public int getDuracion(){return duracion;}
    //public int getFecha(){return fecha;}
}
