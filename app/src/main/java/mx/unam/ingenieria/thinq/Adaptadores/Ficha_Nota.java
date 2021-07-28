package mx.unam.ingenieria.thinq.Adaptadores;

public class Ficha_Nota
{
    String title,content;

    public Ficha_Nota(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public String getTitle(){return title;}
    public String getTContent(){return content;}
    public void setTitle(String x){this.title=x;}
    public void setTContent(String x){this.content=x;}
}
