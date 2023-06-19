import java.util.ArrayList;

public abstract class Midia {
    protected Genero[] generos = {
        Genero.ACAO, Genero.ANIME, Genero.AVENTURA,
        Genero.COMEDIA, Genero.DOCUMENTARIO, Genero.DRAMA, 
        Genero.POLICIAL, Genero.ROMANCE, Genero.SUSPENSE
    };
    protected int tipo;
    protected String[] idiomas = {"Portugês", "Inglês", "Chinês", "Hindi", "Espanhol", "Francês"};
    protected int id;
    protected Avaliacao avaliacao;
    protected ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    protected String nome;
    protected String idioma = idiomas[App.random.nextInt(6)];
    protected Genero genero = generos[App.random.nextInt(9)];
    protected Data dataDeLancamento;
    protected int views = 0;
    protected int duracao = 0;
    protected double media = 0;
    protected boolean lancamento = false;

    /**
     * Atualiza o número de pessoas que assistiram essa série
     */
    public void atualizaViews()
    {
        this.views++;
    }
    
    /**
     * Recebe uma nota e avalia a mídia
     * @param nota
     */
    public void avaliar(int nota, String data)
    {   
        this.avaliacao = new Avaliacao(nota);
        this.avaliacao.setData(new Data(data));
        this.avaliacoes.add(this.avaliacao);
    }

    /**
     * Adiciona à avaliação o comentário
     * @param comentario
     */
    public void comentar(String comentario)
    {
        this.avaliacao.comentar(comentario);
    }

    /**
     * Inicializa valores obrigatórios.
     * Este método é chamado quando o objeto é criado
     */
    protected void init()
    {
        this.genero = generos[App.random.nextInt(9)];
        this.idioma = idiomas[App.random.nextInt(6)];
    }

    /**
     * Altera o status da mídia para lançamento
     */
    public void marcarComoLancamento()
    {
        this.lancamento = true;
    }

    /**
     * Retorna o tipo
     */
    public int getTipo() {
        return this.tipo;
    }

     /**
     * Retorna o nome
     */
    public String getNome()
    {
        return this.nome;
    }

    /**
     * Retorna o id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Retorna as visualizações
     */
    public int getViews()
    {
        return this.views;
    }

    /**
     * Retorna a duração (Nº episodios)
     */
    public int getDuracao()
    {
        return this.duracao;
    }

    /**
     * Retorna a data de lançamento
     */
    public Data getDataLancamento()
    {
        return this.dataDeLancamento;
    }

    /**
     * Retorna um array de avaliações da série
     */
    public ArrayList<Avaliacao> getAvaliacoes()
    {
        return this.avaliacoes;
    }

    /**
     * Retorna a variável lancamento
     */
    public boolean getLancamento()
    {
        return this.lancamento;
    }

    /**
     * Retorna a média
     * @return
     */
    public double getMedia() {
        return media;
    }
    public Genero getGenero() {
        return genero;
    }
}  
