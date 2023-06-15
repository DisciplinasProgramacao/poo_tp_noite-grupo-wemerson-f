import java.util.ArrayList;

public class Filme extends Midia {
    private int duracao; //(min)
    private double media = 0;

    public Filme(int id, String nome, Data dataDeLancamento)
    {   
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

    @Override
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

    @Override
    public String getNome()
    {
        return this.nome;
    }
    
    @Override
    public int getId()
    {
        return this.id;
    }

    @Override
    public int getViews()
    {
        return this.views;
    }

    @Override
    public Data getLancamento()
    {
        return this.dataDeLancamento;
    }

    @Override
    public ArrayList<Avaliacao> getAvaliacoes()
    {
        return this.avaliacoes;
    }

    @Override
    public int getDuracao() 
    {
        return this.duracao;

    }
}
