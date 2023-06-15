import java.util.ArrayList;

public class Serie extends Midia {
    private int id;
    private int num_de_episodios;
    private double media = 0;
    private String nome;
    private String genero;
    private Data dataDeLancamento;

    public Serie(int id, String nome, Data dataDeLancamento)
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

    public void avaliar(int nota, String comentario)
    {
        this.avaliacoes.add(new Avaliacao(nota, comentario));
    }

    public String toString()
    {
        int soma = this.avaliacoes.stream().mapToInt(i -> i.getNota()).sum();
        if(this.avaliacoes.size() != 0) {
            this.media =  soma/this.avaliacoes.size();
        }
        
        return "Serie: " + this.nome + ","
            + "\nGênero: " + this.genero + ","
            + "\nIdioma: " + this.idioma + "," 
            + "\nVisualizações: " + this.views + ","
            + "\nLançamento: " + this.dataDeLancamento.dataFormatada() + ","
            + "\nAvaliação média: " + (this.avaliacoes.size() == 0 ? 0 : this.media);
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

    public int getDuracao()
    {
        return this.duracao;
    }

    public Data getLancamento()
    {
        return this.dataDeLancamento;
    }

    public ArrayList<Avaliacao> getAvaliacoes()
    {
        return this.avaliacoes;
    }
}
