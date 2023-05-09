import java.sql.Date;

public class Serie {
    private String nome;
    private String idioma;
    private String genero;
    private int views = 0;
    private Date dataDeLancamento;

    
    /**
     * Atualiza o número de pessoas que assistiram essa série
     */
    public void atualizaViews()
    {
        this.views++;
    }
}
