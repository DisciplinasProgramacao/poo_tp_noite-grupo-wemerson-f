import java.util.ArrayList;

public class Filme extends Midia {
    private int duracao; //(min)

    public Filme(int id, String nome, Data dataDeLancamento)
    {   
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.init();
    }

    private void init()
    {
        this.views = 0;
        this.genero = generos[App.random.nextInt(9)];
        this.idioma = idiomas[App.random.nextInt(6)];
    }

    public void atualizaViews()
    {
        this.views++;
    }
    
    public void avaliar(int nota)
    {
        this.avaliacoes.add(new Avaliacao(nota));
    }


    public int getViews()
    {
        return this.views;
    }

    public String toString()
    {
        return "Filme: " + this.nome + "\nDuração: " + this.duracao + " min \nIdioma: " + this.idioma + "\nLançamento: " + this.dataDeLancamento;
    }

    public String getNome()
    {
        return this.nome;
    }
    public int getDuracao()
    {
        return this.duracao;
    }
    public Data getLancamento()
    {
        return this.dataDeLancamento;
    }

    public int getId()
    {
        return this.id;
    }

    public ArrayList<Avaliacao> getAvaliacoes()
    {
        return this.avaliacoes;
    }
}
