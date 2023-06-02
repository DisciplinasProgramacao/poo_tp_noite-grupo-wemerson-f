import java.sql.Date;

public class Serie {
    private int id;
    private int num_de_episodios;
    private String nome;
    private String idioma;
    private String genero;
    private int views = 0;
    private Data dataDeLancamento;

    public Serie(int id, String nome, Data dataDeLancamento)
    {   
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
    }

    public void atualizaViews()
    {
        this.views++;
    }

    public String toString()
    {
        return "Serie: " + this.nome + "," + "\nVisualizações: " + this.views + "\nLançamento: " + this.dataDeLancamento;
    }


    public String getNome()
    {
        return this.nome;
    }

    public int getId()
    {
        return this.id;
    }

    public int getViews()
    {
        return this.views;
    }

    // public int getDuracao()
    // {
    //     return this.duracao;
    // }

    public Data getLancamento()
    {
        return this.dataDeLancamento;
    }
}
